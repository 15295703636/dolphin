package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dp.sonar.domain.entity.InteractionEntity;

import java.util.List;

/**
 * @ClassName AddInteractionBean
 * @Description 添加互动入参
 * @Author Liujt
 * @Date 2019/12/9 15:17
 **/
@Data
@ApiModel(value = "添加互动入参")
public class AddInteractionBean {
    @ApiModelProperty(value = "互动主信息")
    private InteractionEntity interaction;

    @ApiModelProperty(value = "设备ID")
    private List<Integer> device_ids;
}
