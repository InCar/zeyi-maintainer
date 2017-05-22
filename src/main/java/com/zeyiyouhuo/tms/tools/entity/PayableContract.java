package com.zeyiyouhuo.tms.tools.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 应付合同
 * Created by wh on 2016/12/22.
 */

@Entity
@Table(name = "hyr_payable_contract")
public class PayableContract extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String contractNumber;           //合同编号
    private int contractType;                //合同类型   1常规合同  2口头合同
    private int contractStatus;              //合同状态   1待审核 2作废 3已审核
    private String contractCode;             //合同码/审批号
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date contractStartTime;          //合同生效时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date contractEndTime;            //合同失效时间
    private long schedulingPersonnel;        //调度人员
    private int contractCategory;            //合同类别  1汽运长期 2汽运临时 3卡班长期 4卡班临时 5短驳长期 6短驳临时 7铁路合同
    private long orgId;                      //所属机构ID
    private String attachmentId;             //附件Id，逗号分隔

    //汽运临时信息
    private BigDecimal deposit;                 //押金
    private BigDecimal prepaidToBank;           //预付打卡
    private BigDecimal prepaidCash;             //预付现金
    private BigDecimal freightCollect;          //到付
    private BigDecimal freightPaymentReceipt;   //回单付
    private BigDecimal oilFee;                  //油卡金额


    //铁运信息
    private String forwarder;                    //承运商
    private String contacts;                     // 联系人
    private String contactsPhone;                // 联系电话
    private BigDecimal price;                //承运费用 OR 单价(铁运合同 为单价)

    private String oilCardNumbers;              //油卡编号集合

    private String gpsNumber;                   //GPS编号
    private int deleted;                     //是否删除 1已删除

    public PayableContract() {

        this.deposit = new BigDecimal("0");
        this.prepaidToBank = new BigDecimal("0");
        this.prepaidCash = new BigDecimal("0");
        this.freightCollect = new BigDecimal("0");
        this.freightPaymentReceipt = new BigDecimal("0");
        this.oilFee = new BigDecimal("0");
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public int getContractType() {
        return contractType;
    }

    public void setContractType(int contractType) {
        this.contractType = contractType;
    }

    public int getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(int contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Date getContractStartTime() {
        return contractStartTime;
    }

    public void setContractStartTime(Date contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public Date getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(Date contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public long getSchedulingPersonnel() {
        return schedulingPersonnel;
    }

    public void setSchedulingPersonnel(long schedulingPersonnel) {
        this.schedulingPersonnel = schedulingPersonnel;
    }

    public int getContractCategory() {
        return contractCategory;
    }

    public void setContractCategory(int contractCategory) {
        this.contractCategory = contractCategory;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getPrepaidToBank() {
        return prepaidToBank;
    }

    public void setPrepaidToBank(BigDecimal prepaidToBank) {
        this.prepaidToBank = prepaidToBank;
    }

    public BigDecimal getPrepaidCash() {
        return prepaidCash;
    }

    public void setPrepaidCash(BigDecimal prepaidCash) {
        this.prepaidCash = prepaidCash;
    }

    public BigDecimal getFreightCollect() {
        return freightCollect;
    }

    public void setFreightCollect(BigDecimal freightCollect) {
        this.freightCollect = freightCollect;
    }

    public BigDecimal getFreightPaymentReceipt() {
        return freightPaymentReceipt;
    }

    public void setFreightPaymentReceipt(BigDecimal freightPaymentReceipt) {
        this.freightPaymentReceipt = freightPaymentReceipt;
    }

    public BigDecimal getOilFee() {
        return oilFee;
    }

    public void setOilFee(BigDecimal oilFee) {
        this.oilFee = oilFee;
    }

    public String getForwarder() {
        return forwarder;
    }

    public void setForwarder(String forwarder) {
        this.forwarder = forwarder;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getOilCardNumbers() {
        return oilCardNumbers;
    }

    public void setOilCardNumbers(String oilCardNumbers) {
        this.oilCardNumbers = oilCardNumbers;
    }

    public String getGpsNumber() {
        return gpsNumber;
    }

    public void setGpsNumber(String gpsNumber) {
        this.gpsNumber = gpsNumber;
    }
}