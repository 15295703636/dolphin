package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:50
 */
public class VideoQuality {
    PeopleVideoQuality people	;//	-	-
    ContentVideoQuality content	;//	-	-
    boolean supportContentForLegacy	;//	是否向不支持双流的终端发送双流

    public PeopleVideoQuality getPeople() {
        return people;
    }

    public void setPeople(PeopleVideoQuality people) {
        this.people = people;
    }

    public ContentVideoQuality getContent() {
        return content;
    }

    public void setContent(ContentVideoQuality content) {
        this.content = content;
    }

    public boolean isSupportContentForLegacy() {
        return supportContentForLegacy;
    }

    public void setSupportContentForLegacy(boolean supportContentForLegacy) {
        this.supportContentForLegacy = supportContentForLegacy;
    }
}
