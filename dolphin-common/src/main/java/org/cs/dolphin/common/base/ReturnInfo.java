package org.cs.dolphin.common.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.StringUtil;

import java.io.Serializable;
import java.util.List;

public class ReturnInfo implements Serializable {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private long returnCode;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object returnData;

    private Object page;

    public static ReturnInfo build(long status, String msg, Object data) {
        return new ReturnInfo(status, msg, data);
    }

    public static ReturnInfo ok(Object data) {
        return new ReturnInfo(data);
    }

    public static ReturnInfo ok() {
        return new ReturnInfo(null);
    }

    public ReturnInfo() {
        returnCode = MessageCode.COMMON_SUCCEED_FLAG;
    }

    public ReturnInfo(SplitPageInfo page,List dataList) {
        this.page = page;
        this.returnData = dataList;
    }

    /**
     * 失败构造方法
     * @param code
     * @param msg
     * @return
     */
    public ReturnInfo(long code, String msg) {
        this.msg = msg;
        returnCode = code;
    }

    public static ReturnInfo build(long status, String msg) {
        return new ReturnInfo(status, msg, null);
    }

    public ReturnInfo(long status, String msg, Object data) {
        this.returnCode = status;
        this.msg = msg;
        this.returnData = data;
    }

    public ReturnInfo(Object data) {
        this.returnCode = 1000;
        this.msg = "OK";
        this.returnData = data;
    }


    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public long getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(long returnCode) {
        this.returnCode = returnCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getReturnData() {
        return returnData;
    }

    public void setReturnData(Object returnData) {
        this.returnData = returnData;
    }

    /**
     * 将json结果集转化为ReturnInfo对象
     *
     * @param jsonData json数据
     * @param clazz    ReturnInfo中的object类型
     * @return
     */
    public static ReturnInfo formatToBean(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ReturnInfo.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("returnData");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("returnCode").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static ReturnInfo format(String json) {
        try {
            return MAPPER.readValue(json, ReturnInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz    集合中的类型
     * @return
     */
    public static ReturnInfo formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("returnData");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("returnCode").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    public String toString() {
        return StringUtil.beanToString(this);
    }
}
