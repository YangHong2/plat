package com.dhlk.light.util;

import com.dhlk.light.entity.InfoBox;
import com.dhlk.light.entity.KMeansLight;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2020/12/4
 * Time:8:56
 * @Description:
 */
public class LightClusterUtil {

    /**
     * 每一类的电压总和的80%和电流总和的20%之后求平均作为参考
     * 来判断灯性能优劣
     *
     * @param arr
     * @return
     */
    public static double plus(String[] arr) {

        double i = 0;
        double j = 0;
        for (String s : arr) {
            String[] split = s.split(",");
            i += Double.parseDouble(split[0]) * 0.2;
            j += Double.parseDouble(split[1]) * 0.8;
        }
        double p = (i + j) / arr.length;
        System.out.println(p);
        return p;
    }

    /**
     * 把聚类之后的字符串拆分，调用plus方法，判断性能
     * infoBox的clusterOne为性能优秀，clusterTwo为良好，clusterThree为性能一般
     *
     * @param list
     * @return InfoBox infoBox包装三类灯性能
     */
    public static InfoBox lightCluster(List<KMeansLight> list) {
        InfoBox infoBox = new InfoBox();

        for (KMeansLight kMeansLight : list) {
            String str = kMeansLight.getClusterOne();
            str = str.replaceAll("\\[", " ");
            str = str.replaceAll("]", " ");

            String[] one = str.split(" , ");
            for (int i = 0; i < one.length; i++) {
                one[i] = one[i].trim().replaceAll(" ", "");
            }
            System.out.println("clusterone " + one.length);

            String str2 = kMeansLight.getClusterTwo();
            str2 = str2.replaceAll("\\[", " ");
            str2 = str2.replaceAll("]", " ");
            String[] two = str2.split(" , ");

            for (int i = 0; i < two.length; i++) {
                two[i] = two[i].trim().replaceAll(" ", "");
            }
            System.out.println("clustertwo " + two.length);
            String str3 = kMeansLight.getClusterThree();
            str3 = str3.replaceAll("\\[", " ");
            str3 = str3.replaceAll("]", " ");
            String[] three = str3.split(" , ");

            for (int i = 0; i < three.length; i++) {
                three[i] = three[i].trim().replaceAll(" ", "");
            }
            System.out.println("clusterthree " + three.length);

            double onePlus = LightClusterUtil.plus(one);
            double twoPlus = LightClusterUtil.plus(two);
            double threePlus = LightClusterUtil.plus(three);

            if (onePlus < twoPlus) {
                if (twoPlus < threePlus) {
                    infoBox.setClusterOne(one);
                    infoBox.setClusterTwo(two);
                    infoBox.setClusterThree(three);
                } else if (threePlus > onePlus) {
                    infoBox.setClusterOne(one);
                    infoBox.setClusterTwo(three);
                    infoBox.setClusterThree(two);
                }else {
                    infoBox.setClusterOne(three);
                    infoBox.setClusterTwo(one);
                    infoBox.setClusterThree(two);
                }
            } else if (threePlus < onePlus) {
                if (threePlus < twoPlus) {
                    infoBox.setClusterOne(three);
                    infoBox.setClusterTwo(two);
                    infoBox.setClusterThree(one);
                }else {
                    infoBox.setClusterOne(two);
                    infoBox.setClusterTwo(three);
                    infoBox.setClusterThree(one);
                }
            } else {
                infoBox.setClusterOne(two);
                infoBox.setClusterTwo(one);
                infoBox.setClusterThree(three);
            }

        }
        return infoBox;
    }

}
