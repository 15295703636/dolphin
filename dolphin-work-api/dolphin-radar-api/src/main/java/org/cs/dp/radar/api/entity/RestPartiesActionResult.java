package org.cs.dp.radar.api.entity;

import java.util.List;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 11:11
 */
public class RestPartiesActionResult {
    private long partyCount	;//	与会者的数量	-
    private long failedPartyActionCount	;//	控制失败的与会者的数量	-
    private List<RestPartyActionResult> failedPartyActions	;//	控制失败的结果列表

    public long getPartyCount() {
        return partyCount;
    }

    public void setPartyCount(long partyCount) {
        this.partyCount = partyCount;
    }

    public long getFailedPartyActionCount() {
        return failedPartyActionCount;
    }

    public void setFailedPartyActionCount(long failedPartyActionCount) {
        this.failedPartyActionCount = failedPartyActionCount;
    }

    public List<RestPartyActionResult> getFailedPartyActions() {
        return failedPartyActions;
    }

    public void setFailedPartyActions(List<RestPartyActionResult> failedPartyActions) {
        this.failedPartyActions = failedPartyActions;
    }
}
