package com.zhongdihang.bankdispatch.modular.controller;

import com.zhongdihang.bankdispatch.common.constant.DictEnum;
import com.zhongdihang.bankdispatch.common.util.StrUtil;
import com.zhongdihang.bankdispatch.core.controller.BaseController;
import com.zhongdihang.bankdispatch.core.page.PageFactory;
import com.zhongdihang.bankdispatch.modular.domain.Assess;
import com.zhongdihang.bankdispatch.modular.service.AssessService;
import com.zhongdihang.bankdispatch.modular.service.dto.AssessDto;
import com.zhongdihang.bankdispatch.modular.service.dto.AssessQueryDto;
import com.zhongdihang.bankdispatch.modular.service.dto.DeptDto;
import com.zhongdihang.bankdispatch.modular.service.form.AssessForm;
import com.zhongdihang.bankdispatch.rest.ResultModel;
import com.zhongdihang.bankdispatch.rest.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */
@RestController
@RequestMapping("/assess")
@Api(description = "评估机构控制器")
public class AssessController extends BaseController{
    @Autowired
    private AssessService assessService;

    @ApiOperation("添加评估机构")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultModel addAssess(@Valid AssessForm assessForm) {
        Boolean flag = assessService.existsByAssessName(assessForm.getName());
        if (flag) {
            return ResultModel.error(ResultStatus.FAILURE, "该机构名称已经存在");
        }
        Assess assess = new Assess();
        assess.setName(assessForm.getName());
        assess.setAddress(assessForm.getAddress());
        assess.setTelephone(assessForm.getTelephone());
        assess.setGuarantyType(assessForm.getGuarantyType());
        assess.setLinkMen(assessForm.getLinkMen());
        assess.setCreateTime(new Date());
        assess.setDeleted(false);
        assessService.saveAssess(assess);
        return ResultModel.ok();
    }

    @ApiOperation("删除评估机构")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultModel deleteAssess(@RequestParam("id") Long id) {
        assessService.deleteAssess(id);
        return ResultModel.ok();
    }

    @ApiOperation("评估机构详情")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResultModel getAssess(@RequestParam("id") Long id) {
        Assess assess = assessService.getAssess(id);
        if(assess==null){
            return ResultModel.error(ResultStatus.FAILURE, "机构不存在");
        }
        AssessDto assessDto = new AssessDto().convert(assess);
        return ResultModel.ok(assessDto);
    }

    @ApiOperation("查询评估机构")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "condition", value = "评估机构名称/电话号码", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "Date", name = "startDate", value = "注册开始日期", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "Date", name = "endDate", value = "注册结束日期", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "pageIndex", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "pageSize", value = "每页数量", required = true)})
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultModel findAssess(String condition, Date startDate, Date endDate, Integer pageIndex, Integer pageSize) {
        AssessQueryDto assessQueryDto = new AssessQueryDto()
                .setCondition(condition)
                .setStartDate(startDate)
                .setEndDate(endDate);
        PageRequest pageRequest = new PageFactory().defaultPage();
        Page<Assess> assesses = assessService.findAssess(assessQueryDto, pageRequest);
        Page<AssessDto> assessDtos = assesses.map(new AssessDto());
        assessDtos.forEach(t->setGuarantyType(t));
        return ResultModel.ok(assessDtos);
    }

    public AssessDto setGuarantyType(AssessDto assessDto){
        String guaranty=assessDto.getGuarantyType();
        if (!StringUtils.isEmpty(guaranty)) {
            String[] guarantyAry = guaranty.split(",");
            StringBuilder guarantyStr=new StringBuilder();
            for (String s : guarantyAry) {
                guarantyStr.append(getDictDataName(DictEnum.抵押物类别,s));
                guarantyStr.append(",");
            }
            assessDto.setGuarantyType(StrUtil.removeSuffix(guarantyStr.toString(),","));
        }
        return assessDto;
    }


    @ApiOperation("修改评估机构信息")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResultModel updateAssess(@Valid AssessForm assessForm) {
        Assess assess = assessService.getAssess(assessForm.getId());
        if(assess==null){
            ResultModel.error(ResultStatus.INVALID_USERNAME,"评估机构不存在");
        }
        assess.setName(assessForm.getName());
        assess.setAddress(assessForm.getAddress());
        assess.setLinkMen(assessForm.getLinkMen());
        assess.setTelephone(assessForm.getTelephone());
        assess.setGuarantyType(assessForm.getGuarantyType());
        assess.setUpdateTime(new Date());
        assessService.saveAssess(assess);
        return ResultModel.ok();
    }

    @ApiOperation("评估机构列表")
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public ResultModel bankList(){
        List<Assess> assesses=assessService.findAssessList();
        List<DeptDto> deptDtos=new ArrayList<>();
        for (Assess assess : assesses) {
            DeptDto deptDto=new DeptDto();
            deptDto.setTypeName("评估机构");
            deptDto.setType(2);
            deptDto.setDeptId(assess.getId().toString());
            deptDto.setDeptName(assess.getName());
            deptDtos.add(deptDto);
        }
        return ResultModel.ok(deptDtos);
    }
}
