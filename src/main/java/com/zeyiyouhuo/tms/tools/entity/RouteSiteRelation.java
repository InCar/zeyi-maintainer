package com.zeyiyouhuo.tms.tools.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/12.
 */
@Entity
@Table(name = "hyr_route_site_relation")
public class RouteSiteRelation extends AuditOnCreationEntity implements Serializable {
    private static final long serialVersionUID = 8180160018835053348L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long routeId;     //线路ID

    private long siteId;     //站点ID

    private int siteOrder;      // 站点序号，出发站点序号为1，其他依次排序

    private int mileage;      //行驶里程

    private int driveTime;     //行驶时间（分钟）

    private int restTime;       //停靠时间（分钟）

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
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

    public void setDriveTime(int driveTime) {
        this.driveTime = driveTime;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }
}
