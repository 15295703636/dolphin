package org.cs.dp.sonar.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 服务
 */
@Data
@ApiModel(value = "服务管理实体")
public class ServerEntity {
    @ApiModelProperty(value = "ID")
    private Integer server_id;

    @ApiModelProperty(value = "服务名称")
    private String server_name;

    @ApiModelProperty(value = "服务类型,controlServer:互动控制平台;mediaServer:流媒体;storeServer:存储;recordServer:录制;xmpp：xmpp服务器")
    private String server_type;

    @ApiModelProperty(value = "服务地址")
    private String server_ip;

    @ApiModelProperty(value = "端口号")
    private Integer server_port;

    @ApiModelProperty(value = "描述")
    private String server_desc;

    @ApiModelProperty(value = "用于登录xmpp的用户名")
    private String XMPPAccount;

    @ApiModelProperty(value = "于登录xmpp的密码")
    private String XMPPPwd;

    @ApiModelProperty(value = "媒体模块")
    private Integer module_media;

    @ApiModelProperty(value = "互动模块")
    private Integer module_control;

    @ApiModelProperty(value = "存储模块")
    private Integer module_store;

    @ApiModelProperty(value = "录制模块")
    private Integer module_record;

    public ServerEntity(Integer server_id, String server_name, String server_type, String server_ip, Integer server_port, String server_desc, String XMPPAccount, String XMPPPwd, Integer module_media, Integer module_control, Integer module_store, Integer module_record) {
        this.server_id = server_id;
        this.server_name = server_name;
        this.server_type = server_type;
        this.server_ip = server_ip;
        this.server_port = server_port;
        this.server_desc = server_desc;
        this.XMPPAccount = XMPPAccount;
        this.XMPPPwd = XMPPPwd;
        this.module_media = module_media;
        this.module_control = module_control;
        this.module_store = module_store;
        this.module_record = module_record;
    }

}