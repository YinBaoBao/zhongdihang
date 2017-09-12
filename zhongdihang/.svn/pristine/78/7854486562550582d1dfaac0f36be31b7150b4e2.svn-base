package com.zhongdihang.bankdispatch.modular.controller;

import com.zhongdihang.bankdispatch.common.constant.DictEnum;
import com.zhongdihang.bankdispatch.common.util.MutiStrFactory;
import com.zhongdihang.bankdispatch.common.exceptions.BusinessException;
import com.zhongdihang.bankdispatch.core.controller.BaseController;
import com.zhongdihang.bankdispatch.modular.domain.Dict;
import com.zhongdihang.bankdispatch.modular.domain.DictData;
import com.zhongdihang.bankdispatch.modular.service.DictService;
import com.zhongdihang.bankdispatch.modular.service.dto.DictDataDto;
import com.zhongdihang.bankdispatch.modular.service.dto.DictDto;
import com.zhongdihang.bankdispatch.modular.service.dto.SelectItem;
import com.zhongdihang.bankdispatch.rest.ResultModel;
import com.zhongdihang.bankdispatch.rest.ResultStatus;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.zhongdihang.bankdispatch.common.util.MutiStrFactory.parseKeyValue;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/5 15:43
 * @Modified By：
 * @Version: V1.0.0
 */
@RestController
@RequestMapping("/dict")
@Api(description = "字典控制器")
public class DictController extends BaseController{

    @Autowired
    private DictService dictService;

    /**
     * 新增字典
     *
     * @param dictValues 格式例如   "1:启用;2:禁用;3:冻结"
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResultModel add(@RequestParam("dictCode") String dictCode,
                           @RequestParam("dictName") String dictName,
                           @RequestParam("dictValues") String dictValues) {
        if(StringUtils.isEmpty(dictCode)){
            throw new BusinessException(ResultStatus.FAILURE,"请输入字典代码!");
        }
        if(StringUtils.isEmpty(dictName)){
            throw new BusinessException(ResultStatus.FAILURE,"请输入字典名称!");
        }
        //判断有没有该字典
        Dict dict = dictService.getByDictName(dictName);
        if(dict != null ){
            throw new BusinessException(ResultStatus.FAILURE,"该字典名称已存在");
        }
        dict=dictService.getByDictCode(dictCode);
        if(dict != null ){
            throw new BusinessException(ResultStatus.FAILURE,"该字典代码已存在");
        }
        if (StringUtils.isEmpty(dictValues)){
            throw new BusinessException(ResultStatus.FAILURE,"请输入字典项！");
        }
        //解析dictValues
        List<Map<String, String>> items = parseKeyValue(dictValues);
        dict = new Dict();
        dict.setCreateTime(new Date());
        dict.setCreateUser(getUser());
        dict.setDictCode(dictCode);
        dict.setDictName(dictName);
        Set<DictData> dictDatas=new HashSet<>();
        for (Map<String, String> item : items) {
            String code = item.get(MutiStrFactory.MUTI_STR_KEY);
            String name = item.get(MutiStrFactory.MUTI_STR_VALUE);
            DictData dictData=new DictData();
            dictData.setDictDataCode(code);
            dictData.setDictDataName(name);
            dictData.setDict(dict);
            dictDatas.add(dictData);
        }
        dict.setCreateTime(new Date());
        dict.setCreateUser(getUser());
        dict.setDictDatas(dictDatas);
        this.dictService.saveDict(dict);
        return ResultModel.ok();
    }

    /**
     * 获取所有字典列表
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ResultModel list(@RequestParam(value = "condition",required = false) String condition) {
        List<Dict> dicts=new ArrayList<>();
        if (StringUtils.isEmpty(condition)){
            dicts=dictService.findAll();
        }else {
            dicts = dictService.findDictByCondition(condition);
        }
        List<DictDto> dictDtos=new ArrayList<>();
        dicts.forEach(t->dictDtos.add(new DictDto().mapDict(t)));
        return ResultModel.ok(dictDtos);
    }

    /**
     * 字典详情
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public ResultModel detail(@RequestParam("id") Long id,Model model) {
        DictDto dictDto=new DictDto();
        Dict dict=dictService.getDict(id);
        if (dict==null)throw new BusinessException(ResultStatus.FAILURE,"字典不存在");
        List<DictDataDto> dictDataDtos=new ArrayList<>();
        dict.getDictDatas().forEach(t->dictDataDtos.add(new DictDataDto(t)));
        Collections.sort(dictDataDtos);
        dictDto.mapDict(dict);
        model.addAttribute("dict",dictDto);
        model.addAttribute("dictDatas",dictDataDtos);
        return ResultModel.ok(model);
    }

    /**
     * 修改字典
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Object update(@RequestParam("id") Long id,
                         @RequestParam("dictCode") String dictCode,
                         @RequestParam("dictName") String dictName,
                         @RequestParam("dictValues") String dictValues) {
        if(StringUtils.isEmpty(dictCode)){
            throw new BusinessException(ResultStatus.FAILURE,"请输入字典代码!");
        }
        if(StringUtils.isEmpty(dictName)){
            throw new BusinessException(ResultStatus.FAILURE,"请输入字典名称!");
        }
        Dict dict=dictService.getDict(id);
        if (dict==null){
            throw new BusinessException(ResultStatus.FAILURE,"字典不存在!");
        }
        delete(id);
        add(dictCode,dictName,dictValues);
        return ResultModel.ok();
    }

    /**
     * 删除字典记录
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public ResultModel delete(@RequestParam("id") Long id) {
        dictService.deleteDict(id);
        return ResultModel.ok();
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/31 20:49
     * @MenthodName: getDictData
     * @Description:根据字典表的代码获取字典表，生成下拉框
     * @param dictCode 字典表代码
     * @Return: com.zhongdihang.bankdispatch.rest.ResultModel
     */
    @RequestMapping(value = "/select",method = RequestMethod.GET)
    @ResponseBody
    public ResultModel getDictData(@RequestParam String dictCode){

        if (StringUtils.isEmpty(dictCode)){
            return ResultModel.error(ResultStatus.FAILURE,"无字典代码");
        }
        DictEnum dictEnum= DictEnum.fromString(dictCode);
        if(dictEnum==null){
            return ResultModel.error(ResultStatus.FAILURE,"无效的字典");
        }
        Map<String, String> map= getDictData(dictEnum);
        List<SelectItem> selectItems=new ArrayList<>();
        for (String s : map.keySet()) {
            SelectItem selectItem=new SelectItem(s,map.get(s));
            selectItems.add(selectItem);
        }

        return ResultModel.ok(selectItems);
    }


}
