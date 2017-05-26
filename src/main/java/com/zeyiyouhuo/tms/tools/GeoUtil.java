package com.zeyiyouhuo.tms.tools;

/**
 * Created by dave on 17/5/25.
 */
public class GeoUtil {
    private static final double A = 6378245.0;
    private static final double EE = 0.00669342162296594323;
    private static final double RAD = Math.PI / 180;

    // 计算两点距离
    public static double calcDistance(double lng1, double lat1, double lng2, double lat2) {
        lng1 = RAD * lng1;
        lng2 = RAD * lng2;
        lat1 = RAD * lat1;
        lat2 = RAD * lat2;

        return Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lng2 - lng1)) * 6371393;
    }

    // 距离某点radius米，对应的维度差值
    public static double getDeltaLat(double radius) {
        return radius / 111000;
    }

    // 距离某点radius米，对应的经度差值
    public static double getDeltaLng(double lat, double radius) {
        return radius / 111000 / Math.cos(RAD * lat);
    }

    // 点是否在path构成的多边形内
    public static boolean pointInPolygon(double[] point, double[][] path) {
        boolean inside = false;

        for (int i = 0, j = path.length - 1; i < path.length; j = i++) {
            double x = point[0];
            double y = point[1];
            double xi = path[i][0];
            double xj = path[j][0];
            double yi = path[i][1];
            double yj = path[j][1];

            if (((yi > y) != (yj > y)) && (x < (xj - xi) * (y - yi) / (yj - yi) + xi)) {
                inside = !inside;
            }
        }

        return inside;
    }


    /**
     * WGS-84坐标转换成GCJ-02坐标，精度在0.000001度及1米内
     * @param wgLongitude
     * @param wgLatitude
     * @return
     */
    public static double[] wgs84ToGcj02(double wgLongitude, double wgLatitude) {
        double mgLat;
        double mgLon;

        if (outOfChina(wgLatitude, wgLongitude)) {
            mgLat = wgLatitude;
            mgLon = wgLongitude;
        } else {
            double dLat = transformLat(wgLongitude - 105.0, wgLatitude - 35.0);
            double dLon = transformLon(wgLongitude - 105.0, wgLatitude - 35.0);
            double radLat = wgLatitude / 180.0 * Math.PI;
            double magic = Math.sin(radLat);
            magic = 1 - EE * magic * magic;
            double sqrtMagic = Math.sqrt(magic);
            dLat = (dLat * 180.0) / ((A * (1 - EE)) / (magic * sqrtMagic) * Math.PI);
            dLon = (dLon * 180.0) / (A / sqrtMagic * Math.cos(radLat) * Math.PI);
            mgLat = wgLatitude + dLat;
            mgLon = wgLongitude + dLon;
        }

        return new double[]{mgLon, mgLat};
    }

    private static boolean outOfChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347) {
            return true;
        }
        if (lat < 0.8293 || lat > 55.8271) {
            return true;
        }
        return false;
    }

    private static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x * Math.PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * Math.PI) + 40.0 * Math.sin(y / 3.0 * Math.PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * Math.PI) + 320 * Math.sin(y * Math.PI / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x * Math.PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * Math.PI) + 40.0 * Math.sin(x / 3.0 * Math.PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * Math.PI) + 300.0 * Math.sin(x / 30.0 * Math.PI)) * 2.0 / 3.0;
        return ret;
    }
}
