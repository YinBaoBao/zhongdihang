package com.zhongdihang.bankdispatch.modular.controller;

import com.zhongdihang.bankdispatch.common.constant.DictEnum;
import com.zhongdihang.bankdispatch.common.node.ZTreeNode;
import com.zhongdihang.bankdispatch.core.controller.BaseController;
import com.zhongdihang.bankdispatch.modular.domain.Assess;
import com.zhongdihang.bankdispatch.modular.domain.Bank;
import com.zhongdihang.bankdispatch.modular.service.AssessService;
import com.zhongdihang.bankdispatch.modular.service.BankService;
import com.zhongdihang.bankdispatch.modular.service.DictService;
import com.zhongdihang.bankdispatch.modular.service.dto.BankDto;
import com.zhongdihang.bankdispatch.modular.service.dto.DeptDto;
import com.zhongdihang.bankdispatch.modular.service.form.BankForm;
import com.zhongdihang.bankdispatch.rest.ResultModel;
import com.zhongdihang.bankdispatch.rest.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Created by win10 on 2017/08/15.
 */

@RestController
@RequestMapping("/bank")
@Api(description = "银行控制器")
public class BankController extends BaseController{
    @Autowired
    private DictService dictService;
    @Autowired
    private BankService bankService;
    @Autowired
    private AssessService assessService;
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BankController.class);

    @ApiOperation("添加银行机构")
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody ResultModel addBank( BankForm bankForm){
        logger.info(bankForm);
        Bank bank = new Bank();
        bank.setName(bankForm.getName());
        bank.setCreateTime(new Date());
        bank.setAddress(bankForm.getAddress());
        bank.setArea(bankForm.getArea());
        bank.setLinkMen(bankForm.getLinkMen());
        bank.setTelephone(bankForm.getTelephone());
        bank.setDeleted(false);
        bank.setType(bankForm.getType());
        bankService.saveBank(bank);
        return ResultModel.ok();
    }

    @ApiOperation("银行信息详情")
    @RequestMapping(value = "/info", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResultModel getBank(String BankId){
        Bank bank = bankService.getBank(Long.valueOf(BankId));
        BankDto bankDto = new BankDto();
        bankDto.setName(bank.getName());
        bankDto.setAddress(bank.getAddress());
        bankDto.setArea(bank.getArea());
        bankDto.setCreateTime(bank.getCreateTime());
        bankDto.setDeleted(bank.getDeleted());
        bankDto.setLinkMen(bank.getLinkMen());
        bankDto.setTelephone(bank.getTelephone());
        bankDto.setType(bank.getType());
        bankDto.setUpdateTime(bank.getUpdateTime());
        bankDto.setId(bank.getId().toString());
        return ResultModel.ok(bankDto);
    }

    @ApiOperation("删除银行机构")
    @RequestMapping(value = "/delete", method = RequestMethod.POST,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResultModel deleteBank(String BankId){
        try{
            bankService.deleteBank(Long.valueOf(BankId));
            return ResultModel.ok();
        }catch (Exception e){
            return ResultModel.error(ResultStatus.SERVER_ERROR,e);
        }
    }

    @ApiOperation("评估机构入围银行")
    @RequestMapping(value = "/assess", method = RequestMethod.POST)
    public ResultModel bankAssess(Long bankId,@RequestParam("assessIds[]")  Long[] assessIds){
        bankService.bankAssess(bankId,assessIds);
        return ResultModel.ok();
    }

    @ApiOperation("评估机构选择")
    @RequestMapping(value = "/bankAssessTree", method = RequestMethod.GET)
    public ResultModel assessTreeListByBank(@RequestParam Long bankId){
        Bank bank = bankService.getBank(bankId);
        if (bank==null){
            return ResultModel.error(ResultStatus.FAILURE,"银行不存在");
        }
        Set<Assess> assesses= bank.getAssesses();
        List<ZTreeNode> zTreeNodes=assessService.getAssessTree();
        if (assesses==null||assesses.size()<=0) {
            return ResultModel.ok(zTreeNodes);
        } else {
            zTreeNodes.forEach(z->assesses.forEach(a->{
                if(z.getId().equals(a.getId().toString())){
                    z.setChecked(true);
                }
            }));
            return ResultModel.ok(zTreeNodes);
        }
    }

    @ApiOperation("银行列表")
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public ResultModel bankList(){
        List<Bank> banks=bankService.findBankList();
        List<DeptDto> deptDtos=new ArrayList<>();
        for (Bank bank : banks) {
            DeptDto deptDto=new DeptDto();
            deptDto.setDeptId(bank.getId().toString());
            deptDto.setDeptName(bank.getName());
            deptDto.setTypeName("银行");
            deptDto.setType(1);
            deptDtos.add(deptDto);
        }
        return ResultModel.ok(deptDtos);
    }

    @ApiOperation("添加银行机构")
    @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody ResultModel editBank(BankForm bankForm){
        Bank bank = bankService.getBank(Long.valueOf(bankForm.getId()));
        if (bank==null){
            return ResultModel.error(ResultStatus.FAILURE,"未找到对应的机构");
        }
        bank.setName(bankForm.getName());
        bank.setCreateTime(new Date());
        bank.setAddress(bankForm.getAddress());
        bank.setArea(bankForm.getArea());
        bank.setLinkMen(bankForm.getLinkMen());
        bank.setTelephone(bankForm.getTelephone());
        bank.setDeleted(false);
        bank.setType(bankForm.getType());
        bankService.saveBank(bank);
        return ResultModel.ok();
    }

    @ApiOperation("银行列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultModel bankList(@RequestParam(required = false) String bankName){
        List<Bank> banks=bankService.findBankList();
        if(!StringUtils.isEmpty(bankName)){
            banks=banks.stream().filter(t->t.getName().equals(bankName)).collect(Collectors.toList());
        }
        List<BankDto> bankDtoList = new ArrayList<>();
        for (Bank bank:banks){
            BankDto bankDto = new BankDto();
            bankDto.setAddress(bank.getAddress());
            bankDto.setId(bank.getId().toString());
            bankDto.setName(bank.getName());
            bankDto.setType(getDictDataName(DictEnum.银行类别,bank.getType()));
            bankDto.setTelephone(bank.getTelephone());
            bankDto.setArea(bank.getArea());
            bankDto.setLinkMen(bank.getLinkMen());
            bankDto.setDeleted(bank.getDeleted());
            bankDtoList.add(bankDto);
        }
        return ResultModel.ok(bankDtoList);
    }

}
