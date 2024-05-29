package com.yy.manage.service;

import java.util.List;
import com.yy.manage.domain.ThesisInfo;

/**
 * 论文信息Service接口
 * 
 * @author YY
 * @date 2024-05-29
 */
public interface IThesisInfoService 
{
    /**
     * 查询论文信息
     * 
     * @param 编号 论文信息主键
     * @return 论文信息
     */
    public ThesisInfo selectThesisInfoById(Long id);

    /**
     * 查询论文信息列表
     * 
     * @param thesisInfo 论文信息
     * @return 论文信息集合
     */
    public List<ThesisInfo> selectThesisInfoList(ThesisInfo thesisInfo);

    /**
     * 新增论文信息
     * 
     * @param thesisInfo 论文信息
     * @return 结果
     */
    public int insertThesisInfo(ThesisInfo thesisInfo);

    /**
     * 修改论文信息
     * 
     * @param thesisInfo 论文信息
     * @return 结果
     */
    public int updateThesisInfo(ThesisInfo thesisInfo);

    /**
     * 批量删除论文信息
     * 
     * @param 编号s 需要删除的论文信息主键集合
     * @return 结果
     */
    public int deleteThesisInfoByIds(Long[] ids);

    /**
     * 删除论文信息信息
     * 
     * @param 编号 论文信息主键
     * @return 结果
     */
    public int deleteThesisInfoById(Long id);
}
