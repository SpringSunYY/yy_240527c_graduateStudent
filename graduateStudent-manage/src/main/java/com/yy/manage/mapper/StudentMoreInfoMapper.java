package com.yy.manage.mapper;

import java.util.List;
import com.yy.manage.domain.StudentMoreInfo;

/**
 * 学生更多信息Mapper接口
 * 
 * @author YY
 * @date 2024-05-29
 */
public interface StudentMoreInfoMapper 
{
    /**
     * 查询学生更多信息
     * 
     * @param id 学生更多信息主键
     * @return 学生更多信息
     */
    public StudentMoreInfo selectStudentMoreInfoById(Long id);

    /**
     * 查询学生更多信息列表
     * 
     * @param studentMoreInfo 学生更多信息
     * @return 学生更多信息集合
     */
    public List<StudentMoreInfo> selectStudentMoreInfoList(StudentMoreInfo studentMoreInfo);

    /**
     * 新增学生更多信息
     * 
     * @param studentMoreInfo 学生更多信息
     * @return 结果
     */
    public int insertStudentMoreInfo(StudentMoreInfo studentMoreInfo);

    /**
     * 修改学生更多信息
     * 
     * @param studentMoreInfo 学生更多信息
     * @return 结果
     */
    public int updateStudentMoreInfo(StudentMoreInfo studentMoreInfo);

    /**
     * 删除学生更多信息
     * 
     * @param id 学生更多信息主键
     * @return 结果
     */
    public int deleteStudentMoreInfoById(Long id);

    /**
     * 批量删除学生更多信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStudentMoreInfoByIds(Long[] ids);
}
