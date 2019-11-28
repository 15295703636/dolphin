package org.cs.dp.radar.api.entity;

import java.util.List;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:42
 */
public class RestPartyMru {
    private String mruName	;
    List<RestParty> parties;

    public String getMruName() {
        return mruName;
    }

    public void setMruName(String mruName) {
        this.mruName = mruName;
    }

    public List<RestParty> getParties() {
        return parties;
    }

    public void setParties(List<RestParty> parties) {
        this.parties = parties;
    }
}
