package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:16
 */
public class RestFileUrl {
    private long fileId	;//	文件的ID	-
    private String url	;//	文件的下载链接	-
    private String use	;//	该文件下载链接的用途	取值见常量：文件链接的用途

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }
}
