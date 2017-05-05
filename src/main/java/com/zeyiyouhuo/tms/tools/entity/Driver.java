package com.zeyiyouhuo.tms.tools.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by dave on 17/5/5.
 */
@Entity
@Table(name = "hyr_driver")
public class Driver extends AuditableEntity implements Serializable {
    private static final long serialVersionUID = 5263437241947463161L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long orgId;     // 归属则一机构ID

    private long fleetId;   // 归属信息部ID

    private long truckId;

    private long userId;

    private boolean isPrimary; // 是否是主司机

    private boolean employed; // 是否雇请司机（自营司机）

    private String phone;

    private String name;

    private String idCard; //身份证号

    private long idCardPic; //身份证图片

    private String drivingLicense;  //驾照号码

    private long drivingLicensePic; //驾照图片路径

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date drivingLicenseEndTime;

    private long jobCertificatePic; //从业资格证图片

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date jobCertificateEndTime;

    private boolean deleted; //是否删除 默认0   1 表示删除

    @JsonIgnore
    private int certifyStatus;  // 0 未认证, 1 提交认证中, 2 认证成功, 3 认证失败, 10 帐户禁用

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

    public long getFleetId() {
        return fleetId;
    }

    public void setFleetId(long fleetId) {
        this.fleetId = fleetId;
    }

    public long getTruckId() {
        return truckId;
    }

    public void setTruckId(long truckId) {
        this.truckId = truckId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public boolean isEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public long getIdCardPic() {
        return idCardPic;
    }

    public void setIdCardPic(long idCardPic) {
        this.idCardPic = idCardPic;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public long getDrivingLicensePic() {
        return drivingLicensePic;
    }

    public void setDrivingLicensePic(long drivingLicensePic) {
        this.drivingLicensePic = drivingLicensePic;
    }

    public Date getDrivingLicenseEndTime() {
        return drivingLicenseEndTime;
    }

    public void setDrivingLicenseEndTime(Date drivingLicenseEndTime) {
        this.drivingLicenseEndTime = drivingLicenseEndTime;
    }

    public long getJobCertificatePic() {
        return jobCertificatePic;
    }

    public void setJobCertificatePic(long jobCertificatePic) {
        this.jobCertificatePic = jobCertificatePic;
    }

    public Date getJobCertificateEndTime() {
        return jobCertificateEndTime;
    }

    public void setJobCertificateEndTime(Date jobCertificateEndTime) {
        this.jobCertificateEndTime = jobCertificateEndTime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getCertifyStatus() {
        return certifyStatus;
    }

    protected void setCertifyStatus(int certifyStatus) {
        this.certifyStatus = certifyStatus;
    }

}