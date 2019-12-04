package org.cs.dolphin.common.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;

import java.util.Date;

public class LogEntity {
    @ColumnWidth(10)
    @ExcelProperty("ID")
    private Long log_id;

    @ColumnWidth(10)
    @ExcelProperty("模块标志")
    private String log_module;

    @ColumnWidth(30)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty("记录时间")
    private Date log_time;

    @ColumnWidth(10)
    @ExcelProperty("操作用户")
    private String log_user_name = null != ThreadLocalUserInfoUtil.get() ? ThreadLocalUserInfoUtil.get().getUser_name() : null;

    @ColumnWidth(10)
    @ExcelProperty("日志类型")
    private String log_type;

    @ColumnWidth(10)
    @ExcelProperty("日志级别")
    private String log_level;

    @ColumnWidth(10)
    @ExcelProperty("请求ID")
    private String log_ip;

    @ColumnWidth(50)
    @ExcelProperty("请求Url")
    private String log_url;

    @ColumnWidth(100)
    @ExcelProperty("日志内容")
    private String log_content;

    public LogEntity( String log_module, String log_type,String log_level, String log_ip, String log_url, String log_content) {
        this.log_module = log_module;
        this.log_type = log_type;
        this.log_level = log_level;
        this.log_ip = log_ip;
        this.log_url = log_url;
        this.log_content = log_content;
    }

    public LogEntity(String log_module,String log_url, String log_content) {
        this.log_module = log_module;
        this.log_type = "error";
        this.log_level = "error";
        this.log_url = log_url;
        this.log_content = log_content;
    }

    public LogEntity() {
    }

    public LogEntity(Long log_id, String log_module, Date log_time, String log_user_name, String log_type, String log_level, String log_ip, String log_url, String log_content) {
        this.log_id = log_id;
        this.log_module = log_module;
        this.log_time = log_time;
        this.log_user_name = log_user_name;
        this.log_type = log_type;
        this.log_level = log_level;
        this.log_ip = log_ip;
        this.log_url = log_url;
        this.log_content = log_content;
    }

    public Long getLog_id() {
        return log_id;
    }

    public String getLog_module() {
        return log_module;
    }

    public Date getLog_time() {
        return log_time;
    }

    public String getLog_user_name() {
        return log_user_name;
    }

    public String getLog_type() {
        return log_type;
    }

    public String getLog_level() {
        return log_level;
    }

    public String getLog_ip() {
        return log_ip;
    }

    public String getLog_url() {
        return log_url;
    }

    public String getLog_content() {
        return log_content;
    }
}