package org.cs.dp.ucenter.domain;

import lombok.Data;
import org.cs.dp.radar.api.entity.RestDeptReq;

/**
 * @ClassName SoMruUpDeptReqBean
 * @Description 云视讯更新组织参数封装
 * @Author Liujt
 * @Date 2020/3/7 14:20
 **/
@Data
public class SoMruUpDeptReqBean extends RestDeptReq {
    private Long ysx_id;
}
