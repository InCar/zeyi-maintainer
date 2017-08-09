package com.zeyiyouhuo.tms.tools.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dave at 2017/8/8 上午10:04.
 * 车主收款帐户信息
 */
@Entity
@Table(name = "hyr_truck_owner_receipt_account")
public class TruckOwnerReceiptAccount extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long ownerId;

    private String payee; // 收款人，默认帐户

    private String bankCardNo; // 银行卡号

    private String bankName; // 开户行名称

    private long bankCardPic; // 银行卡照片

    public TruckOwnerReceiptAccount(TruckOwner owner) {
        ownerId = owner.getId();
        payee = owner.getPayee();
        bankCardNo = owner.getBankCardNo();
        bankName = owner.getBankName();
        bankCardPic = owner.getBankCardPic();
        setCreateTime(owner.getCreateTime());
        setUpdateTime(owner.getUpdateTime());
        setCreateUserId(owner.getCreateUserId());
        setCreateUsername(owner.getCreateUsername());
        setUpdateUserId(owner.getUpdateUserId());
        setUpdateUsername(owner.getUpdateUsername());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
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
}
