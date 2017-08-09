package com.zeyiyouhuo.tms.tools.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dave on 17/1/4.
 */
@Entity
@Table(name = "hyr_truck_owner")
public class TruckOwner extends AuditableEntity implements Serializable {
    private static final long serialVersionUID = -6524302844612165003L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long orgId; // 则一机构ID

    private long fleetId; // 信息部ID

    private long userId; // 车主帐户ID

    @JsonIgnore
    private int type; // 车主类型  0:个人 1:公司

    private String name; // 车主名称

    private String phone; // 车主联系电话

    private String idCard; // 身份证号

    private long idCardPic; // 身份证照片

    private String licenseNo;       //营业执照编号

    private long licensePic;      //营业执照照片

    private String payee; // 收款人，默认帐户

    private String bankCardNo; // 银行卡号

    private String bankName; // 开户行名称

    private long bankCardPic; // 银行卡照片

    @Transient
    private List<TruckOwnerReceiptAccount> receiptAccounts;

    private boolean deleted;

    /**
     * 车主类型
     */
    public enum OwnerType {
        PERSONAL(0), COMPANY(1);

        private int typeCode;

        OwnerType(int typeCode) {
            this.typeCode = typeCode;
        }

        public int getTypeCode() {
            return this.typeCode;
        }

        public static OwnerType getOwnerType(int typeCode) {
            for (OwnerType type : OwnerType.values()) {
                if (type.getTypeCode() == typeCode) {
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

    public long getFleetId() {
        return fleetId;
    }

    public void setFleetId(long fleetId) {
        this.fleetId = fleetId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    protected void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public long getLicensePic() {
        return licensePic;
    }

    public void setLicensePic(long licensePic) {
        this.licensePic = licensePic;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public long getBankCardPic() {
        return bankCardPic;
    }

    public void setBankCardPic(long bankCardPic) {
        this.bankCardPic = bankCardPic;
    }

    public OwnerType getOwnerType() {
        return OwnerType.getOwnerType(type);
    }

    public void setOwnerType(OwnerType type) {
        this.type = type.getTypeCode();
    }

    public List<TruckOwnerReceiptAccount> getReceiptAccounts() {
        return receiptAccounts;
    }

    public void setReceiptAccounts(List<TruckOwnerReceiptAccount> receiptAccounts) {
        this.receiptAccounts = receiptAccounts;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
