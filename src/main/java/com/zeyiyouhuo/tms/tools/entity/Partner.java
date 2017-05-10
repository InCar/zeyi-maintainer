package com.zeyiyouhuo.tms.tools.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by incarxsh on 2016/10/25 0025.
 */
@Entity
@Table(name = "hyr_partner")
public class Partner extends AuditableEntity implements Serializable {
    private static final long serialVersionUID = -7609827181760620473L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String fullName;

    private String name;

    private String nameAbbr;

    private long orgId;                 //客户机构ID

    private String partnerOrgCode;      //客户机构码

    private long rootPartnerId;         //顶级客户Id

    private long parentPartnerId;       // 上级客户ID

    private long zeyiOrgId;             //归属则一机构ID

    private int partnerType;            //类型 (1,长期 2,临时,默认为2)

    private String idCode;              //社会信用代码

    private String contact;             //联系人

    private String tel;                 //联系电话

    private String contactJob;          //联系人职务

    private long dispatcherUserId;      //调度员id

    private long salesmanUserId;        //业务员id

    private long accountantUserId;      //结算员工号

    private int provinceCode;

    private String provinceName;

    private int cityCode;

    private String cityName;

    private String address;

    private String remark;

    private boolean deleted;

    private long payPartnerId;              //结算客户id

    private int payDay;                     //周期结算日

    private int payPeriod;                  //结算周期（1，现结   2，月结    3，季结）

    private boolean hasInvoiceInfo;         //是否有开票信息

    private String invoiceName;             //开票信息-名称

    private String invoiceTaxpayerNum;      //开票信息-纳税人识别号

    private String invoiceAddress;          //开票信息-地址

    private String invoicePhone;            //开票信息-电话

    private String invoiceBank;             //开票信息-开户行

    private String invoiceAccount;          //开票信息-账号

    private int invoiceType;                //开票信息-开票类型,1虚拟发票，2增值税专用发票

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAbbr() {
        return nameAbbr;
    }

    public void setNameAbbr(String nameAbbr) {
        this.nameAbbr = nameAbbr;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getPartnerOrgCode() {
        return partnerOrgCode;
    }

    public void setPartnerOrgCode(String partnerOrgCode) {
        this.partnerOrgCode = partnerOrgCode;
    }

    public long getRootPartnerId() {
        return rootPartnerId;
    }

    public void setRootPartnerId(long rootPartnerId) {
        this.rootPartnerId = rootPartnerId;
    }

    public long getParentPartnerId() {
        return parentPartnerId;
    }

    public void setParentPartnerId(long parentPartnerId) {
        this.parentPartnerId = parentPartnerId;
    }

    public long getZeyiOrgId() {
        return zeyiOrgId;
    }

    public void setZeyiOrgId(long zeyiOrgId) {
        this.zeyiOrgId = zeyiOrgId;
    }

    public int getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(int partnerType) {
        this.partnerType = partnerType;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getContactJob() {
        return contactJob;
    }

    public void setContactJob(String contactJob) {
        this.contactJob = contactJob;
    }

    public long getDispatcherUserId() {
        return dispatcherUserId;
    }

    public void setDispatcherUserId(long dispatcherUserId) {
        this.dispatcherUserId = dispatcherUserId;
    }

    public long getSalesmanUserId() {
        return salesmanUserId;
    }

    public void setSalesmanUserId(long salesmanUserId) {
        this.salesmanUserId = salesmanUserId;
    }

    public long getAccountantUserId() {
        return accountantUserId;
    }

    public void setAccountantUserId(long accountantUserId) {
        this.accountantUserId = accountantUserId;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getPayPartnerId() {
        return payPartnerId;
    }

    public void setPayPartnerId(long payPartnerId) {
        this.payPartnerId = payPartnerId;
    }

    public int getPayDay() {
        return payDay;
    }

    public void setPayDay(int payDay) {
        this.payDay = payDay;
    }

    public int getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(int payPeriod) {
        this.payPeriod = payPeriod;
    }

    public boolean isHasInvoiceInfo() {
        return hasInvoiceInfo;
    }

    public void setHasInvoiceInfo(boolean hasInvoiceInfo) {
        this.hasInvoiceInfo = hasInvoiceInfo;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public String getInvoiceTaxpayerNum() {
        return invoiceTaxpayerNum;
    }

    public void setInvoiceTaxpayerNum(String invoiceTaxpayerNum) {
        this.invoiceTaxpayerNum = invoiceTaxpayerNum;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public String getInvoicePhone() {
        return invoicePhone;
    }

    public void setInvoicePhone(String invoicePhone) {
        this.invoicePhone = invoicePhone;
    }

    public String getInvoiceBank() {
        return invoiceBank;
    }

    public void setInvoiceBank(String invoiceBank) {
        this.invoiceBank = invoiceBank;
    }

    public String getInvoiceAccount() {
        return invoiceAccount;
    }

    public void setInvoiceAccount(String invoiceAccount) {
        this.invoiceAccount = invoiceAccount;
    }

    public int getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(int invoiceType) {
        this.invoiceType = invoiceType;
    }
}
