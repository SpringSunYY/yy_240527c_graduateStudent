package com.yy.manage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yy.common.annotation.Excel;
import com.yy.common.core.domain.BaseEntity;

/**
 * 学生更多信息对象 tb_student__more_info
 * 
 * @author YY
 * @date 2024-05-29
 */
public class StudentMoreInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 紧急联系人 */
    @Excel(name = "紧急联系人")
    private String emergencyContact;

    /** 号码 */
    @Excel(name = "号码")
    private String emergencyContactPhone;

    /** 类型 */
    @Excel(name = "类型")
    private String graduateType;

    /** 就读年限 */
    @Excel(name = "就读年限")
    private String yearsOfStudy;

    /** 延期 */
    @Excel(name = "延期")
    private String isPostpone;

    /** 学生 */
    @Excel(name = "学生")
    private Long userId;
    private String userName;

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
    public void setEmergencyContact(String emergencyContact) 
    {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyContact() 
    {
        return emergencyContact;
    }
    public void setEmergencyContactPhone(String emergencyContactPhone) 
    {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getEmergencyContactPhone() 
    {
        return emergencyContactPhone;
    }
    public void setGraduateType(String graduateType) 
    {
        this.graduateType = graduateType;
    }

    public String getGraduateType() 
    {
        return graduateType;
    }
    public void setYearsOfStudy(String yearsOfStudy) 
    {
        this.yearsOfStudy = yearsOfStudy;
    }

    public String getYearsOfStudy() 
    {
        return yearsOfStudy;
    }
    public void setIsPostpone(String isPostpone) 
    {
        this.isPostpone = isPostpone;
    }

    public String getIsPostpone() 
    {
        return isPostpone;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("emergencyContact", getEmergencyContact())
            .append("emergencyContactPhone", getEmergencyContactPhone())
            .append("graduateType", getGraduateType())
            .append("yearsOfStudy", getYearsOfStudy())
            .append("isPostpone", getIsPostpone())
            .append("userId", getUserId())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
