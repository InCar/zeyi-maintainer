package com.zeyiyouhuo.tms.tools.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by dave on 17/5/9.
 */
@Entity
@Table(name = "hyr_site")
public class Site extends AuditableEntity implements Serializable {
    private static final long serialVersionUID = 8180160018835053348L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String siteCode;

    private String name;

    private String nameAbbr;

    private int orderNum;

    private long orgId;

    private long partnerId;

    private int provinceCode;

    private String provinceName;

    private int cityCode;

    private String cityName;

    private String address;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private BigDecimal lngMin;

    private BigDecimal lngMax;

    private BigDecimal latMin;

    private BigDecimal latMax;

    private String fence;

    private boolean fenceRounded;

    private boolean deleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
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

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(long partnerId) {
        this.partnerId = partnerId;
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

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLngMin() {
        return lngMin;
    }

    public void setLngMin(BigDecimal lngMin) {
        this.lngMin = lngMin;
    }

    public BigDecimal getLngMax() {
        return lngMax;
    }

    public void setLngMax(BigDecimal lngMax) {
        this.lngMax = lngMax;
    }

    public BigDecimal getLatMin() {
        return latMin;
    }

    public void setLatMin(BigDecimal latMin) {
        this.latMin = latMin;
    }

    public BigDecimal getLatMax() {
        return latMax;
    }

    public void setLatMax(BigDecimal latMax) {
        this.latMax = latMax;
    }

    public String getFence() {
        return fence;
    }

    public void setFence(String fence) {
        this.fence = fence;
    }

    public boolean isFenceRounded() {
        return fenceRounded;
    }

    public void setFenceRounded(boolean fenceRounded) {
        this.fenceRounded = fenceRounded;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}