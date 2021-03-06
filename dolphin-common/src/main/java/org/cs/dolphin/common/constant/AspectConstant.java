package org.cs.dolphin.common.constant;

/**
 * @ClassName AspectConstant
 * @Description 切面常量类
 * @Author Liujt
 * @Date 2019/11/25 11:31
 **/
public class AspectConstant {

    //用户模块 日志切面扫描包路径, 在提供接口的服务中添加扫面路径
    public static final String USER_SERVER = "within(org.cs.dp.ucenter.controller..*) || within(org.cs.dp.ucenter.feign..*)";
    //Sonar模块 日志切面扫描包路径
    public static final String SONAR_SERVER = "within(org.cs.dp.sonar.controller..*)";

}
