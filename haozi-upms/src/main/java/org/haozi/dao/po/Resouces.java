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
@TableName("upms_resouces")
public class Resouces implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long resourcesId;

    /**
     * 资源名
     */
    private String resourceName;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 类型 类型0:按钮 1：菜单
     */
    private String type;

    /**
     * 删除标识
     */
    private String isDel;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 资源标识
     */
    private String resourceFlag;

    public Long getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Long resourcesId) {
        this.resourcesId = resourcesId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getResourceFlag() {
        return resourceFlag;
    }

    public void setResourceFlag(String resourceFlag) {
        this.resourceFlag = resourceFlag;
    }

    @Override
    public String toString() {
        return "Resouces{" +
        "resourcesId = " + resourcesId +
        ", resourceName = " + resourceName +
        ", parentId = " + parentId +
        ", type = " + type +
        ", isDel = " + isDel +
        ", url = " + url +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        ", resourceFlag = " + resourceFlag +
        "}";
    }
}
