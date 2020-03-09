package org.cs.dp.sonar.domain;

import lombok.Data;

/**
 * @ClassName DealStartResBean
 * @Description 处理其启动会议返回结果
 * @Author Liujt
 * @Date 2020/3/7 10:25
 **/
@Data
public class DealStartResBean {
    /**
     * 处理结果 true成功 false失败
     */
    private boolean result_sign;
    /**
     * 云视讯会议id
     */
    private Long ysx_course_id;
    /**
     * result_sign=false时，存放错误信息
     */
    private String error_msg;
}
