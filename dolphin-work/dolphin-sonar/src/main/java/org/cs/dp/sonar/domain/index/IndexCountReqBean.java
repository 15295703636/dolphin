package org.cs.dp.sonar.domain.index;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;

/**
 * @ClassName IndexCountResBean
 * @Description
 * @Author Liujt
 * @Date 2020/3/6 13:32
 **/
@Data
public class IndexCountReqBean {
    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "时间端类型 1:7天 2:半个月 3:一个月")
    private Integer type;

    private Integer customer_id = ThreadLocalUserInfoUtil.get().getCustomer_id();
}
