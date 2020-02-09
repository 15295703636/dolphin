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
    public ReturnInfo sendmsg(@RequestParam String msg,@RequestParam String method) {
        System.out.println("brs="+msg);
        return iBrsService.sendmsg(msg,method);
    }

    @Override
    @GetMapping(API_PREFIX+"/sendmsgtopic")
    public ReturnInfo sendmsgtopic(@RequestParam String msg,@RequestParam String method,@RequestParam String queue) {
        return iBrsService.sendmsgtopic(msg,method,queue);
    }

    @Override
    @PostMapping(API_PREFIX+"/login")
    public ReturnInfo login(@RequestBody BssTaskReq bssTaskReq,@RequestParam String queue) {
        return iBrsService.login(bssTaskReq, queue);
    }

    @Override
    @PostMapping(API_PREFIX+"/starttask")
    public ReturnInfo startTask(@RequestBody BssTaskReq bssTaskReq, @RequestParam String queue) {
        return iBrsService.startTask(bssTaskReq, queue);
    }

    @Override
    @PostMapping(API_PREFIX+"/stoptask")
    public ReturnInfo stopTask(@RequestBody BssTaskReq bssTaskReq,@RequestParam  String queue) {
        return iBrsService.stopTask(bssTaskReq, queue);
    }

    @Override
    @PostMapping(API_PREFIX+"/querytask")
    public ReturnInfo queryTask(@RequestBody BssTaskReq bssTaskReq,@RequestParam  String queue) {
        return iBrsService.queryTask(bssTaskReq, queue);
    }

    @Override
    @PostMapping(API_PREFIX+"/deletetask")
    public ReturnInfo deleteTaskTask(@RequestBody BssTaskReq bssTaskReq,@RequestParam  String queue) {
        return iBrsService.deleteTaskTask(bssTaskReq, queue);
    }

    @Override
    @PostMapping(API_PREFIX+"/uploadfile")
    public ReturnInfo uploadFile(@RequestBody BssTaskReq bssTaskReq,@RequestParam  String queue) {
        return iBrsService.uploadFile(bssTaskReq, queue);
    }

    @Override
    @PostMapping(API_PREFIX+"/uploadthum")
    public ReturnInfo uploadThumbnail(@RequestBody BssTaskReq bssTaskReq,@RequestParam String queue) {
        return iBrsService.uploadThumbnail(bssTaskReq, queue);
    }

    @Override
    @PostMapping(API_PREFIX+"/catthum")
    public ReturnInfo catThumbnail(@RequestBody BssTaskReq bssTaskReq,@RequestParam  String queue) {
        return iBrsService.catThumbnail(bssTaskReq, queue);
    }


}
