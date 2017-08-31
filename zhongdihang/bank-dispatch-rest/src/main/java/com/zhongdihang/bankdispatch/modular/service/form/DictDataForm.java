package com.zhongdihang.bankdispatch.modular.service.form;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/17 20:32
 * @Modified By：
 * @Version: V1.0.0
 */
public class DictDataForm {
    private Long id;

    private String dictDataCode;

    private String dictDataName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDictDataCode() {
        return dictDataCode;
    }

    public void setDictDataCode(String dictDataCode) {
        this.dictDataCode = dictDataCode;
    }

    public String getDictDataName() {
        return dictDataName;
    }

    public void setDictDataName(String dictDataName) {
        this.dictDataName = dictDataName;
    }
}
