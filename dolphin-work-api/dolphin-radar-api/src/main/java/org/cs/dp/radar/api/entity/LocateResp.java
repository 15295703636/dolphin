package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:04
 */
public class LocateResp {
    private String client_ip	;//	客户端的IP地址	-
    private int err_code	;//	错误码	值为0时表示成功
    private String err_info	;//	错误信息	-
    private String guid	;//	与请求体中的guid相同	-
    private String is_private	;//	-	值为"true"时表示客户端在内网，值为"false"时表示客户端在外网。
    private String more_info	;//	补充信息	-

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public String getErr_info() {
        return err_info;
    }

    public void setErr_info(String err_info) {
        this.err_info = err_info;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getIs_private() {
        return is_private;
    }

    public void setIs_private(String is_private) {
        this.is_private = is_private;
    }

    public String getMore_info() {
        return more_info;
    }

    public void setMore_info(String more_info) {
        this.more_info = more_info;
    }
}
