package com.zhongdihang.bankdispatch.modular.controller;

import com.zhongdihang.bankdispatch.core.page.PageFactory;
import com.zhongdihang.bankdispatch.modular.domain.File;
import com.zhongdihang.bankdispatch.modular.domain.Guaranty;
import com.zhongdihang.bankdispatch.modular.service.FileService;
import com.zhongdihang.bankdispatch.modular.service.GuarantyService;
import com.zhongdihang.bankdispatch.modular.service.dto.GuarantyDto;
import com.zhongdihang.bankdispatch.modular.service.dto.GuarantyQueryDto;
import com.zhongdihang.bankdispatch.modular.service.form.GuarantyForm;
import com.zhongdihang.bankdispatch.rest.ResultModel;
import com.zhongdihang.bankdispatch.rest.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/11.
 */
@RestController
@RequestMapping("/guaranty")
@Api(description = "抵押物控制器")
public class GuarantyController {
    @Autowired
    private GuarantyService guarantyService;
    @Autowired
    private FileService fileService;

    @ApiOperation("删除抵押物")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultModel isDelete(@RequestParam("id") Long id) {
        guarantyService.deleteGuaranty(id);
        return ResultModel.ok();
    }

    @ApiOperation("添加抵押物")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultModel addGuaranty(@RequestBody GuarantyForm guarantyForm,Long fileId){
        Guaranty guaranty = new Guaranty();
        File file = fileService.findFile(fileId);
        if (file!=null){
            guaranty.setFile(file);
        }else {
            return ResultModel.error(ResultStatus.FAILURE,"没有匹配的产证信息");
        }

//        guaranty.setDispatchNo(guarantyForm.getDispatchNo());
        guaranty.setCreateTime(new Date());
        guaranty.setContactName(guarantyForm.getClientName());
        guaranty.setContactPhone(guarantyForm.getClientTelephone());
        guarantyService.saveGuaranty(guaranty);
        return ResultModel.ok();
    }

    /*@ApiOperation("查找抵押物")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "condition", value = "抵押物编号", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "Date", name = "startDate", value = "注册开始日期", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "Date", name = "endDate", value = "注册结束日期", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "pageIndex", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "pageSize", value = "每页数量", required = true)})
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultModel findGuaranty(String condition, Date startDate, Date endDate, Integer pageIndex, Integer pageSize){
        GuarantyQueryDto guarantyQueryDto = new GuarantyQueryDto()
                .setCondition(condition)
                .setStartDate(startDate)
                .setEndDate(endDate);
        PageRequest pageRequest = new PageFactory().defaultPage();
        Page<Guaranty> guaranties = guarantyService.findGuaranty(guarantyQueryDto, pageRequest);
        Page<GuarantyDto> guarantyDtos = guaranties.map(new GuarantyDto());
        return ResultModel.ok(guarantyDtos);
    }*/

    @ApiOperation("编辑抵押物信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultModel updateGuaranty(GuarantyForm guarantyForm){
        File file = fileService.findFile(guarantyForm.getFileId());
        Guaranty guaranty = guarantyService.getGuaranty(guarantyForm.getId());
        if(file!=null){
            guaranty.setFile(file);
        }else {
            return ResultModel.error(ResultStatus.FAILURE,"无法找到抵押物信息");
        }
        guaranty.setUpdateTime(new Date());
        guaranty.setContactName(guarantyForm.getHandlerName());
        guaranty.setContactPhone(guarantyForm.getHandlerTelephone());
        guarantyService.saveGuaranty(guaranty);
        return ResultModel.ok();
    }
}
