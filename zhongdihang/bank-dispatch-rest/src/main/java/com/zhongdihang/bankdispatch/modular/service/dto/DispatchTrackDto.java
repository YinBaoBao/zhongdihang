package com.zhongdihang.bankdispatch.modular.service.dto;

import java.util.Date;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/9/2 19:39
 * @Modified By：
 * @Version: V1.0.0
 */
public class DispatchTrackDto {
    private Boolean isSelected;

    private String title;

    private String subTitle;

    private Date date;

    public DispatchTrackDto(Boolean isSelected, String title, String subTitle, Date date) {
        this.isSelected = isSelected;
        this.title = title;
        this.subTitle = subTitle;
        this.date = date;
    }
    public DispatchTrackDto() {

    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
