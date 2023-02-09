package org.haozi.dao.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author auto-generator
 * @since 2023-02-09
 */
@TableName("upms_role_resources")
public class RoleResources implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long roleId;

      private Long resourceId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
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

    @Override
    public String toString() {
        return "RoleResources{" +
        "roleId = " + roleId +
        ", resourceId = " + resourceId +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        "}";
    }
}
