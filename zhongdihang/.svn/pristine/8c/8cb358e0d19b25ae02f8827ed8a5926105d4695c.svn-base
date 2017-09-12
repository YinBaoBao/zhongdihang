package com.zhongdihang.bankdispatch.modular.controller;

import com.alibaba.fastjson.JSON;
import com.zhongdihang.bankdispatch.common.constant.DictEnum;
import com.zhongdihang.bankdispatch.common.constant.DispatchStatusEnum;
import com.zhongdihang.bankdispatch.common.constant.SMSTemplateEnum;
import com.zhongdihang.bankdispatch.common.sms.DispatchSMSTemplate;
import com.zhongdihang.bankdispatch.common.sms.SendMsgUtil;
import com.zhongdihang.bankdispatch.core.controller.BaseController;
import com.zhongdihang.bankdispatch.core.page.PageFactory;
import com.zhongdihang.bankdispatch.modular.domain.*;
import com.zhongdihang.bankdispatch.modular.service.DispatchService;
import com.zhongdihang.bankdispatch.modular.service.DispatchTrackService;
import com.zhongdihang.bankdispatch.modular.service.FileService;
import com.zhongdihang.bankdispatch.modular.service.dto.DispatchDto;
import com.zhongdihang.bankdispatch.modular.service.form.DispatchForm;
import com.zhongdihang.bankdispatch.modular.service.form.GuarantyItemForm;
import com.zhongdihang.bankdispatch.modular.service.form.newGUarantyForm;
import com.zhongdihang.bankdispatch.rest.ResultModel;
import com.zhongdihang.bankdispatch.rest.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by win10 on 2017/08/17.
 */
@RestController
@RequestMapping("/dispatch")
@Api(description = "派单控制器")
public class DispatchController extends BaseController
{
    @Autowired
    private DispatchService dispatchService;

    @Autowired
    private FileService fileService;

