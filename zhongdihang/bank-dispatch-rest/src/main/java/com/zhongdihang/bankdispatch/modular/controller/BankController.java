package com.zhongdihang.bankdispatch.modular.controller;

import com.zhongdihang.bankdispatch.common.constant.DictEnum;
import com.zhongdihang.bankdispatch.core.controoller.BaseController;
import com.zhongdihang.bankdispatch.modular.domain.Bank;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


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
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BankController.class);

    @ApiOperation("添加银行机构")
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody ResultModel addBank( BankForm bankForm){
        logger.info(bankForm);
        Bank bank = new Bank();
        bank.setName(bankForm.getName());
        bank.setCreateTime(new Date());
        bank.setAddress(bankForm.getAddress());
        bank.setAreaCode(bankForm.getAreaCode());
        bank.setLinkMen(bankForm.getLinkMen());
        bank.setTelephone(bankForm.getTelephone());
        bank.setDeleted(false);
        bank.setType(bankForm.getType());
        bankService.saveBank(bank);
        return ResultModel.ok();
    }

    @ApiOperation("查询银行机构")
    @RequestMapping(value = "/info", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResultModel getBank(String BankId){
        Bank bank1 = bankService.getBank(Long.valueOf(BankId));
        BankDto bankDto = new BankDto();
        bankDto.setName(bank1.getName());
        bankDto.setAddress(bank1.getAddress());
        bankDto.setAreaCode(bank1.getAreaCode());
        bankDto.setCreateTime(bank1.getCreateTime());
        bankDto.setDeleted(bank1.getDeleted());
        bankDto.setLinkMen(bank1.getLinkMen());
        bankDto.setTelephone(bank1.getTelephone());
        bankDto.setType(getDictDataName(DictEnum.银行类别,bankDto.getType()));
        bankDto.setUpdateTime(bank1.getUpdateTime());
        bankDto.setId(bank1.getId()+"");
        /*bankDto.setCreateUserId(bank1.getCreateUser());
        bankDto.setpBankId(bank1.getpBank());
        bankDto.setUpdateUserId(bank1.getUpdateUser());*/
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

    @ApiOperation("银行和评估机构入围关系")
    @RequestMapping(value = "/assess", method = RequestMethod.POST)
    public ResultModel bankAssess(@RequestBody Long bankId,Long[] assessIds){
        Bank bank1 = bankService.bankAssess(bankId,assessIds);
        return ResultModel.ok(bank1);
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

    @ApiOperation("所有银行")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultModel bankList_(){
        List<Bank> banks=bankService.findBankList();
        List<BankDto> bankDtoList = new ArrayList<>();
        for (Bank bank:banks){
            BankDto bankDto = new BankDto();
            bankDto.setAddress(bank.getAddress());
            bankDto.setId(bank.getId().toString());
            bankDto.setName(bank.getName());
            bankDto.setType(getDictDataName(DictEnum.银行类别,bank.getType()));
            bankDto.setTelephone(bank.getTelephone());
            bankDto.setAreaCode(bank.getAreaCode());
            bankDto.setLinkMen(bank.getLinkMen());
            bankDto.setDeleted(bank.getDeleted());
            bankDtoList.add(bankDto);
        }
        return ResultModel.ok(bankDtoList);
    }

    @ApiOperation("添加银行机构")
    @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody ResultModel editBank( BankForm bankForm){
        Bank bank = bankService.getBank(Long.valueOf(bankForm.getId()));
        if (bank==null){
            return ResultModel.error(ResultStatus.FAILURE,"未找到对应的机构");
        }
        bank.setName(bankForm.getName());
        bank.setCreateTime(new Date());
        bank.setAddress(bankForm.getAddress());
        bank.setAreaCode(bankForm.getAreaCode());
        bank.setLinkMen(bankForm.getLinkMen());
        bank.setTelephone(bankForm.getTelephone());
        bank.setDeleted(false);
        bank.setType(bankForm.getType());
        bankService.saveBank(bank);
        return ResultModel.ok();
    }

    @ApiOperation("银行和评估机构入围关系")
    @RequestMapping(value = "/bankbyname", method = RequestMethod.GET)
    public ResultModel bankByName(@RequestParam String Name){
        List<Bank> bankList = bankService.findBankByName(Name);
        List<BankDto> bankDtoList = new ArrayList<>();
        if (bankDtoList!=null){
            for (Bank bank :bankList){
                BankDto bankDto = new BankDto();
                bankDto.setAddress(bank.getAddress());
                bankDto.setId(bank.getId().toString());
                bankDto.setName(bank.getName());
                bankDto.setType(getDictDataName(DictEnum.银行类别,bank.getType()));
                bankDto.setTelephone(bank.getTelephone());
                bankDto.setAreaCode(bank.getAreaCode());
                bankDto.setLinkMen(bank.getLinkMen());
                bankDto.setDeleted(bank.getDeleted());
                bankDtoList.add(bankDto);
            }
        }
        return ResultModel.ok(bankDtoList);
    }


}
