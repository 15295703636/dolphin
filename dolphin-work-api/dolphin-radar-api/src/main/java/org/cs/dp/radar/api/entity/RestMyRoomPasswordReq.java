package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:26
 */
public class RestMyRoomPasswordReq {
    private String password	;//	新密码	要求是12位以内的数字

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
