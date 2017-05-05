package com.zeyiyouhuo.tms.tools.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by dave on 16/10/19.
 */
@Entity
@Table(name = "hyr_user")
public class User extends AuditableEntity implements Serializable {
    private static final long serialVersionUID = -6584275272394651321L;

    /**
     * 用户角色
     */
    public enum UserRole {
        ROLE_DRIVER(0), ROLE_FLEET(10), ROLE_FLEET_OWNER(20), ROLE_STAFF(100), ROLE_ADMIN(200);

        private int roleCode;

        UserRole(int roleCode) {
            this.roleCode = roleCode;
        }

        public int getRoleCode() {
            return this.roleCode;
        }

        public static UserRole getRole(int roleCode) {
            for (UserRole role : UserRole.values()) {
                if (role.getRoleCode() == roleCode) {
                    return role;
                }
            }
            return null;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long orgId;

    @JsonIgnore
    private int userType;

    private String username;

    private String realName;

    private String phone;

    private String email;

    private String employeeId;

    private String job;

    private String qq;

    private String weixin;

    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    @Column(columnDefinition = "TINYINT(3)")
    private int sex; // 0 未指定， 1 男性， 2 女性

    @JsonIgnore
    private String password;

    @JsonIgnore
    @Column
    private int status; // 0 未认证, 1 提交认证中, 2 认证成功, 3 认证失败, 10 帐户禁用

    @JsonIgnore
    private String loginToken; // 登录token，用于移动端接口访问

    @JsonIgnore
    private Date tokenRefreshTime;

    @JsonIgnore
    private String getuiCid; // 个推CID

    private Date lastAccessTime;

    private String lastAccessIp;

    private boolean deleted;

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

    public int getUserType() {
        return userType;
    }

    protected void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    protected void setStatus(int status) {
        this.status = status;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getGetuiCid() {
        return getuiCid;
    }

    public void setGetuiCid(String getuiCid) {
        this.getuiCid = getuiCid;
    }

    public Date getTokenRefreshTime() {
        return tokenRefreshTime;
    }

    public void setTokenRefreshTime(Date tokenRefreshTime) {
        this.tokenRefreshTime = tokenRefreshTime;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public String getLastAccessIp() {
        return lastAccessIp;
    }

    public void setLastAccessIp(String lastAccessIp) {
        this.lastAccessIp = lastAccessIp;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public UserRole getUserRole() {
        return UserRole.getRole(this.getUserType());
    }

    public void setUserRole(UserRole role) {
        this.userType = role.getRoleCode();
    }
}
