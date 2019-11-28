package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:10
 */
public class RestPartyActionResult {
    private String partyName	;//	与会者的名称	-
    private String partyGuid	;//	与会者的guid	-
    private String errorCode	;//	错误码	-
    private String errorMessage	;//	错误信息

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyGuid() {
        return partyGuid;
    }

    public void setPartyGuid(String partyGuid) {
        this.partyGuid = partyGuid;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
