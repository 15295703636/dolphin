package org.cs.dolphin.common.jsonrpc;

import net.sf.json.JSONObject;
import org.cs.dolphin.common.utils.StringUtil;

import java.util.Map;


public class JsonRpcReq extends JsonRpcMsg
{
	//方法名
	private String method;
	
	private Object params;
	
	//计数器
	private static int req = 0;
	
	public JsonRpcReq()
	{
		super.setId(generateId());
	}
	
	/**
	 * 生成序号
	 * @return
	 */
	protected int generateId()
	{
		return ++req;
	}
	
	public String getMethod()
	{
		return method;
	}

	public void setMethod(String method)
	{
		this.method = method;
	}

	public Object getParams()
	{
		return params;
	}

	public void setParams(Object params)
	{
		this.params = params;
	}
	
	/**
	 * 将请求消息转换成 json格式的字符串
	 * @param req
	 * @return
	 */
	public static String generateJsonStr(JsonRpcMsg req)
	{
		String jsonContent = JSONObject.fromObject(req).toString();
		return jsonContent;
	}
	
	/**
	 * 将请求信息封装成JsonRpcReq
	 * @param jsonStr
	 * @param resultClass
	 * @return
	 */
	public static JsonRpcReq generateMsg(String jsonStr ,Class resultClass)
	{
		JsonRpcReq req = new JsonRpcReq();
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		JsonRpcReq obj = (JsonRpcReq) JSONObject.toBean(jsonObject, JsonRpcReq.class);
		req.setId(obj.getId());
		req.setJsonrpc(obj.getJsonrpc());
		
		if( null != obj.getParams())
		{
			//如果是 String 则不用转换
			if( obj.getParams() instanceof String)
			{
				req.setParams(obj.getParams());
			}
			else if( obj.getParams() instanceof Integer)
			{
				req.setParams(obj.getParams());
			}
			else
			{
				JSONObject json = JSONObject.fromObject(obj.getParams());
				Object result = JSONObject.toBean(json, resultClass);
				req.setParams(result);
			}			
		}	
		return req;
	}
	
	public static JsonRpcReq generateMsg(String jsonStr ,Class resultClass,Map<String, Class> classMap)
	{
		JsonRpcReq req = new JsonRpcReq();
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		JsonRpcReq obj = (JsonRpcReq) JSONObject.toBean(jsonObject, JsonRpcReq.class);
		req.setId(obj.getId());
		req.setJsonrpc(obj.getJsonrpc());
		
		if( null != obj.getParams())
		{
			//如果是 String 则不用转换
			if( obj.getParams() instanceof String)
			{
				req.setParams(obj.getParams());
			}
			else if( obj.getParams() instanceof Integer)
			{
				req.setParams(obj.getParams());
			}
			else
			{
				JSONObject json = JSONObject.fromObject(obj.getParams());
				Object result = JSONObject.toBean(json, resultClass, classMap);
				req.setParams(result);
			}			
		}	
		return req;
	}
	
	/**
	 * 将返回信息封装成JsonRpcReq返回
	 * 如果返回的 result 字段的数据是简单类型的，则可以采用该方法
	 * @param jsonStr
	 * @return
	 */
	public static JsonRpcReq generateMsg(String jsonStr)
	{
		JsonRpcReq req = new JsonRpcReq();
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		JsonRpcReq obj = (JsonRpcReq) JSONObject.toBean(jsonObject, JsonRpcReq.class);
		req.setId(obj.getId());
		req.setJsonrpc(obj.getJsonrpc());
		req.setParams(obj.getParams());
		req.setMethod(obj.getMethod());
		return req;
	}
	
	public String toString()
	{
		return super.toString()+ StringUtil.beanToString(this);
	}
}
