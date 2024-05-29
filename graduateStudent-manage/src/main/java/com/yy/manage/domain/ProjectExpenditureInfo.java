package com.yy.manage.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yy.common.annotation.Excel;
import com.yy.common.core.domain.BaseEntity;

/**
 * 支出信息对象 tb_project_expenditure_info
 * 
 * @author YY
 * @date 2024-05-29
 */
public class ProjectExpenditureInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long id;

    /** 项目 */
    @Excel(name = "项目")
    private Long projectId;
    private String projectName;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 使用人 */
    @Excel(name = "使用人")
    private Long userId;
    private String userName;

    /** 劳务支出 */
    @Excel(name = "劳务支出")
    private BigDecimal expenditure;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setExpenditure(BigDecimal expenditure) 
    {
        this.expenditure = expenditure;
    }

    public BigDecimal getExpenditure() 
    {
        return expenditure;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectId", getProjectId())
            .append("name", getName())
            .append("userId", getUserId())
            .append("expenditure", getExpenditure())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
