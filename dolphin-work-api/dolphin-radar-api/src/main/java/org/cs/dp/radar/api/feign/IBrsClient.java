package org.cs.dp.radar.api.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.utils.AppConstant;
import org.cs.dp.radar.api.entity.brs.BssTaskReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = AppConstant.APPLICATION_RADAR_NAME,
        fallback = IBaioClientFallBack.class)
public interface IBrsClient {
    String API_PREFIX = "/brs";


    @GetMapping(API_PREFIX+"/sendmsg")
    ReturnInfo sendmsg(@RequestParam(name = "msg") String msg, @RequestParam(name = "method") String method);
    @GetMapping(API_PREFIX+"/sendmsgtopic")
    ReturnInfo sendmsgtopic(@RequestParam(name = "msg") String msg,
                            @RequestParam(name = "method") String method,
                            @RequestParam(name = "queue") String queue);

    @PostMapping(API_PREFIX+"/login")
    ReturnInfo login(@RequestBody BssTaskReq bssTaskReq,@RequestParam(name = "queue") String queue);

    @PostMapping(API_PREFIX+"/starttask")
    ReturnInfo startTask(@RequestBody BssTaskReq bssTaskReq,@RequestParam(name = "queue") String queue);
    @PostMapping(API_PREFIX+"/stoptask")
    ReturnInfo stopTask(@RequestBody BssTaskReq bssTaskReq,@RequestParam(name = "queue") String queue);
    @PostMapping(API_PREFIX+"/querytask")
    ReturnInfo queryTask(@RequestBody BssTaskReq bssTaskReq,@RequestParam(name = "queue") String queue);
    @GetMapping(API_PREFIX+"/deletetask")
    ReturnInfo deleteTaskTask(@RequestBody BssTaskReq bssTaskReq,@RequestParam(name = "queue") String queue);
    @GetMapping(API_PREFIX+"/uploadfile")
    ReturnInfo uploadFile(@RequestBody BssTaskReq bssTaskReq,@RequestParam(name = "queue") String queue);
    @GetMapping(API_PREFIX+"/uploadthum")
    ReturnInfo uploadThumbnail(@RequestBody BssTaskReq bssTaskReq,@RequestParam(name = "queue") String queue);
    @GetMapping(API_PREFIX+"/catthum")
    ReturnInfo catThumbnail(@RequestBody BssTaskReq bssTaskReq,@RequestParam(name = "queue") String queue);


}
