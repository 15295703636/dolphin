package org.cs.dolphin.common.base;

public class RequestPage<SplitPageInfo, T2> {

    SplitPageInfo page;

    T2 info;

    public SplitPageInfo getPage() {
        return page;
    }

    public void setPage(SplitPageInfo page) {
        this.page = page;
    }

    public T2 getInfo() {
        return info;
    }

    public void setInfo(T2 info) {
        this.info = info;
    }

	public RequestPage() {
	}

	public RequestPage(SplitPageInfo page, T2 info) {
		this.page = page;
		this.info = info;
	}
}