package org.cs.dp.sonar.domain;

import lombok.Data;
import org.cs.dp.radar.api.entity.brs.BssTaskProgram;

/**
 * @ClassName QueryTaskResBean
 * @Description 查询流媒体信息返回实体
 * @Author Liujt
 * @Date 2020/3/3 15:41
 **/
@Data
public class QueryTaskResBean {
    private Integer code;
    private BssTaskProgram data;
}
