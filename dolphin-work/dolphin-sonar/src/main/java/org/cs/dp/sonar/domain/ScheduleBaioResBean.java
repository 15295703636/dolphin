package org.cs.dp.sonar.domain;

import lombok.Data;

import java.util.List;

/**
 * @ClassName ScheduleBaioResBean
 * @Description
 * @Author Liujt
 * @Date 2020/3/9 15:51
 **/
@Data
public class ScheduleBaioResBean {
    private String type;
    private String name;
    private String start_time;
    private String end_time;
    private String lecturer;
    private String conference_number;
    private String lecturer_device;
    private List<String> participants;

    public ScheduleBaioResBean() {
    }
}
