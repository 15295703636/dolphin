package org.cs.dp.sonar.domain;

import lombok.Data;
import org.cs.dp.radar.api.entity.RestPartyReq;

/**
 * @ClassName RestPartyReqBean
 * @Description TODO
 * @Author Liujt
 * @Date 2020/2/25 11:54
 **/
@Data
public class RestPartyReqBean extends RestPartyReq {
    private Long yxs_id;
}
