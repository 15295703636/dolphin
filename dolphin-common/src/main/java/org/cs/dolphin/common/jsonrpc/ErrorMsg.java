package org.cs.dolphin.common.jsonrpc;


import org.cs.dolphin.common.utils.StringUtil;

public class ErrorMsg
{
	private int code;
	
	private Object message;

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public Object getMessage()
	{
		return message;
	}

	public void setMessage(Object message)
	{
		this.message = message;
	}
	
	public String toString()
	{
		return StringUtil.beanToString(this);
	}
}
