package com.zeyiyouhuo.tms.tools.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dave on 16/10/24.
 */
@Entity
@Table(name = "hyr_org")
public class Org extends AuditableEntity implements Serializable {
    private static final long serialVersionUID = 8180160018835053348L;

    /**
     * 机构类型枚举类
     */
    public enum OrgType {
        ZEYI_ORG(1), PARTNER_ORG(10), FLEET_ORG(20);

        private int typeCode;

        OrgType(int typeCode) {
            this.typeCode = typeCode;
        }

        public int getTypeCode() {
            return typeCode;
        }

        public static OrgType getType(int typeCode) {
            for (OrgType type : OrgType.values()) {
                if (type.getTypeCode() == typeCode) {
                    return type;
                }
            }

            return null;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String orgCode;

    private String rootCode;

    private long rootId;

    private long parentId;

    @JsonIgnore
    @Column(columnDefinition = "TINYINT(3)")
    private int orgType;

    private String name;

    private int provinceCode;

    private String province;

    private int cityCode;

    private String city;

    private String address;

    @JsonIgnore
    private int leftOrder;

    @JsonIgnore
    private int rightOrder;

    private boolean deleted;

    // 本机构是否是org的子机构
    public boolean isSubOf(Org org) {
        return rootId == org.getRootId() && leftOrder > org.getLeftOrder() && rightOrder < org.getRightOrder();
    }

    // 本机构是否是org的上级机构
    public boolean isSupOf(Org org) {
        return rootId == org.getRootId() && leftOrder < org.getLeftOrder() && rightOrder > org.getRightOrder();
    }

    // 本机构是否是org的子机构，或者是org机构本身
    public boolean isSelfOrSubOf(Org org) {
        return rootId == org.getRootId() && leftOrder >= org.getLeftOrder() && rightOrder <= org.getRightOrder();
    }

    // 本机构是否是org的上级机构，或者是org机构本身
    public boolean isSelfOrSupOf(Org org) {
        return rootId == org.getRootId() && leftOrder <= org.getLeftOrder() && rightOrder >= org.getRightOrder();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getRootCode() {
        return rootCode;
    }

    public void setRootCode(String rootCode) {
        this.rootCode = rootCode;
    }

    public long getRootId() {
        return rootId;
    }

    public void setRootId(long rootId) {
        this.rootId = rootId;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public int getOrgType() {
        return orgType;
    }

    protected void setOrgType(int orgType) {
        this.orgType = orgType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getLeftOrder() {
        return leftOrder;
    }

    public void setLeftOrder(int leftOrder) {
        this.leftOrder = leftOrder;
    }

    public int getRightOrder() {
        return rightOrder;
    }

    public void setRightOrder(int rightOrder) {
        this.rightOrder = rightOrder;
    }

    public OrgType getType() {
        return OrgType.getType(orgType);
    }

    public void setType(OrgType type) {
        orgType = type.getTypeCode();
    }
}
