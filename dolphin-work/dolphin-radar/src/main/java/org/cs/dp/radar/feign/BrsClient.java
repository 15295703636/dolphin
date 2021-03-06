package org.cs.dp.radar.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.brs.BssTaskReq;
import org.cs.dp.radar.api.feign.IBrsClient;
import org.cs.dp.radar.service.IBrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 流媒体接口调用
 */
@RestController
public class BrsClient implements IBrsClient {

    @Autowired
    IBrsService iBrsService;

    @Override
    @GetMapping(API_PREFIX+"/sendmsg")
    public ReturnInfo sendmsg(@RequestParam(name = "msg") String msg,@RequestParam(name = "method") String method) {
        System.out.println("brs="+msg);
        return iBrsService.sendmsg(msg,method);
    }

    @Override
    @GetMapping(API_PREFIX+"/sendmsgtopic")
    public ReturnInfo sendmsgtopic(@RequestParam(name = "msg") String msg,
                                   @RequestParam(name = "method") String method,
                                   @RequestParam(name = "queue") String queue) {
        return iBrsService.sendmsgtopic(msg,method,queue);
    }

    @Override
    @PostMapping(API_PREFIX+"/login")
    public ReturnInfo login(@RequestBody BssTaskReq bssTaskReq,@RequestParam(name = "queue") String queue) {
        return iBrsService.login(bssTaskReq, queue);
    }

    @Override
    @PostMapping(API_PREFIX+"/starttask")
    public ReturnInfo startTask(BssTaskReq bssTaskReq, String queue) {
        return iBrsService.startTask(bssTaskReq, queue);
    }

    @Override
    @PostMapping(API_PREFIX+"/stoptask")
    public ReturnInfo stopTask(BssTaskReq bssTaskReq, String queue) {
        return iBrsService.stopTask(bssTaskReq, queue);
    }

    @Override
    @PostMapping(API_PREFIX+"/querytask")
    public ReturnInfo queryTask(BssTaskReq bssTaskReq, String queue) {
        return iBrsService.queryTask(bssTaskReq, queue);
    }

    @Override
    @GetMapping(API_PREFIX+"/deletetask")
    public ReturnInfo deleteTaskTask(BssTaskReq bssTaskReq, String queue) {
        return iBrsService.deleteTaskTask(bssTaskReq, queue);
    }

    @Override
    @GetMapping(API_PREFIX+"/uploadfile")
    public ReturnInfo uploadFile(BssTaskReq bssTaskReq, String queue) {
        return iBrsService.uploadFile(bssTaskReq, queue);
    }

    @Override
    @GetMapping(API_PREFIX+"/uploadthum")
    public ReturnInfo uploadThumbnail(BssTaskReq bssTaskReq, String queue) {
        return iBrsService.uploadThumbnail(bssTaskReq, queue);
    }

    @Override
    @GetMapping(API_PREFIX+"/catthum")
    public ReturnInfo catThumbnail(BssTaskReq bssTaskReq, String queue) {
        return iBrsService.catThumbnail(bssTaskReq, queue);
    }


}
