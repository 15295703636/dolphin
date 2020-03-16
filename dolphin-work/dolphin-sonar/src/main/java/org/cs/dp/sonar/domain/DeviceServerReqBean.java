package org.cs.dp.sonar.domain;

import lombok.Data;

/**
 * @ClassName DeviceServerStartBean
 * @Description 端调用平台创建录播课入参
 * @Author Liujt
 * @Date 2020/3/4 10:17
 **/
@Data
public class DeviceServerReqBean {
    /**
     * 操作标志
     */
    private String subtype;
    /**
     * 任务名称
     */
    private String task_name;
    /**
     * 设别序列号
     */
    private String sn;
}
