package com.dhlk.apis.service;

import com.alibaba.fastjson.JSONObject;
import com.dhlk.apis.entity.Energy;
import com.dhlk.apis.util.Result;
import com.dhlk.apis.util.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2021/3/23
 * Time:10:57
 * @Description:
 */
@Service
public class ReadHdfs implements Serializable {

    static HashSet<String> fileList = new HashSet<>();

    private String dir;

    /**
     * @param hdfsUrl
     * @param tenant
     * @param deviceId
     * @param dt
     * @return
     * @throws Exception
     */
    public Result readHdfsDate(String hdfsUrl, String tenant, String deviceId, String dt, Integer pageNum, Integer pageSize) throws Exception {
        // hadoop 配置
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", hdfsUrl);
        FSDataInputStream in = null;

        FileSystem fs = FileSystem.get(
                URI.create(hdfsUrl),
                conf, "root");

        String month = "";
        String path = "";
        dir = new String(ResourceBundle.getBundle("application").getString("hdfsdir").getBytes("ISO-8859-1"), "GBK");

        if (dt != null && dt != "") {
            month = dt.substring(0, 7);
        }

        if (tenant != null) {
            if (deviceId != null) {
                if (dt != null) {
                    path = hdfsUrl + dir + tenant + "/" + deviceId + "/" + month + "/" + dt;
                } else {
                    path = hdfsUrl + dir + tenant + "/" + deviceId;
                }
            } else {
                path = hdfsUrl + dir + tenant;
            }
        } else {
            path = hdfsUrl + dir;
        }

        HashSet<String> listAllFile = listAll(conf, new Path(path));
        PageHelper.startPage(pageNum, pageSize);
        ArrayList<String> list = new ArrayList<>();
        for (String s : listAllFile) {
            in = fs.open(new Path(s));
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));

            String line = null;

            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            in.close();
        }
        fs.close();

        int startNum = (pageNum - 1) * pageSize;
        int endNum = 0;
        if (startNum > list.size()) {
            startNum = 0;
        }
        if (pageSize > list.size() || (pageNum * pageSize) > list.size()) {
            endNum = list.size();
        } else {
            endNum = pageNum * pageSize;
        }

        List<String> subList = list.subList(startNum, endNum);
        ArrayList<Energy> arrayList = new ArrayList<>();
        for (String s : subList) {
            Energy energy = parseHdfsData(s);
            arrayList.add(energy);
        }

        PageInfo<Energy> pageInfo = new PageInfo<>(arrayList);
        return ResultUtils.success(pageInfo);
    }

    private Energy parseHdfsData(String s) {
        Energy energy = null;
        JSONObject after = null;
        if (s.contains("table")) {
            JSONObject jsonObject = JSONObject.parseObject(s);
             after = jsonObject.getJSONObject("after");

        }else {
             after = JSONObject.parseObject(s);
        }
        energy = new Energy(
                after.get("energy_ts").toString(),
                after.get("energy_id").toString(),
                after.get("t_act_energy").toString(),
                after.get("t_react_energy").toString(),
                after.get("t_act_pwr").toString(),
                after.get("t_react_pwr").toString(),
                after.get("t_act_energy_A").toString(),
                after.get("t_react_energy_A").toString(),
                after.get("E_V_A").toString(),
                after.get("E_I_A").toString(),
                after.get("act_pwr_A").toString(),
                after.get("react_pwr_A").toString(),
                after.get("pwr_rate_A").toString(),
                after.get("t_act_energy_B").toString(),
                after.get("t_react_energy_B").toString(),
                after.get("E_V_B").toString(),
                after.get("E_I_B").toString(),
                after.get("act_pwr_B").toString(),
                after.get("react_pwr_B").toString(),
                after.get("pwr_rate_B").toString(),
                after.get("t_act_energy_C").toString(),
                after.get("t_react_energy_C").toString(),
                after.get("E_V_C").toString(),
                after.get("E_I_C").toString(),
                after.get("act_pwr_C").toString(),
                after.get("react_pwr_C").toString(),
                after.get("pwr_rate_C").toString(),
                after.get("freq").toString(),
                after.get("deviceCode").toString());
        return energy;
    }

    public static HashSet<String> listAll(Configuration conf, Path path) throws IOException {

        FileSystem fs = FileSystem.get(conf);

        if (fs.exists(path)) {
            FileStatus[] stats = fs.listStatus(path);
            for (int i = 0; i < stats.length; ++i) {
                if (!stats[i].isDir()) {
                    fileList.add(stats[i].getPath().toString());
                } else {
                    listAll(conf, stats[i].getPath());
                }
            }
        }
        fs.close();
        fs.close();

        return fileList;
    }

}
