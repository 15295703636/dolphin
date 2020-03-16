package org.cs.dolphin.common.jsonrpc;

import net.sf.json.JSONObject;
import org.cs.dolphin.common.utils.StringUtil;

import java.util.Map;


/**
 * 应答消息
 * @author hhpan
 *
 */
public class JsonRpcRsp extends JsonRpcMsg
{
	//error 和 result 不能同时包含
	private Object result;

	private ErrorMsg error;

	public Object getResult()
	{
		return result;
	}

	public void setResult(Object result)
	{
		this.result = result;
	}
		
	public ErrorMsg getError()
	{
		return error;
	}

	public void setError(ErrorMsg error)
	{
		this.error = error;
	}
	
	/**
	 * 将返回信息封装成JsonRpcRsp返回
	 * 如果返回的 result 字段的数据是复杂类型的，则可以采用该方法
	 * @param jsonStr
	 * @param resultClass
	 * @return
	 */
	public static JsonRpcRsp generateMsg(String jsonStr ,Class resultClass)
	{
		JsonRpcRsp rsp = new JsonRpcRsp();
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		JsonRpcRsp obj = (JsonRpcRsp) JSONObject.toBean(jsonObject, JsonRpcRsp.class);
		rsp.setId(obj.getId());
		rsp.setJsonrpc(obj.getJsonrpc());
		
		if( null != obj.getError())
		{
			JSONObject json = JSONObject.fromObject(obj.getError());
			ErrorMsg error = (ErrorMsg) JSONObject.toBean(json, ErrorMsg.class);
			rsp.setError(error);
		}
		
		if( null != obj.getResult())
		{
			//如果是 String 则不用转换
			if( obj.getResult() instanceof String)
			{
				rsp.setResult(obj.getResult());
			}
			else if( obj.getResult() instanceof Integer)
			{
				rsp.setResult(obj.getResult());
			}
			else
			{
				JSONObject json = JSONObject.fromObject(obj.getResult());
				Object result = JSONObject.toBean(json, resultClass);
				rsp.setResult(result);
			}			
		}	
		return rsp;
	}
	
	public static JsonRpcRsp generateMsg(String jsonStr ,Class resultClass,Map<String,Class> map)
	{
		JsonRpcRsp rsp = new JsonRpcRsp();
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		JsonRpcRsp obj = (JsonRpcRsp) JSONObject.toBean(jsonObject, JsonRpcRsp.class);
		rsp.setId(obj.getId());
		rsp.setJsonrpc(obj.getJsonrpc());
		
		if( null != obj.getError())
		{
			JSONObject json = JSONObject.fromObject(obj.getError());
			ErrorMsg error = (ErrorMsg) JSONObject.toBean(json, ErrorMsg.class);
			rsp.setError(error);
		}
		
		if( null != obj.getResult())
		{
			//如果是 String 则不用转换
			if( obj.getResult() instanceof String)
			{
				rsp.setResult(obj.getResult());
			}
			else if( obj.getResult() instanceof Integer)
			{
				rsp.setResult(obj.getResult());
			}
			else
			{
				JSONObject json = JSONObject.fromObject(obj.getResult());
				Object result = JSONObject.toBean(json, resultClass, map);
				rsp.setResult(result);
			}			
		}	
		return rsp;
	}
	
	/**
	 * 将返回信息封装成JsonRpcRsp返回
	 * 如果返回的 result 字段的数据是简单类型的，则可以采用该方法
	 * @param jsonStr
	 * @return
	 */
	public static JsonRpcRsp generateMsg(String jsonStr)
	{
		JsonRpcRsp rsp = new JsonRpcRsp();
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		JsonRpcRsp obj = (JsonRpcRsp) JSONObject.toBean(jsonObject, JsonRpcRsp.class);
		rsp.setId(obj.getId());
		rsp.setJsonrpc(obj.getJsonrpc());
		rsp.setResult(obj.getResult());
		
		if( null != obj.getError())
		{
			JSONObject json = JSONObject.fromObject(obj.getError());
			ErrorMsg error = (ErrorMsg) JSONObject.toBean(json, ErrorMsg.class);
			rsp.setError(error);
		}
		return rsp;
	}
	
	public String toString()
	{
		return super.toString()+ StringUtil.beanToString(this);
	}
}
