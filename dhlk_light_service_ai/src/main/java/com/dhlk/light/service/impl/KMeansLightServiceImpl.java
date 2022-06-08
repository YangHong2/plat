package com.dhlk.light.service.impl;

import com.dhlk.light.dao.KMeansLightDao;
import com.dhlk.light.entity.InfoBox;
import com.dhlk.light.entity.KMeansLight;
import com.dhlk.light.entity.LighgtProportion;
import com.dhlk.light.entity.LightLocation;
import com.dhlk.light.service.KMeansLightService;
import com.dhlk.light.util.Result;
import com.dhlk.light.util.ResultEnum;
import com.dhlk.light.util.ResultUtils;
import com.dhlk.light.util.LightClusterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2020/11/23
 * Time:10:18
 * @Description:
 */
@Service
public class KMeansLightServiceImpl implements KMeansLightService {

    @Autowired
    private KMeansLightDao kMeansLightDao;

    /**
     * 查询某个租户下聚类之后灯的信息
     *
     * @param factoryCode 租户
     * @return
     */
    @Override
    public Result findAll(String factoryCode) {

        if (factoryCode == null) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }

        List<KMeansLight> list = kMeansLightDao.findList(factoryCode);

        InfoBox infoBox = LightClusterUtil.lightCluster(list);

        System.out.println("infoBox1 "+infoBox.getClusterOne().length+" infoBox2 "+infoBox.getClusterTwo().length+" infoBox3 "+infoBox.getClusterThree().length);
        return ResultUtils.success(infoBox);
    }

    /**
     * 显示每类灯的占比
     *
     * @param factoryCode
     * @return
     */
    public Result lightProportion(String factoryCode) {

        List<KMeansLight> list = kMeansLightDao.findList(factoryCode);
        // 每一类的灯数量和占比 对象
        LighgtProportion lighgtProportion = new LighgtProportion();
        // 占比保留四位数
        DecimalFormat decimalFormat = new DecimalFormat("#.0000");

        InfoBox infoBox = LightClusterUtil.lightCluster(list);

        double goodCount = infoBox.getClusterOne().length; // 优秀数量
        double generalCount = infoBox.getClusterTwo().length; // 良好数量
        double dangerCount = infoBox.getClusterThree().length; // 一般数量

        lighgtProportion.setGoodCount((int) goodCount);
        lighgtProportion.setGeneralCount((int) generalCount);
        lighgtProportion.setDangerCount((int) dangerCount);

        double total = goodCount + generalCount + dangerCount;

        lighgtProportion.setGood(Double.parseDouble(decimalFormat.format(goodCount / total)));
        lighgtProportion.setGeneral(Double.parseDouble(decimalFormat.format(generalCount / total)));
        lighgtProportion.setDnager(Double.parseDouble(decimalFormat.format(dangerCount / total)));

        return ResultUtils.success(lighgtProportion);
    }

    /**
     * 重点关注的灯的区域信息
     *
     * @param factoryCode
     * @return
     */
    @Override
    public Result focusSnAndArea(String factoryCode) {
        List<KMeansLight> list = kMeansLightDao.findList(factoryCode);
        List<LightLocation> lightLocation = new ArrayList<>();
        if (list.size() > 0) {
            KMeansLight kMeansLight = list.get(0);

            String str3 = kMeansLight.getClusterThree();
            str3 = str3.replaceAll("\\[", " ");
            str3 = str3.replaceAll("]", " ");

            String[] three = str3.split(" , ");
            for (int i = 0; i < three.length; i++) {
                three[i] = three[i].trim().replaceAll(" ", "");
            }

            for (String s : three) {
                String[] split = s.split(",");
                LightLocation area = kMeansLightDao.selectAreaBySn(split[2]);
                lightLocation.add(area);
            }
        }
        return ResultUtils.success(lightLocation);
    }
}