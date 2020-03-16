package org.cs.dp.sonar.domain;

import lombok.Data;
import org.cs.dp.radar.api.entity.RestLiveStreamingReq;

/**
 * @ClassName RestLiveStreamingReqBean
 * @Description 开启直播入参
 * @Author Liujt
 * @Date 2020/3/2 17:03
 **/
@Data
public class RestLiveStreamingReqBean extends RestLiveStreamingReq {
    private String courser_id;
}
