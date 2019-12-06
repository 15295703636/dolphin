package org.cs.dp.ucenter.domain;

import java.util.List;

/**
 * @ClassName TreeNodeBean
 * @Description TODO
 * @Author Liujt
 * @Date 2019/12/6 13:41
 **/
public class TreeNodeBean {
    private String name;// 名称
    private String id;// id
    private String pid;//父ID
    private List<TreeNodeBean> child;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeNodeBean> getChild() {
        return child;
    }

    public void setChild(List<TreeNodeBean> child) {
        this.child = child;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
