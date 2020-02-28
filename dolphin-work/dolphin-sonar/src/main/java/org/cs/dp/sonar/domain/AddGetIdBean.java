package org.cs.dp.sonar.domain;

import lombok.Data;

/**
 * @ClassName AddGetIdBean
 * @Description TODO
 * @Author Liujt
 * @Date 2020/2/27 14:55
 **/
@Data
public class AddGetIdBean {
    private Integer id;
    private Integer newId;

    public AddGetIdBean() {
    }

    public AddGetIdBean(Integer id) {
        this.id = id;
    }
}
