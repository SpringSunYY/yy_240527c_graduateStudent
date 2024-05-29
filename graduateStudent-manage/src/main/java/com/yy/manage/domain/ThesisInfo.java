package com.yy.manage.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yy.common.annotation.Excel;
import com.yy.common.core.domain.BaseEntity;

/**
 * 论文信息对象 tb_thesis_info
 *
 * @author YY
 * @date 2024-05-29
 */
public class ThesisInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long id;

    /** 期刊 */
    @Excel(name = "期刊")
    private String journal;

    /** 题目 */
    @Excel(name = "题目")
    private String title;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 论文编号 */
    @Excel(name = "论文编号")
    private String thesisNumber;

    /** 导师类别 */
    @Excel(name = "导师类别")
    private String tutorType;

    /** 作者 */
    @Excel(name = "作者")
    private Long userId;
    private String userName;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

    /** 发表级别 */
    @Excel(name = "发表级别")
    private String publishLevel;

    /** 依托项目 */
    @Excel(name = "依托项目")
    private String relyProject;

    /** 论文文件 */
    @Excel(name = "论文文件")
    private String file;

    /** 页码 */
    @Excel(name = "页码")
    private String pageNumber;

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
    public void setJournal(String journal)
    {
        this.journal = journal;
    }

    public String getJournal()
    {
        return journal;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setThesisNumber(String thesisNumber)
    {
        this.thesisNumber = thesisNumber;
    }

    public String getThesisNumber()
    {
        return thesisNumber;
    }
    public void setTutorType(String tutorType)
    {
        this.tutorType = tutorType;
    }

    public String getTutorType()
    {
        return tutorType;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setPublishTime(Date publishTime)
    {
        this.publishTime = publishTime;
    }

    public Date getPublishTime()
    {
        return publishTime;
    }
    public void setPublishLevel(String publishLevel)
    {
        this.publishLevel = publishLevel;
    }

    public String getPublishLevel()
    {
        return publishLevel;
    }
    public void setRelyProject(String relyProject)
    {
        this.relyProject = relyProject;
    }

    public String getRelyProject()
    {
        return relyProject;
    }
    public void setFile(String file)
    {
        this.file = file;
    }

    public String getFile()
    {
        return file;
    }
    public void setPageNumber(String pageNumber)
    {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber()
    {
        return pageNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("journal", getJournal())
                .append("title", getTitle())
                .append("type", getType())
                .append("thesisNumber", getThesisNumber())
                .append("tutorType", getTutorType())
                .append("userId", getUserId())
                .append("publishTime", getPublishTime())
                .append("publishLevel", getPublishLevel())
                .append("relyProject", getRelyProject())
                .append("file", getFile())
                .append("pageNumber", getPageNumber())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
