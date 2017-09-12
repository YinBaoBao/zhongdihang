package com.zhongdihang.bankdispatch.modular.service.impl;

import com.zhongdihang.bankdispatch.modular.dao.*;
import com.zhongdihang.bankdispatch.modular.domain.Dispatch;
import com.zhongdihang.bankdispatch.modular.domain.DispatchTrack;
import com.zhongdihang.bankdispatch.modular.domain.PreReport;
import com.zhongdihang.bankdispatch.modular.service.DispatchTrackService;
import com.zhongdihang.framework.biz.jpa.service.impl.ServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by win10 on 2017/08/17.
 */
@Service
public class DispatchTrackServiceImpl extends ServiceBean<Long, DispatchTrack, DispatchTrackDao> implements DispatchTrackService
{
    @Autowired
    private AssessDao assessDao;
    @Autowired
    private DispatchDao dispatchDao;
    @Autowired
    private PreReportDao preReportDao;
    @Autowired
    private FileDao fileDao;

    @Override
    public Dispatch dispatchUpdate(int Status,Long dispatchId,Long fileId)
    {
        Dispatch dispatch = dispatchDao.findOne(dispatchId);
        PreReport preReport=new PreReport();
        preReport.setFile(fileDao.findOne(fileId));
        preReport.setCreateTime(new Date());
        preReport.setPredict("1");
        preReport.setDispatch(dispatch);
        //==========================================
        //1.进行中2.已完成，3.有问题
        //==========================================
        switch (Status)
        {
            case 2:
                dispatch.setStatus("2");
                break;
            case 3:
                dispatch.setStatus("3");
                break;
        }
        preReportDao.save(preReport);
        return dispatchDao.save(dispatch);
    }

    @Override
    public DispatchTrack save(DispatchTrack dispatchTrack) {
        return getDao().save(dispatchTrack);
    }

    @Override
    public List<DispatchTrack> listDispatchTrack(Long dispatchId) {
        return getDao().findByDispatchId(dispatchId);
    }
}

