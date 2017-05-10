package com.zeyiyouhuo.tms.tools.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/25.
 */
@Entity
@Table(name = "hyr_truck")
public class Truck extends AuditableEntity implements Serializable {
    private static final long serialVersionUID = 5273333483300386077L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long orgId; // 归属则一机构ID

    private long ownerId;

    private long fleetId; // 信息部（车队）ID

    private long primaryDriverId; // 主司机ID

    private long secondaryDriverId; // 副司机ID

    private String truckCode; // 车辆编码

    private String license; // 车牌

    private String carriageType;

    private float length;

    private float volume; // 体积

    @Column(name = "`load`")
    private float load; // 载重

    @JsonIgnore
    private int type; // 车辆来源, 0：外包车，1：外请车, 2：自营车

    @JsonIgnore
    private int ownBy; // 归属类型，0：司机拥有，1：信息部拥有，2：则一拥有

    private boolean canMount; //是否是挂车

    private long trailerId; //挂车ID

    private long vehicleLicensePic; //车辆行驶证

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date vehicleLicenseEndTime;

    private long transportLicensePic; //运输许可证

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date transportLicenseEndTime;

    private long vehicleInsurancePic;  //车辆保险单

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date vehicleInsuranceEndTime;

    @JsonIgnore
    private int certifyStatus;   //0、待审核   1、审核通过  2、审核失败

    private int runningStatus; //车辆运行状态 0空闲  1 在途   2 维修

    private boolean deleted; // 是否删除

    /**
     * 车辆类型
     */
    public enum TruckType {
        OUTSOURCE_TRUCK(0), INVITED_TRUCK(1), SELF_OWNED_TRUCK(2);
        private int typeCode;

        TruckType(int typeCode) {
            this.typeCode = typeCode;
        }

        public int getTypeCode() {
            return typeCode;
        }

        public static TruckType getTruckType(int typeCode) {
            for (TruckType type : TruckType.values()) {
                if (type.getTypeCode() == typeCode) {
                    return type;
                }
            }
            return null;
        }
    }

    /**
     * 注册来源类型， 司机注册，信息部注册，则一注册
     */
    public enum OwnByType {
        DRIVER(0), FLEET(1), ZEYI(2);
        private int code;

        OwnByType(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }

        public static OwnByType getRegisterByType(int code) {
            for (OwnByType type : OwnByType.values()) {
                if (type.getCode() == code) {
                    return type;
                }
            }
            return null;
        }
    }

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

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getFleetId() {
        return fleetId;
    }

    public void setFleetId(long fleetId) {
        this.fleetId = fleetId;
    }

    public long getPrimaryDriverId() {
        return primaryDriverId;
    }

    public void setPrimaryDriverId(long primaryDriverId) {
        this.primaryDriverId = primaryDriverId;
    }

    public long getSecondaryDriverId() {
        return secondaryDriverId;
    }

    public void setSecondaryDriverId(long secondaryDriverId) {
        this.secondaryDriverId = secondaryDriverId;
    }

    public String getTruckCode() {
        return truckCode;
    }

    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getCarriageType() {
        return carriageType;
    }

    public void setCarriageType(String carriageType) {
        this.carriageType = carriageType;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getLoad() {
        return load;
    }

    public void setLoad(float load) {
        this.load = load;
    }

    public int getType() {
        return type;
    }

    protected void setType(int type) {
        this.type = type;
    }

    public int getOwnBy() {
        return ownBy;
    }

    protected void setOwnBy(int ownBy) {
        this.ownBy = ownBy;
    }

    public boolean isCanMount() {
        return canMount;
    }

    public void setCanMount(boolean canMount) {
        this.canMount = canMount;
    }

    public long getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(long trailerId) {
        this.trailerId = trailerId;
    }

    public long getVehicleLicensePic() {
        return vehicleLicensePic;
    }

    public void setVehicleLicensePic(long vehicleLicensePic) {
        this.vehicleLicensePic = vehicleLicensePic;
    }

    public Date getVehicleLicenseEndTime() {
        return vehicleLicenseEndTime;
    }

    public void setVehicleLicenseEndTime(Date vehicleLicenseEndTime) {
        this.vehicleLicenseEndTime = vehicleLicenseEndTime;
    }

    public long getTransportLicensePic() {
        return transportLicensePic;
    }

    public void setTransportLicensePic(long transportLicensePic) {
        this.transportLicensePic = transportLicensePic;
    }

    public Date getTransportLicenseEndTime() {
        return transportLicenseEndTime;
    }

    public void setTransportLicenseEndTime(Date transportLicenseEndTime) {
        this.transportLicenseEndTime = transportLicenseEndTime;
    }

    public long getVehicleInsurancePic() {
        return vehicleInsurancePic;
    }

    public void setVehicleInsurancePic(long vehicleInsurancePic) {
        this.vehicleInsurancePic = vehicleInsurancePic;
    }

    public Date getVehicleInsuranceEndTime() {
        return vehicleInsuranceEndTime;
    }

    public void setVehicleInsuranceEndTime(Date vehicleInsuranceEndTime) {
        this.vehicleInsuranceEndTime = vehicleInsuranceEndTime;
    }

    public int getCertifyStatus() {
        return certifyStatus;
    }

    protected void setCertifyStatus(int certifyStatus) {
        this.certifyStatus = certifyStatus;
    }

    public int getRunningStatus() {
        return runningStatus;
    }

    public void setRunningStatus(int runningStatus) {
        this.runningStatus = runningStatus;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public TruckType getTruckType() {
        return TruckType.getTruckType(type);
    }

    public void setTruckType(TruckType type) {
        this.type = type.getTypeCode();
    }

    public OwnByType getOwnByType() {
        return OwnByType.getRegisterByType(this.ownBy);
    }

    public void setOwnByType(OwnByType type) {
        this.ownBy = type.getCode();
    }
}
