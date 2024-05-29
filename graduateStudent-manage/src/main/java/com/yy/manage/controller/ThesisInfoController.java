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
import com.yy.manage.domain.ThesisInfo;
import com.yy.manage.service.IThesisInfoService;
import com.yy.common.utils.poi.ExcelUtil;
import com.yy.common.core.page.TableDataInfo;

/**
 * 论文信息Controller
 * 
 * @author YY
 * @date 2024-05-29
 */
@RestController
@RequestMapping("/manage/thesisInfo")
public class ThesisInfoController extends BaseController
{
    @Autowired
    private IThesisInfoService thesisInfoService;

    /**
     * 查询论文信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:thesisInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(ThesisInfo thesisInfo)
    {
        startPage();
        List<ThesisInfo> list = thesisInfoService.selectThesisInfoList(thesisInfo);
        return getDataTable(list);
    }

    /**
     * 导出论文信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:thesisInfo:export')")
    @Log(title = "论文信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ThesisInfo thesisInfo)
    {
        List<ThesisInfo> list = thesisInfoService.selectThesisInfoList(thesisInfo);
        ExcelUtil<ThesisInfo> util = new ExcelUtil<ThesisInfo>(ThesisInfo.class);
        util.exportExcel(response, list, "论文信息数据");
    }

    /**
     * 获取论文信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:thesisInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(thesisInfoService.selectThesisInfoById(id));
    }

    /**
     * 新增论文信息
     */
    @PreAuthorize("@ss.hasPermi('manage:thesisInfo:add')")
    @Log(title = "论文信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ThesisInfo thesisInfo)
    {
        return toAjax(thesisInfoService.insertThesisInfo(thesisInfo));
    }

    /**
     * 修改论文信息
     */
    @PreAuthorize("@ss.hasPermi('manage:thesisInfo:edit')")
    @Log(title = "论文信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ThesisInfo thesisInfo)
    {
        return toAjax(thesisInfoService.updateThesisInfo(thesisInfo));
    }

    /**
     * 删除论文信息
     */
    @PreAuthorize("@ss.hasPermi('manage:thesisInfo:remove')")
    @Log(title = "论文信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(thesisInfoService.deleteThesisInfoByIds(ids));
    }
}
