package com.zhongdihang.bankdispatch.modular.service.dto;

import com.zhongdihang.bankdispatch.common.util.StrUtil;
import com.zhongdihang.bankdispatch.modular.domain.Dict;
import com.zhongdihang.bankdispatch.modular.domain.DictData;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/17 21:09
 * @Modified By：
 * @Version: V1.0.0
 */
public class DictDto {
    private String id;
    private String dictName;
    private String dictCode;
    private String dictDatas;

    public DictDto mapDict(Dict dict){
        this.setDictCode(dict.getDictCode());
        this.setId(dict.getId().toString());
        this.setDictName(dict.getDictName());
        Set<DictData> dictDatas=dict.getDictDatas();
        List<DictData> dictDataList=dictDatas.stream().sorted((o1, o2) -> {
            if (o1 == null || o2 == null || StringUtils.isEmpty(o1.getDictDataCode()) || StringUtils.isEmpty(o2.getDictDataCode())) {
                return -1;
            }
            if (o1.getDictDataCode().length() > o2.getDictDataCode().length()) {
                return 1;
            }
            if (o1.getDictDataCode().length() < o2.getDictDataCode().length()) {
                return -1;
            }
            if (o1.getDictDataCode().compareTo(o2.getDictDataCode()) > 0) {
                return 1;
            }
            if (o1.getDictDataCode().compareTo(o2.getDictDataCode()) < 0) {
                return -1;
            }
            if (o1.getDictDataCode().compareTo(o2.getDictDataCode()) == 0) {
                return 0;
            }
            return 0;

        }).collect(Collectors.toList());
        StringBuilder strDictData=new StringBuilder();
        for (DictData dictData : dictDataList) {
            strDictData.append(dictData.getDictDataCode() + ":" +dictData.getDictDataName() + ",");
        }
        this.setDictDatas(StrUtil.removeSuffix(strDictData.toString(),","));
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictDatas() {
        return dictDatas;
    }

    public void setDictDatas(String dictDatas) {
        this.dictDatas = dictDatas;
    }
}
