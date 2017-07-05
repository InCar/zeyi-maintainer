package com.zeyiyouhuo.tms.tools.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wh on 2017/1/11.
 */
@Entity
@Table(name = "hyr_waybill")
public class Waybill extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String waybillNumber;            //运单编号
    //短驳单信息
    private String deliveryNumber;           //交收单号
    private String receiver;                 //收货人
    private String receiverPhone;            //收货人电话
    private String receiverAddress;          //收货人地址
    private int shortBargeType;              //短驳类型 1.提货运单   2.送货运单
    private long customerWaybillId;          //客户运单id
    private String customerWaybillNumber;      //客户运单编号

    private long customerId;                 //客户ID
    private long customerContractId;         //客户合同ID(应收合同)
    private String customerContractNumber;   //客户合同编号(应收合同)
    private long routeId;                    //线路ID
    private String routeName;                //线路名称
    private String departureCity;            //出发城市
    private String arrivedCity;              //到达城市
    private long orgId;                      //所属机构
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date arrivalTime;                //靠站时间
    private String vehicleModel;             //车型
    private float vehicleLength;             //车长
    private long currentSiteId;              //运单当前站点Id
    private int currentSiteOrder;            //当前站点序号
    @Transient
    private String siteList;                 //线路站点集合
    private String remark;                   //备注
    private int waybillCategory;             //运单分类 1汽运运单 2卡班运单 3铁运运单 4.短驳
    private String description;              //货物品名
    private Integer amount;                  //件数
    private Float weight;                    //重量
    private Float volume;                    //体积
    private long payableContractId;          //应付合同号
    private String payableContractNumber;    //应付合同编号
    private long truckId;                    //车辆ID
    private String license;                  //车牌
    private long primaryDriverId;            //主司机ID
    private String primaryDriver;            //主司机
    private String primaryDriverPhone;       //主司机电话
    private long secondaryDriverId;          //副司机ID
    private String secondaryDriver;          //副司机
    private String secondaryDriverPhone;     //副司机电话
    private long ownerId;                    //车主id
    private String owner;                    //车主姓名
    private String ownerPhone;               //车主电话
    private long fleetId;                    //信息部ID
    private String fleetName;                 //信息部名称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date plannedStartTime;           //计划开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date plannedEndTime;             //计划完成时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date actualCompletionTime;       //实际完成时间
    private int isLate;                      //异常审核状态 0未晚点 1已晚点 2车管审批中 3区总审核中 4总部审核中 5审核完毕
    private int lateTime;                    //晚点时长（分钟）
    private int truckType;                   //车辆来源, 0：外包车，1：外请车, 2：自营车3：车队（信息部）车辆
    private long departurePhoto;             //发车照片
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date departurePhotoTime;         //上传发车照片的时间
    private long signPhoto;                  //签收照片
    private Date departureTime;              //发车时间
    private Date signTime;                   //签收时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date effectiveTime;              //生效时间
    private int waybillStatus;               //任务状态 -10已作废 0待指派 5已指派信息部 10已指派车辆 20待发车
    //30在途 40已到达 50已确认 60已签收
    private int replacement;                 //是否补单 默认0 ，   0 不是    1 是
    private int driverReceiptStatus;         //司机是否回单  0未回单 1已回单
    private String firstReceiptCode;         //回单1
    private String secondReceiptCode;        //回单2
    private boolean isException;             //是否是异常运单
    private int isOverTime;                  //是否加班 0 否  1 是
    private int buildSelf;                   //自建任务 0 否  1 是 默认0

    private long receivableContractRouteId;  //应收合同线路
    private long payableContractRouteId;     //应付合同线路
    private String carriageType;             //厢型
    private int signStatus;                  //签收状态 0 未签收 1已经部分签收 2全部签收
    private boolean revoked;                 //是否删除
    private long mileage;                    //实际里程数
    private int exceptionStatus;             //1： 追踪未关闭      2： 追踪关闭     3：已经完成扣款
    private long rootPartnerId;              //顶级客户Id
    private boolean lateStatus;                  //是否准点 0:晚点 1:准点
    private String siteDtos;                  //线路站点关系集合
    private int checkStatus;                 //审批状态 0:未处理 1:已处理 2:已确认 3:已审核 4:已关闭 5:车管审批 6:区域审批 7:总部审批 8:已完成

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getSiteDtos() {
        return siteDtos;
    }

    public void setSiteDtos(String siteDtos) {
        this.siteDtos = siteDtos;
    }

    public boolean isLateStatus() {
        return lateStatus;
    }

    public void setLateStatus(boolean lateStatus) {
        this.lateStatus = lateStatus;
    }

    public long getRootPartnerId() {
        return rootPartnerId;
    }

    public void setRootPartnerId(long rootPartnerId) {
        this.rootPartnerId = rootPartnerId;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public int getExceptionStatus() {
        return exceptionStatus;
    }

    public void setExceptionStatus(int exceptionStatus) {
        this.exceptionStatus = exceptionStatus;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public int getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(int signStatus) {
        this.signStatus = signStatus;
    }

    public String getCarriageType() {
        return carriageType;
    }

    public void setCarriageType(String carriageType) {
        this.carriageType = carriageType;
    }

    public int getCurrentSiteOrder() {
        return currentSiteOrder;
    }

    public void setCurrentSiteOrder(int currentSiteOrder) {
        this.currentSiteOrder = currentSiteOrder;
    }

    public long getReceivableContractRouteId() {
        return receivableContractRouteId;
    }

    public void setReceivableContractRouteId(long receivableContractRouteId) {
        this.receivableContractRouteId = receivableContractRouteId;
    }

    public long getPayableContractRouteId() {
        return payableContractRouteId;
    }

    public void setPayableContractRouteId(long payableContractRouteId) {
        this.payableContractRouteId = payableContractRouteId;
    }


    public Date getDeparturePhotoTime() {
        return departurePhotoTime;
    }

    public void setDeparturePhotoTime(Date departurePhotoTime) {
        this.departurePhotoTime = departurePhotoTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWaybillNumber() {
        return waybillNumber;
    }

    public void setWaybillNumber(String waybillNumber) {
        this.waybillNumber = waybillNumber;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public int getShortBargeType() {
        return shortBargeType;
    }

    public void setShortBargeType(int shortBargeType) {
        this.shortBargeType = shortBargeType;
    }

    public long getCustomerWaybillId() {
        return customerWaybillId;
    }

    public void setCustomerWaybillId(long customerWaybillId) {
        this.customerWaybillId = customerWaybillId;
    }

    public String getCustomerWaybillNumber() {
        return customerWaybillNumber;
    }

    public void setCustomerWaybillNumber(String customerWaybillNumber) {
        this.customerWaybillNumber = customerWaybillNumber;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getCustomerContractId() {
        return customerContractId;
    }

    public void setCustomerContractId(long customerContractId) {
        this.customerContractId = customerContractId;
    }

    public String getCustomerContractNumber() {
        return customerContractNumber;
    }

    public void setCustomerContractNumber(String customerContractNumber) {
        this.customerContractNumber = customerContractNumber;
    }

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivedCity() {
        return arrivedCity;
    }

    public void setArrivedCity(String arrivedCity) {
        this.arrivedCity = arrivedCity;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public float getVehicleLength() {
        return vehicleLength;
    }

    public void setVehicleLength(float vehicleLength) {
        this.vehicleLength = vehicleLength;
    }

    public long getCurrentSiteId() {
        return currentSiteId;
    }

    public void setCurrentSiteId(long currentSiteId) {
        this.currentSiteId = currentSiteId;
    }

    public String getSiteList() {
        return siteList;
    }

    public void setSiteList(String siteList) {
        this.siteList = siteList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getWaybillCategory() {
        return waybillCategory;
    }

    public void setWaybillCategory(int waybillCategory) {
        this.waybillCategory = waybillCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public long getPayableContractId() {
        return payableContractId;
    }

    public void setPayableContractId(long payableContractId) {
        this.payableContractId = payableContractId;
    }

    public String getPayableContractNumber() {
        return payableContractNumber;
    }

    public void setPayableContractNumber(String payableContractNumber) {
        this.payableContractNumber = payableContractNumber;
    }

    public long getTruckId() {
        return truckId;
    }

    public void setTruckId(long truckId) {
        this.truckId = truckId;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public long getPrimaryDriverId() {
        return primaryDriverId;
    }

    public void setPrimaryDriverId(long primaryDriverId) {
        this.primaryDriverId = primaryDriverId;
    }

    public String getPrimaryDriver() {
        return primaryDriver;
    }

    public void setPrimaryDriver(String primaryDriver) {
        this.primaryDriver = primaryDriver;
    }

    public String getPrimaryDriverPhone() {
        return primaryDriverPhone;
    }

    public void setPrimaryDriverPhone(String primaryDriverPhone) {
        this.primaryDriverPhone = primaryDriverPhone;
    }

    public long getSecondaryDriverId() {
        return secondaryDriverId;
    }

    public void setSecondaryDriverId(long secondaryDriverId) {
        this.secondaryDriverId = secondaryDriverId;
    }

    public String getSecondaryDriver() {
        return secondaryDriver;
    }

    public void setSecondaryDriver(String secondaryDriver) {
        this.secondaryDriver = secondaryDriver;
    }

    public String getSecondaryDriverPhone() {
        return secondaryDriverPhone;
    }

    public void setSecondaryDriverPhone(String secondaryDriverPhone) {
        this.secondaryDriverPhone = secondaryDriverPhone;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public long getFleetId() {
        return fleetId;
    }

    public void setFleetId(long fleetId) {
        this.fleetId = fleetId;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    public Date getPlannedStartTime() {
        return plannedStartTime;
    }

    public void setPlannedStartTime(Date plannedStartTime) {
        this.plannedStartTime = plannedStartTime;
    }

    public Date getActualCompletionTime() {
        return actualCompletionTime;
    }

    public void setActualCompletionTime(Date actualCompletionTime) {
        this.actualCompletionTime = actualCompletionTime;
    }

    public int getIsLate() {
        return isLate;
    }

    public void setIsLate(int isLate) {
        this.isLate = isLate;
    }

    public int getLateTime() {
        return lateTime;
    }

    public void setLateTime(int lateTime) {
        this.lateTime = lateTime;
    }

    public int getTruckType() {
        return truckType;
    }

    public void setTruckType(int truckType) {
        this.truckType = truckType;
    }

    public long getDeparturePhoto() {
        return departurePhoto;
    }

    public void setDeparturePhoto(long departurePhoto) {
        this.departurePhoto = departurePhoto;
    }

    public long getSignPhoto() {
        return signPhoto;
    }

    public void setSignPhoto(long signPhoto) {
        this.signPhoto = signPhoto;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public int getWaybillStatus() {
        return waybillStatus;
    }

    public void setWaybillStatus(int waybillStatus) {
        this.waybillStatus = waybillStatus;
    }

    public int getReplacement() {
        return replacement;
    }

    public void setReplacement(int replacement) {
        this.replacement = replacement;
    }

    public int getDriverReceiptStatus() {
        return driverReceiptStatus;
    }

    public void setDriverReceiptStatus(int driverReceiptStatus) {
        this.driverReceiptStatus = driverReceiptStatus;
    }

    public String getFirstReceiptCode() {
        return firstReceiptCode;
    }

    public void setFirstReceiptCode(String firstReceiptCode) {
        this.firstReceiptCode = firstReceiptCode;
    }

    public String getSecondReceiptCode() {
        return secondReceiptCode;
    }

    public void setSecondReceiptCode(String secondReceiptCode) {
        this.secondReceiptCode = secondReceiptCode;
    }

    public Date getPlannedEndTime() {
        return plannedEndTime;
    }

    public void setPlannedEndTime(Date plannedEndTime) {
        this.plannedEndTime = plannedEndTime;
    }

    public boolean isException() {
        return isException;
    }

    public void setException(boolean exception) {
        isException = exception;
    }

    public int getIsOverTime() {
        return isOverTime;
    }

    public void setIsOverTime(int isOverTime) {
        this.isOverTime = isOverTime;
    }

    public int getBuildSelf() {
        return buildSelf;
    }

    public void setBuildSelf(int buildSelf) {
        this.buildSelf = buildSelf;
    }
}

