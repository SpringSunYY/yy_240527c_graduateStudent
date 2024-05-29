package com.yy.manage.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yy.common.annotation.Excel;
import com.yy.common.core.domain.BaseEntity;

/**
 * 项目信息对象 tb_project_info
 * 
 * @author YY
 * @date 2024-05-29
 */
public class ProjectInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 项目号 */
    @Excel(name = "项目号")
    private String itemNumber;

    /** 项目编号 */
    @Excel(name = "项目编号")
    private String projectNumber;

    /** 负责人 */
    @Excel(name = "负责人")
    private Long userId;
    private String userName;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 经费总额 */
    @Excel(name = "经费总额")
    private BigDecimal totalExpenditure;

    /** 余额 */
    @Excel(name = "余额")
    private BigDecimal remainingSum;

    /** 劳务支出 */
    @Excel(name = "劳务支出")
    private BigDecimal expenditure;


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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setItemNumber(String itemNumber) 
    {
        this.itemNumber = itemNumber;
    }

    public String getItemNumber() 
    {
        return itemNumber;
    }
    public void setProjectNumber(String projectNumber) 
    {
        this.projectNumber = projectNumber;
    }

    public String getProjectNumber() 
    {
        return projectNumber;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setTotalExpenditure(BigDecimal totalExpenditure) 
    {
        this.totalExpenditure = totalExpenditure;
    }

    public BigDecimal getTotalExpenditure() 
    {
        return totalExpenditure;
    }
    public void setRemainingSum(BigDecimal remainingSum) 
    {
        this.remainingSum = remainingSum;
    }

    public BigDecimal getRemainingSum() 
    {
        return remainingSum;
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
            .append("name", getName())
            .append("type", getType())
            .append("itemNumber", getItemNumber())
            .append("projectNumber", getProjectNumber())
            .append("userId", getUserId())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("totalExpenditure", getTotalExpenditure())
            .append("remainingSum", getRemainingSum())
            .append("expenditure", getExpenditure())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
