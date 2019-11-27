package org.cs.dolphin.common.exception;

/**
 * 异常码常量接口
 */
public interface MessageCode {
    /**
     * 用户状态失效
     */
    long USER_SESSION = 4401;
    /**
     * 本次操作的结果标识，成功：1000；失败：1001；数据不存在：1002 (如需扩展再引申)
     */
    long COMMON_SUCCEED_FLAG = 1000;

    /**
     * 本次操作的结果标识，成功：1000；失败：1001；数据不存在：1002 (如需扩展再引申)
     */
    long COMMON_FAILURE_FLAG = 1001;

    /**
     * 本次操作的结果标识，成功：1000；失败：1001；数据不存在：1002 (如需扩展再引申)
     */
    long COMMON_NOT_EXIST_FLAG = 1002;

    /**
     * 其它异常
     */
    long OTHER_EXCEPTION = 1003;

    /**
     * 数据正常
     */
    long COMMON_DATA_NORMAL = 1004;

    /**
     * 数据异常
     */
    long COMMON_DATA_UNNORMAL = 1005;

    /**
     * 本次操作的结果标识，成功：1000；失败：1001；数据不存在：1002  数据已经存在：1006(如需扩展再引申)
     */
    long COMMON_EXIST_FLAG = 1006;

    /**
     * 数据库异常
     */
    long DB_EXCEPTION = 1100;

    /**
     * 与数据库通讯异常
     */
    long DB_CONNECTION_EXCEPTION = 1102;

    /**
     * 与中心节点数据库同步异常
     */
    long DB_SYNCHRO_EXCEPTION = 1200;

    /**
     * 会话超时
     */
    long SESSION_TIMEOUT = 1701;

    /**
     * 验证码错误
     */
    long VERIFYCODE_ERROR = 1702;

    /**
     * 验证码初始化失败
     */
    long VERIFYCODE_INIT_ERROR = 1703;

    /**
     * 用户不存在
     */
    long OPERATOR_IS_INEXISTENT = 1705;

    /**
     * 密码错误
     */
    long PASSWORD_IS_ERROR = 1707;

    /**
     * 验证码校验失败
     */
    long VERIFIYCODE_CHECK_ERROR = 1710;

    /**
     * 开始时间应该小于结束时间
     */
    long STARTTIME_ENDTIME_ERROR = 1711;

    /**
     * 输入参数非法
     */
    long INPUT_PARAM_INVALID = 1712;

    /**
     * IP地址或者端口号不正确
     */
    long IP_OR_PORT_INVALID = 1713;

    /**
     * 登录失败
     */
    long LOGIN_FAILED = 1714;

    //*******************************  Edu Control Platform Begin ************************************
    /**
     * 终端不存在
     */
    long Fail_Peer_Not_Exist = 2001;

    /**
     * 终端不在会议中
     */
    long Fail_Peer_Not_In_Conference = 2002;

    /**
     * 终端已添加过
     */
    long Fail_Peer_Already_In_Conference = 2003;

    /**
     * 终端无法呼叫
     */
    long Fail_Peer_Cannot_Call = 2004;

    /**
     * 终端已经呼叫
     */
    long Fail_Peer_Already_Called = 2005;

    /**
     * 终端无法挂断
     */
    long Fail_Peer_Cannot_Hangup = 2006;

    /**
     * 终端不在呼叫中
     */
    long Fail_Peer_No_Called = 2007;

    /**
     * 会议不存在或者已经结束
     */
    long Fail_Conference_Not_Exist = 2008;

    /**
     * 系统超过负荷
     */
    long Fail_Sys_Overload = 2009;

    /**
     * 连接互动服务失败
     */
    long Fail_Conn_EduContrl_Platform = 2010;

    /**
     * 跟互动服务交互失败
     */
    long Fail_Inter_EduContrl_Platform = 2011;

    /**
     * 该操作没有完全成功,一部分失败
     */
    long COMMON_Not_Completed_SUCCEED = 2012;

    /**
     * 创建会议失败
     */
    long Fail_Create_Conference = 2013;

    /**
     * 添加教室失败
     */
    long Fail_Add_Room = 2014;

    /**
     * 呼叫教室失败
     */
    long Fail_Call_Room = 2015;

    /**
     * 挂断教室失败
     */
    long Fail_Hangup_Room = 2016;

    /**
     * 删除教室失败
     */
    long Fail_Del_Room = 2017;
    //*******************************  Edu Control Platform  End **************************************

    //*******************************  Device Control Begin  ************************************
    /**
     * 跟XMPP服务器连接失败
     */
    long Fail_Connect_XMPPServer = 3001;

    /**
     * 跟XMPP服务交互失败
     */
    long Fail_Inter_XMPPServer = 3002;

    /**
     * 跟存储服务器连接失败
     */
    long Fail_Connect_StoreServer = 3003;

    /**
     * 跟存储服务器交互失败
     */
    long Fail_Inter_StoreServer = 3004;

    /**
     * 跟录制服务器连接失败
     */
    long Fail_Connect_RecordServer = 3005;

    /**
     * 跟录制服务器交互失败
     */
    long Fail_Inter_RecordServer = 3006;

    /**
     * 跟媒体服务器连接失败
     */
    long Fail_Connect_MediaServer = 3007;

    /**
     * 跟媒体服务器交互失败
     */
    long Fail_Inter_MediaServer = 3008;

    //*******************************  Device Control End  **************************************


    //*******************************  Classroom Platform Begin **********************************
    /**
     * 教室正在上课，不能删除
     */
    long Fail_Del_Classroom_By_Course = 4001;

    /**
     * 教室正在上课，不能被再次使用
     */
    long Fail_Create_Course_By_Using = 4002;

    /**
     * 设备名已经存在，不能被再次使用
     */
    long Fail_Dev_Name_Exist = 4003;

    /**
     * 选择的教室状态不正常
     */
    long Fail_Classroom_Checked_Invalid = 4004;

    /**
     * 教室冲突
     */
    long Fail_Classroom_Checked_Conflict = 4005;

    /**
     * 预约时间必须大于当前时间
     */
    long PlanTime_Must_Greater_Than_CurTime = 4006;

    /**
     * 设备已经添加过了
     */
    long Fail_Dev_Exist = 4007;
    //*******************************  Classroom Platform End ************************************
}
