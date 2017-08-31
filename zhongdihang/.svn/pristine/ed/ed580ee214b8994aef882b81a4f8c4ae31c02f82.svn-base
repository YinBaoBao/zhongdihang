package com.zhongdihang.bankdispatch.modular.service.dto;

import com.zhongdihang.bankdispatch.modular.domain.DictData;
import org.springframework.util.StringUtils;

import java.util.Comparator;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/18 16:45
 * @Modified By：
 * @Version: V1.0.0
 */
public class DictDataDto implements Comparable{

    private String id;

    private String dictDataName;

    private String dictDataCode;

    public DictDataDto(String id, String dictDataName, String dictDataCode) {
        this.id = id;
        this.dictDataName = dictDataName;
        this.dictDataCode = dictDataCode;
    }

    public DictDataDto(DictData dictData){
        this.setId(dictData.getId().toString());
        this.setDictDataName(dictData.getDictDataName());
        this.setDictDataCode(dictData.getDictDataCode());
    }

    public DictDataDto() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDictDataName() {
        return dictDataName;
    }

    public void setDictDataName(String dictDataName) {
        this.dictDataName = dictDataName;
    }

    public String getDictDataCode() {
        return dictDataCode;
    }

    public void setDictDataCode(String dictDataCode) {
        this.dictDataCode = dictDataCode;
    }


    @Override
    public int compareTo(Object object) {
        DictDataDto o= (DictDataDto) object;
        if (this == null || o == null || StringUtils.isEmpty(this.getDictDataCode()) || StringUtils.isEmpty(o.getDictDataCode())) {
            return -1;
        }
        if (this.getDictDataCode().length() > o.getDictDataCode().length()) {
            return 1;
        }
        if (this.getDictDataCode().length() < o.getDictDataCode().length()) {
            return -1;
        }
        if (this.getDictDataCode().compareTo(o.getDictDataCode()) > 0) {
            return 1;
        }
        if (this.getDictDataCode().compareTo(o.getDictDataCode()) < 0) {
            return -1;
        }
        if (this.getDictDataCode().compareTo(o.getDictDataCode()) == 0) {
            return 0;
        }
        return 0;
    }
}
