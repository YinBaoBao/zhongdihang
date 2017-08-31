package com.zhongdihang.bankdispatch.modular.service;

import com.zhongdihang.bankdispatch.modular.domain.Dispatch;
import com.zhongdihang.bankdispatch.modular.domain.DispatchTrack;
import com.zhongdihang.bankdispatch.modular.domain.User;

/**
 * Created by win10 on 2017/08/17.
 */
public interface DispatchTrackService {


    //=====================================
    //更新单据状态
    //=====================================
    Dispatch dispatchUpdate(int Status,Long dispatchId,Long fileId);
}
