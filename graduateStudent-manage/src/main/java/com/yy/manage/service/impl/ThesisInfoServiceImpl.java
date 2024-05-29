package com.yy.manage.service.impl;

import java.util.Date;
import java.util.List;

import com.yy.common.annotation.DataScope;
import com.yy.common.core.domain.entity.SysUser;
import com.yy.common.utils.SecurityUtils;
import com.yy.common.utils.StringUtils;
import com.yy.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yy.manage.mapper.ThesisInfoMapper;
import com.yy.manage.domain.ThesisInfo;
import com.yy.manage.service.IThesisInfoService;

/**
 * 论文信息Service业务层处理
 * 
 * @author YY
 * @date 2024-05-29
 */
@Service
public class ThesisInfoServiceImpl implements IThesisInfoService 
{
    @Autowired
    private ThesisInfoMapper thesisInfoMapper;

    @Autowired
    private ISysUserService userService;

    /**
     * 查询论文信息
     * 
     * @param 编号 论文信息主键
     * @return 论文信息
     */
    @Override
    public ThesisInfo selectThesisInfoById(Long id)
    {
        return thesisInfoMapper.selectThesisInfoById(id);
    }

    /**
     * 查询论文信息列表
     * 
     * @param thesisInfo 论文信息
     * @return 论文信息
     */
    @DataScope(userAlias = "tb_thesis_info")
    @Override
    public List<ThesisInfo> selectThesisInfoList(ThesisInfo thesisInfo)
    {
        List<ThesisInfo> thesisInfos = thesisInfoMapper.selectThesisInfoList(thesisInfo);
        for (ThesisInfo info : thesisInfos) {
            //获取基础值，判断是否为空
            SysUser sysUser = userService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(sysUser)) {
                info.setUserName(sysUser.getNickName());
            }
        }
        return thesisInfos;
    }

    /**
     * 新增论文信息
     * 
     * @param thesisInfo 论文信息
     * @return 结果
     */
    @Override
    public int insertThesisInfo(ThesisInfo thesisInfo)
    {
        thesisInfo.setCreateBy(SecurityUtils.getUsername());
        thesisInfo.setCreateTime(new Date());
        return thesisInfoMapper.insertThesisInfo(thesisInfo);
    }

    /**
     * 修改论文信息
     * 
     * @param thesisInfo 论文信息
     * @return 结果
     */
    @Override
    public int updateThesisInfo(ThesisInfo thesisInfo)
    {
        thesisInfo.setUpdateTime(new Date());
        return thesisInfoMapper.updateThesisInfo(thesisInfo);
    }

    /**
     * 批量删除论文信息
     * 
     * @param 编号s 需要删除的论文信息主键
     * @return 结果
     */
    @Override
    public int deleteThesisInfoByIds(Long[] ids)
    {
        return thesisInfoMapper.deleteThesisInfoByIds(ids);
    }

    /**
     * 删除论文信息信息
     * 
     * @param id 论文信息主键
     * @return 结果
     */
    @Override
    public int deleteThesisInfoById(Long id)
    {
        return thesisInfoMapper.deleteThesisInfoById(id);
    }
}
