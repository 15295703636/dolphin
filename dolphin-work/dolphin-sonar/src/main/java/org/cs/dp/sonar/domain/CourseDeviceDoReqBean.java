package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName CourseDeviceDoReqBean
 * @Description 进行中的日程调用云视讯对端操作的实体封装
 * @Author Liujt
 * @Date 2020/2/25 13:52
 **/
@Data
@ApiModel(value = "进行中的日程调用云视讯对端操作的实体封装")
public class CourseDeviceDoReqBean {
    private Long ysx_course_id;

    private Long ysx_device_id;
}
