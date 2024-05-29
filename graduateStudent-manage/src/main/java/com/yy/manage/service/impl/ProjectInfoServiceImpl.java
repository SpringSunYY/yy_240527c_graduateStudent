package com.yy.manage.service.impl;

import java.util.List;

import com.yy.common.annotation.DataScope;
import com.yy.common.core.domain.entity.SysUser;
import com.yy.common.utils.DateUtils;
import com.yy.common.utils.SecurityUtils;
import com.yy.common.utils.StringUtils;
import com.yy.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yy.manage.mapper.ProjectInfoMapper;
import com.yy.manage.domain.ProjectInfo;
import com.yy.manage.service.IProjectInfoService;

/**
 * 项目信息Service业务层处理
 *
 * @author YY
 * @date 2024-05-29
 */
@Service
public class ProjectInfoServiceImpl implements IProjectInfoService {
    @Autowired
    private ProjectInfoMapper projectInfoMapper;
    @Autowired
    private ISysUserService userService;


    /**
     * 查询项目信息
     *
     * @param id 项目信息主键
     * @return 项目信息
     */
    @Override
    public ProjectInfo selectProjectInfoById(Long id) {
        return projectInfoMapper.selectProjectInfoById(id);
    }


    @Override
    public List<ProjectInfo> selectProjectInfoList(ProjectInfo projectInfo) {
        List<ProjectInfo> projectInfos = projectInfoMapper.selectProjectInfoList(projectInfo);
        for (ProjectInfo info : projectInfos) {
            //获取基础值，判断是否为空
            SysUser sysUser = userService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(sysUser)) {
                info.setUserName(sysUser.getNickName());
            }
        }
        return projectInfos;
    }

    /**
     * 新增项目信息
     *
     * @param projectInfo 项目信息
     * @return 结果
     */
    @Override
    public int insertProjectInfo(ProjectInfo projectInfo) {
        //判断是否有此项目
        ProjectInfo info = new ProjectInfo();
        info.setName(projectInfo.getName());
        List<ProjectInfo> projectInfos = projectInfoMapper.selectProjectInfoList(info);
        if (StringUtils.isNotEmpty(projectInfos)) {
            throw new RuntimeException("此项目已经存在！！！");
        }

        projectInfo.setCreateBy(SecurityUtils.getUsername());
        projectInfo.setCreateTime(DateUtils.getNowDate());
        return projectInfoMapper.insertProjectInfo(projectInfo);
    }

    /**
     * 修改项目信息
     *
     * @param projectInfo 项目信息
     * @return 结果
     */
    @Override
    public int updateProjectInfo(ProjectInfo projectInfo) {
        projectInfo.setUpdateTime(DateUtils.getNowDate());
        return projectInfoMapper.updateProjectInfo(projectInfo);
    }

    /**
     * 批量删除项目信息
     *
     * @param ids 需要删除的项目信息主键
     * @return 结果
     */
    @Override
    public int deleteProjectInfoByIds(Long[] ids) {
        return projectInfoMapper.deleteProjectInfoByIds(ids);
    }

    /**
     * 删除项目信息信息
     *
     * @param id 项目信息主键
     * @return 结果
     */
    @Override
    public int deleteProjectInfoById(Long id) {
        return projectInfoMapper.deleteProjectInfoById(id);
    }
}
