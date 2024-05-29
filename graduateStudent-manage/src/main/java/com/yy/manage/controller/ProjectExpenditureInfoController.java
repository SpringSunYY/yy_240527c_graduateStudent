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
import com.yy.manage.domain.ProjectExpenditureInfo;
import com.yy.manage.service.IProjectExpenditureInfoService;
import com.yy.common.utils.poi.ExcelUtil;
import com.yy.common.core.page.TableDataInfo;

/**
 * 支出信息Controller
 * 
 * @author YY
 * @date 2024-05-29
 */
@RestController
@RequestMapping("/manage/projectExpenditureInfo")
public class ProjectExpenditureInfoController extends BaseController
{
    @Autowired
    private IProjectExpenditureInfoService projectExpenditureInfoService;

    /**
     * 查询支出信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:projectExpenditureInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectExpenditureInfo projectExpenditureInfo)
    {
        startPage();
        List<ProjectExpenditureInfo> list = projectExpenditureInfoService.selectProjectExpenditureInfoList(projectExpenditureInfo);
        return getDataTable(list);
    }

    /**
     * 导出支出信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:projectExpenditureInfo:export')")
    @Log(title = "支出信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectExpenditureInfo projectExpenditureInfo)
    {
        List<ProjectExpenditureInfo> list = projectExpenditureInfoService.selectProjectExpenditureInfoList(projectExpenditureInfo);
        ExcelUtil<ProjectExpenditureInfo> util = new ExcelUtil<ProjectExpenditureInfo>(ProjectExpenditureInfo.class);
        util.exportExcel(response, list, "支出信息数据");
    }

    /**
     * 获取支出信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:projectExpenditureInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(projectExpenditureInfoService.selectProjectExpenditureInfoById(id));
    }

    /**
     * 新增支出信息
     */
    @PreAuthorize("@ss.hasPermi('manage:projectExpenditureInfo:add')")
    @Log(title = "支出信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectExpenditureInfo projectExpenditureInfo)
    {
        return toAjax(projectExpenditureInfoService.insertProjectExpenditureInfo(projectExpenditureInfo));
    }

    /**
     * 修改支出信息
     */
    @PreAuthorize("@ss.hasPermi('manage:projectExpenditureInfo:edit')")
    @Log(title = "支出信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectExpenditureInfo projectExpenditureInfo)
    {
        return toAjax(projectExpenditureInfoService.updateProjectExpenditureInfo(projectExpenditureInfo));
    }

    /**
     * 删除支出信息
     */
    @PreAuthorize("@ss.hasPermi('manage:projectExpenditureInfo:remove')")
    @Log(title = "支出信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(projectExpenditureInfoService.deleteProjectExpenditureInfoByIds(ids));
    }
}
