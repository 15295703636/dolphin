package org.cs.dp.sonar.domain.index;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName IndexCountResBean
 * @Description 首页统计历史返回
 * @Author Liujt
 * @Date 2020/3/6 14:09
 **/
@Data
@ApiModel(value = "首页统计历史返回")
public class IndexCountResBean {

    @ApiModelProperty(value = "首页统计历史图标返回")
    private Map<String,List<IndexCountDateResBean>> icons;

    @ApiModelProperty(value = "总量")
    private Map total;

    @ApiModelProperty(value = "首页统计历史图标返回")
    private List<IndexCountDateResBean> typeIcon;


}
