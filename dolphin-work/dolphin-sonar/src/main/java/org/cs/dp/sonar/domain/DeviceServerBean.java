package org.cs.dp.sonar.domain;

import lombok.Data;
import org.cs.dp.sonar.domain.entity.DeviceEntity;

/**
 * @ClassName DeviceServerBean
 * @Description
 * @Author Liujt
 * @Date 2020/3/4 10:45
 **/
@Data
public class DeviceServerBean extends DeviceEntity {
    private Integer customer_id;
}
