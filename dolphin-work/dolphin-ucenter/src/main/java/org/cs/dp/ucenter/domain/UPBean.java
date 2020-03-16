package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.cs.dp.ucenter.common.Constant;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName PostTestBean
 * @Description 测试post方法实体
 * @Author Liujt
 * @Date 2019/11/19 19:10
 **/
@ApiModel(description = "用户名密码")
public class UPBean {

    @NotBlank(message = Constant.USER_LOGIN_MSG)
    @ApiModelProperty(value = "用户名称", required = true)
    private String userName;

    @NotBlank(message = Constant.PWD_LOGIN_MSG)
    @ApiModelProperty(value = "密码", required = true)
    private String passWord;

    public String getUserName() {
        return userName.trim();
    }

    public void setUserName(String userName) {
        this.userName = userName.trim();
    }

    public String getPassWord() {
        return passWord.trim();
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord.trim();
    }
}
