package com.yy.manage.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.yy.common.annotation.DataScope;
import com.yy.common.core.domain.entity.SysUser;
import com.yy.common.utils.DateUtils;
import com.yy.common.utils.SecurityUtils;
import com.yy.common.utils.StringUtils;
import com.yy.manage.domain.ProjectInfo;
import com.yy.manage.service.IProjectInfoService;
import com.yy.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yy.manage.mapper.ProjectExpenditureInfoMapper;
import com.yy.manage.domain.ProjectExpenditureInfo;
import com.yy.manage.service.IProjectExpenditureInfoService;

/**
 * 支出信息Service业务层处理
 *
 * @author YY
 * @date 2024-05-29
 */
@Service
public class ProjectExpenditureInfoServiceImpl implements IProjectExpenditureInfoService {
    @Autowired
    private ProjectExpenditureInfoMapper projectExpenditureInfoMapper;

    @Autowired
    private IProjectInfoService projectInfoService;

    @Autowired
    private ISysUserService userService;

    /**
     * 查询支出信息
     *
     * @param id 支出信息主键
     * @return 支出信息
     */
    @Override
    public ProjectExpenditureInfo selectProjectExpenditureInfoById(Long id) {
        return projectExpenditureInfoMapper.selectProjectExpenditureInfoById(id);
    }

    /**
     * 查询支出信息列表
     *
     * @param projectExpenditureInfo 支出信息
     * @return 支出信息
     */
    @DataScope(userAlias = "tb_project_expenditure_info")
    @Override
    public List<ProjectExpenditureInfo> selectProjectExpenditureInfoList(ProjectExpenditureInfo projectExpenditureInfo) {
        List<ProjectExpenditureInfo> projectExpenditureInfos = projectExpenditureInfoMapper.selectProjectExpenditureInfoList(projectExpenditureInfo);
        for (ProjectExpenditureInfo info : projectExpenditureInfos) {
            //获取基础值，判断是否为空
            SysUser sysUser = userService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(sysUser)) {
                info.setUserName(sysUser.getNickName());
            }
            ProjectInfo projectInfo = projectInfoService.selectProjectInfoById(info.getProjectId());
            if (StringUtils.isNotNull(projectInfo)) {
                info.setProjectName(projectInfo.getName());
            }
        }
        return projectExpenditureInfos;
    }

    /**
     * 新增支出信息
     *
     * @param projectExpenditureInfo 支出信息
     * @return 结果
     */
    @Override
    public int insertProjectExpenditureInfo(ProjectExpenditureInfo projectExpenditureInfo) {
        //查询项目
        ProjectInfo projectInfo = projectInfoService.selectProjectInfoById(projectExpenditureInfo.getProjectId());
        if (StringUtils.isNull(projectInfo)) {
            throw new RuntimeException("该项目不存在！！！");
        }
        //得到经费
        BigDecimal expenditure = projectInfo.getRemainingSum().subtract(projectExpenditureInfo.getExpenditure());
        if (expenditure.compareTo(BigDecimal.ZERO)<0) {
            throw new RuntimeException("余额不足！！！");
        }
        projectInfo.setRemainingSum(expenditure);
        //计算已花费
        projectInfo.setExpenditure(projectInfo.getTotalExpenditure().subtract(expenditure));
        projectInfoService.updateProjectInfo(projectInfo);

        projectExpenditureInfo.setCreateBy(SecurityUtils.getUsername());
        projectExpenditureInfo.setCreateTime(DateUtils.getNowDate());
        return projectExpenditureInfoMapper.insertProjectExpenditureInfo(projectExpenditureInfo);
    }

    /**
     * 修改支出信息
     *
     * @param projectExpenditureInfo 支出信息
     * @return 结果
     */
    @Override
    public int updateProjectExpenditureInfo(ProjectExpenditureInfo projectExpenditureInfo) {
        projectExpenditureInfo.setUpdateTime(DateUtils.getNowDate());
        return projectExpenditureInfoMapper.updateProjectExpenditureInfo(projectExpenditureInfo);
    }

    /**
     * 批量删除支出信息
     *
     * @param ids 需要删除的支出信息主键
     * @return 结果
     */
    @Override
    public int deleteProjectExpenditureInfoByIds(Long[] ids) {
        return projectExpenditureInfoMapper.deleteProjectExpenditureInfoByIds(ids);
    }

    /**
     * 删除支出信息信息
     *
     * @param id 支出信息主键
     * @return 结果
     */
    @Override
    public int deleteProjectExpenditureInfoById(Long id) {
        return projectExpenditureInfoMapper.deleteProjectExpenditureInfoById(id);
    }
}
