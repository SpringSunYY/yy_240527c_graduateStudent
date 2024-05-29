package com.yy.manage.service.impl;

import java.util.List;

import com.yy.common.annotation.DataScope;
import com.yy.common.core.domain.entity.SysUser;
import com.yy.common.utils.DateUtils;
import com.yy.common.utils.SecurityUtils;
import com.yy.common.utils.StringUtils;
import com.yy.common.utils.spring.SpringUtils;
import com.yy.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yy.manage.mapper.StudentMoreInfoMapper;
import com.yy.manage.domain.StudentMoreInfo;
import com.yy.manage.service.IStudentMoreInfoService;

/**
 * 学生更多信息Service业务层处理
 *
 * @author YY
 * @date 2024-05-29
 */
@Service
public class StudentMoreInfoServiceImpl implements IStudentMoreInfoService {
    @Autowired
    private StudentMoreInfoMapper studentMoreInfoMapper;

    @Autowired
    private ISysUserService userService;

    /**
     * 查询学生更多信息
     *
     * @param id 学生更多信息主键
     * @return 学生更多信息
     */
    @Override
    public StudentMoreInfo selectStudentMoreInfoById(Long id) {
        return studentMoreInfoMapper.selectStudentMoreInfoById(id);
    }

    /**
     * 查询学生更多信息列表
     *
     * @param studentMoreInfo 学生更多信息
     * @return 学生更多信息
     */
    @DataScope(userAlias = "tb_student__more_info")
    @Override
    public List<StudentMoreInfo> selectStudentMoreInfoList(StudentMoreInfo studentMoreInfo) {
        List<StudentMoreInfo> studentMoreInfos = studentMoreInfoMapper.selectStudentMoreInfoList(studentMoreInfo);
        for (StudentMoreInfo info : studentMoreInfos) {
            //获取基础值，判断是否为空
            SysUser sysUser = userService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(sysUser)) {
                info.setUserName(sysUser.getNickName());
            }
        }
        return studentMoreInfos;
    }

    /**
     * 新增学生更多信息
     *
     * @param studentMoreInfo 学生更多信息
     * @return 结果
     */
    @Override
    public int insertStudentMoreInfo(StudentMoreInfo studentMoreInfo) {
        if (StringUtils.isNull(studentMoreInfo.getUserId())) {
            studentMoreInfo.setUserId(SecurityUtils.getUserId());
        }

        StudentMoreInfo moreInfo = new StudentMoreInfo();
        moreInfo.setUserId(studentMoreInfo.getUserId());
        List<StudentMoreInfo> studentMoreInfos = studentMoreInfoMapper.selectStudentMoreInfoList(moreInfo);
        if (StringUtils.isNotEmpty(studentMoreInfos)) {
            throw new RuntimeException("只可以用于一个信息！！！");
        }

        studentMoreInfo.setCreateBy(SecurityUtils.getUsername());
        studentMoreInfo.setCreateTime(DateUtils.getNowDate());
        return studentMoreInfoMapper.insertStudentMoreInfo(studentMoreInfo);
    }

    /**
     * 修改学生更多信息
     *
     * @param studentMoreInfo 学生更多信息
     * @return 结果
     */
    @Override
    public int updateStudentMoreInfo(StudentMoreInfo studentMoreInfo) {
        studentMoreInfo.setUpdateTime(DateUtils.getNowDate());
        return studentMoreInfoMapper.updateStudentMoreInfo(studentMoreInfo);
    }

    /**
     * 批量删除学生更多信息
     *
     * @param ids 需要删除的学生更多信息主键
     * @return 结果
     */
    @Override
    public int deleteStudentMoreInfoByIds(Long[] ids) {
        return studentMoreInfoMapper.deleteStudentMoreInfoByIds(ids);
    }

    /**
     * 删除学生更多信息信息
     *
     * @param id 学生更多信息主键
     * @return 结果
     */
    @Override
    public int deleteStudentMoreInfoById(Long id) {
        return studentMoreInfoMapper.deleteStudentMoreInfoById(id);
    }
}
