package com.zhongdihang.bankdispatch.modular.controller;

import com.zhongdihang.bankdispatch.modular.domain.DispatchTrack;
import com.zhongdihang.bankdispatch.modular.service.DispatchTrackService;
import com.zhongdihang.bankdispatch.modular.service.dto.DispatchTrackDto;
import com.zhongdihang.bankdispatch.rest.ResultModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/9/2 19:34
 * @Modified By：
 * @Version: V1.0.0
 */
@RestController
@RequestMapping("/dispatchTrack")
@Api(description = "派单跟踪控制器")
public class DispatchTrackController {

    @Autowired
    private DispatchTrackService dispatchTrackService;

    @GetMapping("/list")
    public ResultModel getDispatchTrackInfo(Long dispatchId){
        List<DispatchTrack> dispatchTrackList= dispatchTrackService.listDispatchTrack(dispatchId);
        List<DispatchTrackDto> dispatchTrackDtos=dispatchTrackList
                .stream()
                .map(t->new DispatchTrackDto(true,t.getNode(),t.getRemark(),t.getCreateTime()))
                .collect(Collectors.toList());
        return ResultModel.ok(dispatchTrackDtos);
    }
}
