package com.yy.manage.mapper;

import java.util.List;
import com.yy.manage.domain.ProjectInfo;

/**
 * 项目信息Mapper接口
 * 
 * @author YY
 * @date 2024-05-29
 */
public interface ProjectInfoMapper 
{
    /**
     * 查询项目信息
     * 
     * @param id 项目信息主键
     * @return 项目信息
     */
    public ProjectInfo selectProjectInfoById(Long id);

    /**
     * 查询项目信息列表
     * 
     * @param projectInfo 项目信息
     * @return 项目信息集合
     */
    public List<ProjectInfo> selectProjectInfoList(ProjectInfo projectInfo);

    /**
     * 新增项目信息
     * 
     * @param projectInfo 项目信息
     * @return 结果
     */
    public int insertProjectInfo(ProjectInfo projectInfo);

    /**
     * 修改项目信息
     * 
     * @param projectInfo 项目信息
     * @return 结果
     */
    public int updateProjectInfo(ProjectInfo projectInfo);

    /**
     * 删除项目信息
     * 
     * @param id 项目信息主键
     * @return 结果
     */
    public int deleteProjectInfoById(Long id);

    /**
     * 批量删除项目信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectInfoByIds(Long[] ids);
}
