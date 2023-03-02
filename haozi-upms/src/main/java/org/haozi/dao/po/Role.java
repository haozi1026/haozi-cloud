package org.haozi.dao.po;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import cn.zhxu.bs.bean.SearchBean;

/**
 * <p>
 *
 * </p>
 *
 * @author auto-generator
 * @since 2023-03-02
 */
@TableName("upms_role")
@SearchBean(tables = "upms_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long roleId;


    private String roleName;


    private LocalDateTime createTime;


    private LocalDateTime updateTime;


    private String roleFlag;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(String roleFlag) {
        this.roleFlag = roleFlag;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId = " + roleId +
                ", roleName = " + roleName +
                ", createTime = " + createTime +
                ", updateTime = " + updateTime +
                ", roleFlag = " + roleFlag +
                "}";
    }
}
