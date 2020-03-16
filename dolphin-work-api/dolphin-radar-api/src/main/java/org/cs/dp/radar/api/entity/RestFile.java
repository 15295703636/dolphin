package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:14
 */
public class RestFile {
    private long id	;//	ID	-
    private String fileType	;//	文件类型	取值见常量：文件类型
    private long creatorUserId	;//	文件的创建者的ID	-
    private String creatorUserDisplayName	;//	文件的创建者的昵称	-
    private long createMillis	;//	该文件的创建时间，以毫秒为单位的Unix时间戳	-
    private long fileSizeInByte	;//	该文件的字节数	-
    private String confName	;//	会议名称	-
    private String confNumericId	;//	会议号	-
    private int downloadCount	;//	该文件的下载次数	-
    private String description	;//	备注

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public String getCreatorUserDisplayName() {
        return creatorUserDisplayName;
    }

    public void setCreatorUserDisplayName(String creatorUserDisplayName) {
        this.creatorUserDisplayName = creatorUserDisplayName;
    }

    public long getCreateMillis() {
        return createMillis;
    }

    public void setCreateMillis(long createMillis) {
        this.createMillis = createMillis;
    }

    public long getFileSizeInByte() {
        return fileSizeInByte;
    }

    public void setFileSizeInByte(long fileSizeInByte) {
        this.fileSizeInByte = fileSizeInByte;
    }

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public String getConfNumericId() {
        return confNumericId;
    }

    public void setConfNumericId(String confNumericId) {
        this.confNumericId = confNumericId;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
