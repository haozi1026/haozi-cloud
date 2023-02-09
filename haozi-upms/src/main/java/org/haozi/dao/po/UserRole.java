package org.haozi.dao.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author auto-generator
 * @since 2023-02-09
 */
@TableName("upms_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private Long roleId;

      private Integer id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserRole{" +
        "userId = " + userId +
        ", roleId = " + roleId +
        ", id = " + id +
        "}";
    }
}
