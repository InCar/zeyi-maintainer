package com.zeyiyouhuo.tms.tools.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 每日运单统计
 */
@Entity
@Table(name = "hyr_waybill_day_stat")
public class WaybillDayStat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long orgId;

    private long startedCount;  // 出发运单数量

    private long arrivedCount;  // 到达运单数量

    private Date statTime;      // 统计日期

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public long getStartedCount() {
        return startedCount;
    }

    public void setStartedCount(long startedCount) {
        this.startedCount = startedCount;
    }

    public long getArrivedCount() {
        return arrivedCount;
    }

    public void setArrivedCount(long arrivedCount) {
        this.arrivedCount = arrivedCount;
    }

    public Date getStatTime() {
        return statTime;
    }

    public void setStatTime(Date statTime) {
        this.statTime = statTime;
    }
}
