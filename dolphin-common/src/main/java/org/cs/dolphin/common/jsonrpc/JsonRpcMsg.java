package org.cs.dolphin.common.jsonrpc;

/**
 * json rpc 请求和应答消息的共同基类
 * @author 
 *
 */
public class JsonRpcMsg
{
	//默认是2.0
	private String jsonrpc = "1.0";
	
	//如果是 null ,则是公告消息，不需要应答的
	private Integer id = null;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getJsonrpc()
	{
		return jsonrpc;
	}

	public void setJsonrpc(String jsonrpc)
	{
		this.jsonrpc = jsonrpc;
	}
	
	public String toString()
	{
		return "Msg head:[id="+id+"; jsonrpc="+jsonrpc+"] ;";
	}
}
