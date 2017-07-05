package com.zeyiyouhuo.tms.tools.dto;

import com.zeyiyouhuo.tms.tools.entity.RouteSiteRelation;

/**
 * Created by LuoFeng on 2017/6/28.
 */
public class SiteDto {
    private int siteOrder;          //站点序号
    private int mileage;            //行驶里程
    private int driveTime;         //行驶时间
    private int restTime;           //停靠时间


    public SiteDto() {
    }

    public SiteDto(RouteSiteRelation routeSiteRelation) {
        this.siteOrder = routeSiteRelation.getSiteOrder();
        this.driveTime = routeSiteRelation.getDriveTime();
        this.restTime = routeSiteRelation.getRestTime();
        this.mileage = routeSiteRelation.getMileage();

    }

    public int getSiteOrder() {
        return siteOrder;
    }

    public void setSiteOrder(int siteOrder) {
        this.siteOrder = siteOrder;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getDriveTime() {
        return driveTime;
    }

    public void setDriverTime(int driveTime) {
        this.driveTime = driveTime;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }
}
