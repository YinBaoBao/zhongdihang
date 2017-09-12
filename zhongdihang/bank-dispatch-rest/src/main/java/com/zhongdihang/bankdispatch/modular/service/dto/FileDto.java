package com.zhongdihang.bankdispatch.modular.service.dto;

import java.io.Serializable;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/31 21:49
 * @Modified By：
 * @Version: V1.0.0
 */
public class FileDto implements Serializable{
    private static final long serialVersionUID = 2377892164402293906L;
    private String id;

    private String path;

    public FileDto(String id, String path) {
        this.id = id;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
