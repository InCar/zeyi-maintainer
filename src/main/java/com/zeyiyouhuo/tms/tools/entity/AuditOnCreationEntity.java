package com.zeyiyouhuo.tms.tools.entity;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by dave on 16/12/1.
 */
@MappedSuperclass
public class AuditOnCreationEntity {
    private Date createTime;

    private long createUserId;

    private String createUsername;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(long createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }
}
