package org.cs.dp.radar.api.entity;

/**
 * @author ：mzy
 * @date ：Created in 2019/11/26 10:45
 */
public class TerminateInfo {
    private boolean terminateBeforeFirstJoins;
    private int	beforeFirstJoinsInterval	;
    private boolean terminateAfterLastQuits	;
    private int afterLastQuitsInterval;

    public boolean isTerminateBeforeFirstJoins() {
        return terminateBeforeFirstJoins;
    }

    public void setTerminateBeforeFirstJoins(boolean terminateBeforeFirstJoins) {
        this.terminateBeforeFirstJoins = terminateBeforeFirstJoins;
    }

    public int getBeforeFirstJoinsInterval() {
        return beforeFirstJoinsInterval;
    }

    public void setBeforeFirstJoinsInterval(int beforeFirstJoinsInterval) {
        this.beforeFirstJoinsInterval = beforeFirstJoinsInterval;
    }

    public boolean isTerminateAfterLastQuits() {
        return terminateAfterLastQuits;
    }

    public void setTerminateAfterLastQuits(boolean terminateAfterLastQuits) {
        this.terminateAfterLastQuits = terminateAfterLastQuits;
    }

    public int getAfterLastQuitsInterval() {
        return afterLastQuitsInterval;
    }

    public void setAfterLastQuitsInterval(int afterLastQuitsInterval) {
        this.afterLastQuitsInterval = afterLastQuitsInterval;
    }
}
