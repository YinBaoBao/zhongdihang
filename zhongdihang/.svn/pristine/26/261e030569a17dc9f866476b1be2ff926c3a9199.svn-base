package com.zhongdihang.bankdispatch.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultList<T extends Object> extends AbstractResult {
    private List<T> dataList; //列表数据
    private Integer totalPage; //总页数
    private Integer totalNumber;//总条数
    private Integer pageIndex; //当前页
    private Integer pageSize;  //当前页条数

    public static <T> ResultList<T> ok() {
        return new ResultList<T>(0, null);
    }

    public static <T> ResultList<T> error() {
        return new ResultList<T>(1, null);
    }

    public static <T> ResultList<T> error(int code) {
        return new ResultList<T>(code, null);
    }

    public static <T> ResultList<T> error(int code, String message) {
        return new ResultList<T>(code, message);
    }

    public static <T> ResultList<T> list(Page<T> datas) {
        ResultList<T> res =  new ResultList<T>(0, null);
        List<T> list  = new ArrayList<T>();
        for (T obj:datas){
            list.add(obj);
        }
        res.dataList = list;
        res.totalPage = datas.getTotalPages();
        res.totalNumber = (int)datas.getTotalElements();
        res.pageIndex = datas.getNumber();
        res.pageSize = datas.getNumberOfElements();
        return res;
    }

  
//    @Deprecated
//    public static <T> ResultList<T> list(List<T> datas) {
//        ResultList<T> res =  new ResultList<T>(0, null);
//        res.dataList = datas;
//        return res;
//    }


    public ResultList() {
        this(0, null);
    }


    public ResultList(int code, String message) {
        super(code, message);
    }

    public ResultList<T> list(List<T> objs, int pageSize, int totalNumber, int pageIndex) {
        this.dataList = objs;
        totalPage = totalNumber % pageSize == 0 ? totalNumber / pageSize : totalNumber / pageSize + 1;
        this.totalNumber = totalNumber;
        this.pageIndex = pageIndex;
        return this;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    //总条数
    public Integer getObjectotalNumber() {
        return totalNumber;
    }

    public void setObjectotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public Integer getObjectotalPage() {
        return totalPage;
    }

    public void setObjectotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
