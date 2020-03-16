package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:18
 */
public class RestFileFetchCodeValidationReq {
    private String fileUrl	;//	是	-	通过“复制链接”操作得到的文件链接
    private String fetchCode	;//	是	-	通过“复制链接”操作得到的文件提取码

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFetchCode() {
        return fetchCode;
    }

    public void setFetchCode(String fetchCode) {
        this.fetchCode = fetchCode;
    }
}
