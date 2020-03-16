package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName GetAppSchBean
 * @Description 查看预约日程入参
 * @Author Liujt
 * @Date 2019/12/9 16:17
 **/
@Data
@ApiModel(value = "查看预约日程入参")
public class GetScheduleBean {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "时间")
    private String date;

    @ApiModelProperty(value = "类型 1会议 2互动 3录播")
    private String type;

    @ApiModelProperty(value = "组织ID")
    private Integer org_id;

    @ApiModelProperty(value = "租户ID", hidden = true)
    private Integer customer_id;

    @ApiModelProperty(value = "设备id", hidden = true)
    private List deviceIds;

    @ApiModelProperty(value = "日程名称,端名称")
    private String name;

    public GetScheduleBean() {
    }

    public GetScheduleBean(Integer id) {
        this.id = id;
    }
}
