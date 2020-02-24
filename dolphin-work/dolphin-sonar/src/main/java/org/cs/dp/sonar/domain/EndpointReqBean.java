package org.cs.dp.sonar.domain;

import lombok.Data;
import org.cs.dp.radar.api.entity.RestEndpointReq;

/**
 * @ClassName EndpointReqBean
 * @Description 云视讯添加端实体
 * @Author Liujt
 * @Date 2020/2/24 13:47
 **/
@Data
public class EndpointReqBean extends RestEndpointReq {
    private Long ysx_id;
}
