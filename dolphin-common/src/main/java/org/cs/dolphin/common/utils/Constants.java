package org.cs.dolphin.common.utils;

public interface Constants {

    /**
     * 数据库存放时的日期字符串格式
     */
    String DATE_PATTERN = "yyyyMMddHHmmss";

    /**
     * 数据库中记录日志开始和结束时间时使用的日期字符串格式
     */
    String DATE_LOG_PATTERN = "yyyyMMddHHmmssSSS";

    /**
     * 安全日志中的操作时间从数据库中取出的格式
     */
    String LOG_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 界面输入的日期字符串格式
     */
    String INPUT_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 页面显示的日期字符串格式"yyyy-MM-dd HH:mm:ss"
     */
    String SHOW_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 分页当前显示页的标识
     */
    String SHOW_PAGE_NUMBER = "showPage";

    /**
     * 分页每页记录数标识
     */
    String PAGE_ROW_NUMBER = "pageRowNum";

    /**
     * request中存放分页信息的标识
     */
    String REQUEST_PAGE_INFO = "page";

    /**
     * 分页时默认的页面显示记录个数
     */
    int DEFAULT_PAGE_ROW_NUMBER = 15;

    /**
     * title:保存用户的登录信息或密码
     */
    String IS_SAVE_USERINFO = "1";

    /**
     * 保存密码
     */
    String IS_SAVE_PASSWORD = "1";

    /**
     * title: 用户的密码是否是手动输入的
     */
    String IS_INPUT = "1";

    String OPERATOR = "operator";

    /**
     * 操作码
     */
    String OPERCODE = "opercode";

    /**
     * 加密密钥
     */
    String KEY = "vmediax";

    /**
     * 标志是否登录成功的key
     */
    String SESSIONKEY = "vmediax";

    /**
     * 美元分割符
     */
    String SPLIT_CHAR_DOLLER = "$";

    /**
     * 保存登录信息
     */
    String SAVEINFO = "saveInfo";

    /**
     * 保存密码
     */
    String SAVEPWD = "savePwd";

    /**
     * 本地语言key
     */
    String LOCAL_LANGUAGE_KEY = "WW_TRANS";

    /**
     * 验证码
     */
    String VERIRY_CODE = "VERIRY_CODE";

    /**
     * 页面登录
     */
    String LOGINTYPE_WEB = "0";

    /**
     * sso登录
     */
    String LOGINTYPE_SSO = "1";

    /**
     * 登录方式key
     */
    String LOGINTYPEKEY = "loginType";

    /**
     * 失败页面提示信息，拼接的key值前缀
     */
    String COMMON_ERROR = "error.";

    /**
     * 行分隔符
     */
    String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * 目录分隔符
     */
    String FILE_SEAPRATOR = System.getProperty("file.separator");

    /**
     * 当前系统的编码字符集
     */
    String FILE_ENCODING = System.getProperty("file.encoding");

    /**
     * 字符串分隔"\6"
     */
    String SPLIT_CHAR = "\6";

    /**
     * 字符串分隔 Tab键
     */
    String TAB = "\t";

    /** 用来存入当前用户语言 */
    /**
     * ThreadLocal langThreadLocal = new ThreadLocal();
     */
    ThreadLocal<String> LANGTHREADLOCAL = new ThreadLocal<String>();

    /**
     * 记录文件字段之间的分隔符
     */
    String RECORD_FIELD_SEP = "|";

    /**
     * 日志类型info
     */
    String LOG_TYPE_INFO = "INFO";

    /**
     * 日志类型warn
     */
    String LOG_TYPE_WARN = "WARN";

    /**
     * 日志类型error
     */
    String LOG_TYPE_ERROR = "ERROR";

    /**
     * 日志类型debug
     */
    String LOG_TYPE_DEBUG = "DEBUG";

    /**
     * 日志系统CMS前台
     */
    String LOG_SYSTEM_CMS = "1";

    /**
     * 日志系统CMS管理
     */
    String LOG_SYSTEM_CMS_ADMIN = "2";

    /**
     * 日志系统录制管理
     */
    String LOG_SYSTEM_RMS = "3";

    /**
     * 授权文件
     */
    String LIC_DIR = "license/lic/";

    //产品名称
    public static final String SYSTEM_PRODUCT_NAME = "System.ProductName";

    //MAC地址
    public static final String BIND = "Bind";

    //授权时间
    public static final String EXPIRE = "Expire";

    //序列号
    public static final String SN = "System.SerialNumber";

    //公司名称
    public static final String CNAME = "Manufacture.Name";

    //公司URL
    public static final String CURL = "Manufacture.WebURL";

    //公司邮件
    public static final String CMAIL = "Manufacture.SupportEmail";

    public static final String LIC_Y = "yes";
}
