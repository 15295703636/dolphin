package org.cs.dolphin.common.base;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.cs.dolphin.common.utils.StringUtil;

import java.io.Serializable;

@ApiModel(value = "分页参数")
public class SplitPageInfo implements Serializable {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 2831003536460215740L;

    /**
     * 开始记录数
     */
    @ApiModelProperty(value = "开始记录数")
    protected int beginNum = 0;

    /**
     * 结束记录数
     */
    @ApiModelProperty(value = "结束记录数")
    protected int endNum;

    /**
     * 分页查询的URL
     */
    @ApiModelProperty(value = "")
    private String queryURL;

    /**
     * 记录总数
     */
    @ApiModelProperty(value = "记录总数(前端不传这个字段)")
    private int totals;

    /**
     * 每页显示记录数
     */
    @ApiModelProperty(value = "每页显示记录数")
    private int perPageNum;

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private int allPage;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private int currPage;

    /**
     * 构造
     */
    public SplitPageInfo() {
    }

    /**
     * 取得查询的总记录数
     *
     * @return int 查询的总记录数
     */
    public int getTotals() {
        return this.totals;
    }

    /**
     * 设置查询的总记录数
     *
     * @param totals int 查询的总记录数
     */
    public void setTotals(int totals) {
        this.totals = totals;

        //重新计算
        this.doPage();
    }

    /**
     * 取得每页记录数
     *
     * @return int 每页记录数
     */
    public int getPerPageNum() {
        return this.perPageNum;
    }

    /**
     * 设置每页记录数
     *
     * @param aperpagenum int 每页记录数
     */
    public void setPerPageNum(int aperpagenum) {
        this.perPageNum = aperpagenum;
    }

    /**
     * 得到当前页信息
     *
     * @return int 当前页码
     */
    public int getCurrPage() {
        if (currPage == 0) {
            currPage = 1;
        }
        return currPage;
    }

    /**
     * 设置当前页码
     *
     * @param currPage int 当前页码
     */
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    /**
     * 取得总页数
     *
     * @return int 总页数
     */
    public int getAllPage() {
        return allPage;
    }

    /**
     * 设置总页数
     *
     * @param allPage int 总页数
     */
    public void setAllPage(int allPage) {
        if (allPage == 0) {
            this.allPage = 1;
        } else {
            this.allPage = allPage;
        }
    }

    /**
     * 取得分页查询的URL
     *
     * @return String 分页查询的URL
     */
    public String getQueryURL() {
        return queryURL;
    }

    /**
     * 设置分页查询的URL
     *
     * @param queryURL String 分页查询的URL
     */
    public void setQueryURL(String queryURL) {
        this.queryURL = queryURL;
    }

    /**
     * 计算总页数,开始记录数及结束记录数
     */
    public void doPage() {

        // 计算总页数
        this.setAllPage((this.totals + this.perPageNum - 1) / this.perPageNum);

        if (currPage > allPage) {
            currPage = allPage;
        }
        // 计算开始记录数
        this.beginNum = (this.currPage - 1) * this.perPageNum;
        // 计算结束记录数
        this.endNum = (beginNum + perPageNum) < totals ? beginNum + perPageNum : totals;

    }

    public void setBeginNum(int beginNum) {
        this.beginNum = beginNum;
    }

    /**
     * 取得分页记录起始数
     *
     * @return int 分页记录起始数
     */
    public int getBeginNum() {
        return beginNum;
    }

    /**
     * 取得分页记录结束数
     *
     * @return int 分页记录结束数
     */
    public int getEndNum() {
        return endNum;
    }


    public void setEndNum(int endNum) {
        this.endNum = endNum;
    }

    /**
     * toString
     *
     * @return String 本类对象的描述信息
     */
    public String toString() {
        return StringUtil.beanToString(this);
    }
}
