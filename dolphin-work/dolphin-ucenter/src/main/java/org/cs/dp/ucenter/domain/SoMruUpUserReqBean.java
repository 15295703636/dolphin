package org.cs.dp.ucenter.domain;

import lombok.Data;
import org.cs.dp.radar.api.entity.RestOrgUserReq;

/**
 * @ClassName SoMruUpUserReqBean
 * @Description 云视讯更改用户信息
 * @Author Liujt
 * @Date 2020/2/21 9:01
 **/
@Data
public class SoMruUpUserReqBean extends RestOrgUserReq {
    private String user_id;
}
