package org.cs.dp.sonar.domain;

import lombok.Data;

/**
 * @ClassName CourseSaveResBean
 * @Description 开启课程返回结果
 * @Author Liujt
 * @Date 2020/3/7 10:38
 **/
@Data
public class CourseSaveResBean {
    /**
     * 处理结果
     */
    private boolean result_sign;
    /**
     * 会议类型
     */
    private Integer type;
    /**
     * 直播标志
     */
    private String isLive;
    /**
     * 日程名称
     */
    private String name;
    /**
     * 日程id
     */
    private Integer course_id;
    /**
     * 错误信息
     */
    private String error_msg;
}
