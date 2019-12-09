package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dp.sonar.domain.entity.InteractionEntity;
import org.cs.dp.sonar.domain.entity.MeetingEntity;
import org.cs.dp.sonar.domain.entity.RecordBroadcastEntity;

import java.util.List;

/**
 * @ClassName GetAppSchBean
 * @Description 查看预约日程出参
 * @Author Liujt
 * @Date 2019/12/9 16:17
 **/
@Data
@ApiModel(value = "查看预约日程出参")
public class GetAppSchResBean {

    @ApiModelProperty(value = "会议")
    private List<MeetingEntity> meetings;

    @ApiModelProperty(value = "互动")
    private List<InteractionEntity> interactions;

    @ApiModelProperty(value = "录播")
    private List<RecordBroadcastEntity> rbs;

}
