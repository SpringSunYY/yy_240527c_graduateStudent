package com.yy.manage.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yy.common.annotation.Log;
import com.yy.common.core.controller.BaseController;
import com.yy.common.core.domain.AjaxResult;
import com.yy.common.enums.BusinessType;
import com.yy.manage.domain.StudentMoreInfo;
import com.yy.manage.service.IStudentMoreInfoService;
import com.yy.common.utils.poi.ExcelUtil;
import com.yy.common.core.page.TableDataInfo;

/**
 * 学生更多信息Controller
 * 
 * @author YY
 * @date 2024-05-29
 */
@RestController
@RequestMapping("/manage/studentMoreInfo")
public class StudentMoreInfoController extends BaseController
{
    @Autowired
    private IStudentMoreInfoService studentMoreInfoService;

    /**
     * 查询学生更多信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:studentMoreInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(StudentMoreInfo studentMoreInfo)
    {
        startPage();
        List<StudentMoreInfo> list = studentMoreInfoService.selectStudentMoreInfoList(studentMoreInfo);
        return getDataTable(list);
    }

    /**
     * 导出学生更多信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:studentMoreInfo:export')")
    @Log(title = "学生更多信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StudentMoreInfo studentMoreInfo)
    {
        List<StudentMoreInfo> list = studentMoreInfoService.selectStudentMoreInfoList(studentMoreInfo);
        ExcelUtil<StudentMoreInfo> util = new ExcelUtil<StudentMoreInfo>(StudentMoreInfo.class);
        util.exportExcel(response, list, "学生更多信息数据");
    }

    /**
     * 获取学生更多信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:studentMoreInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(studentMoreInfoService.selectStudentMoreInfoById(id));
    }

    /**
     * 新增学生更多信息
     */
    @PreAuthorize("@ss.hasPermi('manage:studentMoreInfo:add')")
    @Log(title = "学生更多信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StudentMoreInfo studentMoreInfo)
    {
        return toAjax(studentMoreInfoService.insertStudentMoreInfo(studentMoreInfo));
    }

    /**
     * 修改学生更多信息
     */
    @PreAuthorize("@ss.hasPermi('manage:studentMoreInfo:edit')")
    @Log(title = "学生更多信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StudentMoreInfo studentMoreInfo)
    {
        return toAjax(studentMoreInfoService.updateStudentMoreInfo(studentMoreInfo));
    }

    /**
     * 删除学生更多信息
     */
    @PreAuthorize("@ss.hasPermi('manage:studentMoreInfo:remove')")
    @Log(title = "学生更多信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(studentMoreInfoService.deleteStudentMoreInfoByIds(ids));
    }
}
