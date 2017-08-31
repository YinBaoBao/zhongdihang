package com.zhongdihang.bankdispatch.modular.service.impl;

import com.zhongdihang.bankdispatch.modular.dao.FileDao;
import com.zhongdihang.bankdispatch.modular.domain.File;
import com.zhongdihang.bankdispatch.modular.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by win10 on 2017/08/18.
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDao fileDao;;
    @Override
    public File saveFiel(File file) {
        if (file!=null){
           return fileDao.save(file);
        }
        return file;
    }

    @Override
    public File findFile(Long id) {
        File file = fileDao.findOne(id);
        return file;
    }
}
