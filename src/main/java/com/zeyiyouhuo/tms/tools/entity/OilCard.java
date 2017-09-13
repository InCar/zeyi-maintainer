package com.zeyiyouhuo.tms.tools.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by dave on 17/1/3.
 */
@Entity
@Table(name = "hyr_oil_card")
public class OilCard extends AuditableEntity implements Serializable {
    private static final long serialVersionUID = 6647500079543745956L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String cardNumber; // 油卡序列号

    private int type; // 1 中石油， 2 中石化， 3 汇通天下

    private double latestChargeAmount; //最新充值金额

    private Date latestChargeTime; //最新充值时间

    private long orgId; // 归属机构

    private long staffId; //所属员工ID

    private String staffName; //所属员工名字

    private long truckId; // 当前关联车辆

    private String remark; // 备注

    private int status; // 1: 可下发， 2: 已下发，3: 作废，

    private boolean deleted;

    private long supplierId; //供应商id

    public OilCard(String cardNumber, int type, long orgId, String remark, int status, int supplierId) {
        this.cardNumber = cardNumber;
        this.type = type;
        this.orgId = orgId;
        this.remark = remark;
        this.status = status;
        this.supplierId = supplierId;
    }

    public OilCard() {
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }


    public double getLatestChargeAmount() {
        return latestChargeAmount;
    }

    public void setLatestChargeAmount(double latestChargeAmount) {
        this.latestChargeAmount = latestChargeAmount;
    }

    public Date getLatestChargeTime() {
        return latestChargeTime;
    }

    public void setLatestChargeTime(Date latestChargeTime) {
        this.latestChargeTime = latestChargeTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public long getTruckId() {
        return truckId;
    }

    public void setTruckId(long truckId) {
        this.truckId = truckId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
