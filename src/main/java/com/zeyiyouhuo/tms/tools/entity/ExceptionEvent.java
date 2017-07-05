package com.zeyiyouhuo.tms.tools.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by xy on 2017/2/5.
 *
 */
@Entity
@Table(name = "hyr_exception_event")
public class ExceptionEvent extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long waybillId;  //运单ID

    private String waybillNumber;  //运单编号

    private int eventType;  //异常事件类型： 0、正常 1、堵车  2、故障  3、其他  4、事故   5、货损  6、天气  7、未按规定线路行驶  8、停留超时 9.时速低

    private long eventPic;  //异常事件图片

    private long reportPic;  //异常报备图片

    private long gpsTruckPic;  //gps轨迹图

    private int processStatus;  //异常处理状态  0、未处理   1、已处理  2、审核通过  3、审核不通过

    private String remark;      //备注

    private boolean deleted;  //是否删除
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date trafficJamStartTime;   //堵车开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date trafficJamEndTime;     //堵车结束时间

    private int trafficJamTime;         //堵车时长

    private int reportTime;             //报备时长

    private long verifyId;            //审核人ID

    private String verifyName;          //审核人
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date verifyTime;            //审核时间

    private BigDecimal deductibleMoney;  //应扣金额

    private BigDecimal reduceMoney;     //减免金额

    private int completeDocument;       //三证齐全  默认0     齐全： 1       不齐全： 2


    public ExceptionEvent() {
        this.deductibleMoney = new BigDecimal(0);
        this.reduceMoney = new BigDecimal(0);
    }

    public int getCompleteDocument() {
        return completeDocument;
    }

    public void setCompleteDocument(int completeDocument) {
        this.completeDocument = completeDocument;
    }

    public Date getTrafficJamStartTime() {
        return trafficJamStartTime;
    }

    public void setTrafficJamStartTime(Date trafficJamStartTime) {
        this.trafficJamStartTime = trafficJamStartTime;
    }

    public Date getTrafficJamEndTime() {
        return trafficJamEndTime;
    }

    public void setTrafficJamEndTime(Date trafficJamEndTime) {
        this.trafficJamEndTime = trafficJamEndTime;
    }

    public int getTrafficJamTime() {
        return trafficJamTime;
    }

    public void setTrafficJamTime(int trafficJamTime) {
        this.trafficJamTime = trafficJamTime;
    }

    public int getReportTime() {
        return reportTime;
    }

    public void setReportTime(int reportTime) {
        this.reportTime = reportTime;
    }

    public long getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(long verifyId) {
        this.verifyId = verifyId;
    }

    public String getVerifyName() {
        return verifyName;
    }

    public void setVerifyName(String verifyName) {
        this.verifyName = verifyName;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public BigDecimal getDeductibleMoney() {
        return deductibleMoney;
    }

    public void setDeductibleMoney(BigDecimal deductibleMoney) {
        this.deductibleMoney = deductibleMoney;
    }

    public BigDecimal getReduceMoney() {
        return reduceMoney;
    }

    public void setReduceMoney(BigDecimal reduceMoney) {
        this.reduceMoney = reduceMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(long waybillId) {
        this.waybillId = waybillId;
    }

    public String getWaybillNumber() {
        return waybillNumber;
    }

    public void setWaybillNumber(String waybillNumber) {
        this.waybillNumber = waybillNumber;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public long getEventPic() {
        return eventPic;
    }

    public void setEventPic(long eventPic) {
        this.eventPic = eventPic;
    }

    public long getReportPic() {
        return reportPic;
    }

    public void setReportPic(long reportPic) {
        this.reportPic = reportPic;
    }

    public long getGpsTruckPic() {
        return gpsTruckPic;
    }

    public void setGpsTruckPic(long gpsTruckPic) {
        this.gpsTruckPic = gpsTruckPic;
    }

    public int getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(int processStatus) {
        this.processStatus = processStatus;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
