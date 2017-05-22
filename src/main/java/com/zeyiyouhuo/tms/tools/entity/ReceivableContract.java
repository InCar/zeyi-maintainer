package com.zeyiyouhuo.tms.tools.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 应收合同
 * Created by wh on 2016/12/19.
 */

@Entity
@Table(name = "hyr_receivable_contract")
public class ReceivableContract extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String contractName;             //合同名称
    private String contractNumber;           //合同编号
    private String customerContractNumber;   //客户合同编号
    private int contractType;                //合同类型   1常规合同  2口头合同
    private int contractStatus;              //合同状态   1待审核 2作废 3已审核
    private String contractCode;             //合同码/审批号
    private Long customer;                   //客户ID
    private long orgId;                      //所属机构
    private String contacts;                 //联系人
    private String contactsPhone;            //联系电话
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date contractStartTime;          //合同生效时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date contractEndTime;            //合同失效时间
    private int contractCategory;            //合同类别  1整车长期 2整车临时 3零担合同 4铁路合同 5临时零担 6临时铁路
    private BigDecimal price;                //临时应收或者应付价格
    private String attachmentId;             //附件Id，逗号分隔
    private int deleted;                     //是否删除 1已删除



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getCustomerContractNumber() {
        return customerContractNumber;
    }

    public void setCustomerContractNumber(String customerContractNumber) {
        this.customerContractNumber = customerContractNumber;
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

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
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

    public int getContractCategory() {
        return contractCategory;
    }

    public void setContractCategory(int contractCategory) {
        this.contractCategory = contractCategory;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
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
}
