package com.zeyiyouhuo.tms.tools.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 行政区编码表
 * Created by dave on 16/11/15.
 */
@Entity
@Table(name = "hyr_region_code")
public class RegionCode implements Serializable {
    private static final long serialVersionUID = 986996342905816144L;

    /**
     * 行政区类型枚举类
     */
    public enum RegionType {
        PROVINCE(1), CITY(10), DISTRICT(30);

        private int typeCode;

        RegionType(int typeCode) {
            this.typeCode = typeCode;
        }

        public int getTypeCode() {
            return typeCode;
        }

        public static RegionType getType(int typeCode) {
            for (RegionType type : RegionType.values()) {
                if (type.getTypeCode() == typeCode) {
                    return type;
                }
            }

            return null;
        }
    }

    @Id
    private int code;

    private int type; // 1 省或直辖市， 10 地级市或省直管市， 30 区县

    private int parentCode; // 上级编号， 省或直辖市的上级为0

    private String name;

    private String pinyinAbbr; // 中文拼音简称

    private String phoneCode; // 电话区号

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getType() {
        return type;
    }

    protected void setType(int type) {
        this.type = type;
    }

    public int getParentCode() {
        return parentCode;
    }

    public void setParentCode(int parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyinAbbr() {
        return pinyinAbbr;
    }

    public void setPinyinAbbr(String pinyinAbbr) {
        this.pinyinAbbr = pinyinAbbr;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public RegionType getRegionType() {
        return RegionType.getType(type);
    }

    public void setRegionType(RegionType regionType) {
        this.type = regionType.getTypeCode();
    }
}