    @Autowired
    private DispatchTrackService dispatchTrackService;

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DispatchController.class);

    @RequestMapping(value = "/release", method = RequestMethod.POST)
    public ResultModel dispatch(@RequestBody @Valid DispatchForm dispatchForm){
        Dispatch dispatch=new Dispatch();
        dispatch.setCreateTime(new Date());
        dispatch.setCreateUser(getUser());
        dispatch.setBank(getUser().getBank());
        dispatch.setBankUser(getUser());
        dispatch.setDispatchType(dispatchForm.getDispatchType());
        dispatch.setTimeLimit(dispatchForm.getTimeLimit());
        dispatch.setStatus(DispatchStatusEnum.待接单.getStatus());
        dispatch.setArea(dispatchForm.getArea());
        dispatch.setGuarantyType(dispatchForm.getGuarantyType());
        dispatch.setBorrowerName(dispatchForm.getBorrowerName());
        dispatch.setBorrowerPhone(dispatchForm.getBorrowerPhone());
        dispatch.setDispatchNo("-");
        List<GuarantyItemForm> guaranties=dispatchForm.getGuarantyFormList();
        Set<Guaranty> guarantySet=new HashSet<>();
        if(guaranties==null||guaranties.size()<0){
            return ResultModel.error(ResultStatus.FAILURE,"请上传需要评估的房产证图片！");
        }

        for (GuarantyItemForm guarantyItemForm : guaranties) {
            File file=fileService.findFile(guarantyItemForm.getFileId());
            if (file==null|| StringUtils.isEmpty(file.getAbsolutePath())){
                return ResultModel.error(ResultStatus.FAILURE,"请上传需要评估的房产证图片！");
            }
            //判断文件是否存在
            java.io.File realfile=new java.io.File(file.getAbsolutePath());
            if(!realfile.exists()){
                return ResultModel.error(ResultStatus.FAILURE,"请上传需要评估的房产证图片！");
            }
            Guaranty guaranty=new Guaranty();
            guaranty.setCreateTime(new Date());
            guaranty.setCreateUser(getUser());
            guaranty.setDispatch(dispatch);
            guaranty.setFile(file);
            guaranty.setContactName(guarantyItemForm.getContactName());
            guaranty.setContactPhone(guarantyItemForm.getContactPhone());
            guarantySet.add(guaranty);
        }
        dispatch.setGuaranties(guarantySet);
        Assess assess=dispatchService.dispatchAssess(dispatch);
        if (assess==null){
            return ResultModel.error(ResultStatus.FAILURE,"派单银行暂无符合要求的入围评估机构！");
        }
        dispatch.setAssess(assess);
        DispatchTrack dispatchTrack=new DispatchTrack();
        dispatchTrack.setCreateTime(new Date());
        dispatchTrack.setCreateUser(getUser());
        dispatchTrack.setDispatch(dispatch);
        dispatchTrack.setNode("已派单");
        dispatchTrack.setRemark("评估已分配评估公司"+dispatch.getAssess().getName());
        dispatch.addDispatchTracks(dispatchTrack);
        dispatchService.save(dispatch);
        //派单短信通知
        DispatchSMSTemplate dispatchSMSTemplate=new DispatchSMSTemplate(dispatch.getBank().getName(),dispatch.getDispatchNo(),getUser().getRealName(),getUser().getTelephone());
        SendMsgUtil.sendMsg(assess.getTelephone(), JSON.toJSONString(dispatchSMSTemplate), SMSTemplateEnum.DISPATCH.getTemplate());
        return ResultModel.ok();
    }


    @ApiOperation("按权重派单")
    @RequestMapping(value = "/randomDispatch", method = RequestMethod.POST)
    public ResultModel randomDispatch(@RequestBody List<newGUarantyForm> gUarantyFormList)
    {
        Bank bank = getUser().getBank();
        Long bankId = 0L;
        if (bank!=null)
        {
            bankId = bank.getId();
        }
        else
        {
            //当前用户非银行用户
            return ResultModel.error(ResultStatus.FAILURE,"当前用户非银行用户");
        }
        return ResultModel.ok(dispatchService.randomDispatch(bankId,getUser(),gUarantyFormList));
    }


    @ApiOperation("完成单据")
    @RequestMapping(value = "/dispatchUpdate", method = RequestMethod.POST)
    public ResultModel dispatchUpdate(@RequestParam int Status,@RequestParam Long dispatchId,@RequestParam Long fileId)
    {
        try {
            dispatchTrackService.dispatchUpdate(Status,dispatchId,fileId);
            return  ResultModel.ok();
        }catch (Exception e){
            return  ResultModel.error(ResultStatus.FAILURE,e);
        }
    }

    @ApiOperation("查询当前机构订单")
    @ApiImplicitParams
            ({
                    @ApiImplicitParam(paramType = "query", dataType = "int", name = "status", value = "状态", required = false),
                    @ApiImplicitParam(paramType = "query", dataType = "String", name = "dispatchNo", value = "单据号", required = false),
                    @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "pageIndex", value = "当前页", required = true),
                    @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "pageSize", value = "每页数量", required = true)
            })
    @RequestMapping(value = "/finddispatch", method = RequestMethod.GET)
    public @ResponseBody ResultModel finddispatch(String status,String dispatchNo)
    {
        Bank bank = new Bank();
        Assess assess = new Assess();
        if (getUser().getBank()!=null)
        {
            bank  = getUser().getBank();
        }
        else if (getUser().getAssess()!=null)
        {
            assess= getUser().getAssess();
        }
        else
        {
            return ResultModel.error(ResultStatus.FAILURE,"未找到账户信息");
        }

        PageRequest pageRequest = new PageFactory().defaultPage();
        Page<Dispatch> dispatches = dispatchService.findDispatch(status,bank,assess,dispatchNo,pageRequest);
        Page<DispatchDto> dispatchDtos = dispatches.map(new DispatchDto());
        dispatchDtos.forEach(t->t.setStatus(getDictDataName(DictEnum.单据状态,t.getStatus())));
        dispatchDtos.forEach(t->t.setDispatchType(getDictDataName(DictEnum.评估类型,t.getDispatchType())));
        return  ResultModel.ok(dispatchDtos);
    }

    @ApiOperation("转正式评估")
    @RequestMapping(value = "/dispatchFormal", method = RequestMethod.GET)
    public ResultModel dispatchByFormal(@RequestParam Long dispatchId)
    {
        try
        {
            dispatchService.dispatchByFormal(dispatchId,getUser());
            return  ResultModel.ok();
        }
        catch (Exception e)
        {
            return ResultModel.error(ResultStatus.FAILURE,e);
        }
    }

    @ApiOperation("取消评估")
    @RequestMapping(value = "/deleteDispatch", method = RequestMethod.GET)
    public ResultModel deleteDispatch(@RequestParam Long dispatchId)
    {
        try
        {
            dispatchService.deleteLispatch(dispatchId,getUser());
            return  ResultModel.ok();
        }
        catch (Exception e)
        {
            return ResultModel.error(ResultStatus.FAILURE,e);
        }
    }


    @ApiOperation("根据ID查询单据")
    @RequestMapping(value = "/finddispatchbyid", method = RequestMethod.GET)
    public ResultModel findDispatchByid(@RequestParam Long dispatchId)
    {
        DispatchDto dispatchDto = dispatchService.findDispatchById(dispatchId);
        dispatchDto.setStatus(getDictDataName(DictEnum.单据状态,dispatchDto.getStatus()));
        dispatchDto.setDispatchType(getDictDataName(DictEnum.评估类型,dispatchDto.getDispatchType()));
        dispatchDto.setGuarantyType(getDictDataName(DictEnum.抵押物类别,dispatchDto.getGuarantyType()));

        return  ResultModel.ok(dispatchDto);
    }


    @ApiOperation("接单")
    @RequestMapping(value = "/dispatchOrders", method = RequestMethod.GET)
    public ResultModel dispatchOrders(@RequestParam Long dispatchId)
    {
        try
        {
            dispatchService.dispatchOrders(dispatchId);
            return  ResultModel.ok();
        }
        catch (Exception e)
        {
            return  ResultModel.error(ResultStatus.FAILURE,e);
        }
    }


    @ApiOperation("正式评估完成")
    @RequestMapping(value = "/dispatchUpdateZ", method = RequestMethod.GET)
    public ResultModel dispatchUpdateZ(@RequestParam Long dispatchId)
    {
        try
        {
            dispatchService.dispatchUpdateZ(dispatchId);
            return  ResultModel.ok();
        }
        catch (Exception e)
        {
            return  ResultModel.error(ResultStatus.FAILURE,e);
        }
    }

    @ApiOperation("查询总数")
    @RequestMapping(value = "/dispatchStatistics", method = RequestMethod.GET)
    public ResultModel dispatchStatistics()
    {
        return ResultModel.ok(dispatchService.dispatchStatistics());
    }

    @ApiOperation("根据状态查询单据")
    @RequestMapping(value = "/dispatchbystatus", method = RequestMethod.GET)
    public ResultModel dispatchByStatus(@RequestParam String status)
    {
        List<DispatchDto> dispatchDto = dispatchService.dispatchByStatus(status);
        dispatchDto.forEach(t->t.setStatus(getDictDataName(DictEnum.单据状态,t.getStatus())));
        dispatchDto.forEach(t->t.setDispatchType(getDictDataName(DictEnum.评估类型,t.getDispatchType())));
        Page<DispatchDto> dispatchDtos = new PageImpl<DispatchDto>(dispatchDto);
        return ResultModel.ok(dispatchDtos);
    }

    @ApiOperation("根据银行查询各个机构单据总数")
    @RequestMapping(value = "/assessCount", method = RequestMethod.GET)
    @ResponseBody
    public ResultModel assessCount()
    {
        return ResultModel.ok(dispatchService.assessCountByBank());
    }

    @ApiOperation("根据银行经理查询各个机构单据总数")
    @RequestMapping(value = "/assessCountByUser", method = RequestMethod.GET)
    @ResponseBody
    public ResultModel assessCountByUser()
    {
        return ResultModel.ok(dispatchService.assessCountByUser());
    }

    @ApiOperation("银行派单搜索")
    @ApiImplicitParams
            ({
                    @ApiImplicitParam(paramType = "query", dataType = "int", name = "startDate", value = "起始时间", required = false),
                    @ApiImplicitParam(paramType = "query", dataType = "String", name = "endDate", value = "结束时间", required = false),
                    @ApiImplicitParam(paramType = "query", dataType = "String", name = "search", value = "可输入单号、银行、机构、客户名称", required = false),
                    @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "pageIndex", value = "当前页", required = true),
                    @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "pageSize", value = "每页数量", required = true)
            })
    @RequestMapping(value = "/bankSearch", method = RequestMethod.GET)
    @ResponseBody
    public ResultModel bankSearch(String startDate,String endDate,String search)
    {
        SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
        PageRequest pageRequest = new PageFactory().defaultPage();
        Date startDate_ = null;
        Date endDate_ = null;
        try{
            if (startDate!=null &&!startDate.equals("")){
                startDate_=  sdfDay.parse(startDate);
            }
            if (endDate!=null &&!endDate.equals("")){
                endDate_ = sdfDay.parse(endDate);
            }
        }catch (Exception e){
            startDate_ = null;
            endDate_ = null;
        }
        Page<Dispatch> dispatches = dispatchService.bankSearch(startDate_,endDate_,search,pageRequest);
        Page<DispatchDto> dispatchDtos = dispatches.map(new DispatchDto());
        dispatchDtos.forEach(t->t.setStatus(getDictDataName(DictEnum.单据状态,t.getStatus())));
        dispatchDtos.forEach(t->t.setDispatchType(getDictDataName(DictEnum.评估类型,t.getDispatchType())));
        return ResultModel.ok(dispatchDtos);
    }

}
