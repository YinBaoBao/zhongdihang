package com.zhongdihang.bankdispatch.modular.domain;

import com.zhongdihang.framework.biz.jpa.domian.EntityLongId;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by win10 on 2017/08/18.
 */
@Entity
public class File extends EntityLongId {
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String originalFilename;

    @Column(nullable = false)
    private String fileName;
    /*
    相对路径
     */
    @Column(nullable = false)
    private String relativePath;

    /*
    绝对路径
     */
    @Column(nullable = false)
    private String absolutePath;

    @Column(nullable = false)
    private String suffixName;

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
}
