
package org.cs.dolphin.common.exception;


public class BaseException extends Exception {
    /**
     * 序列化编号
     */
    private static final long serialVersionUID = 6677944836460917272L;

    String details = ""; //$NON-NLS-1$

    public BaseException() {
        super();
    }

    public BaseException(String s) {
        super(s);
    }

    public BaseException(String s, String d) {
        super(s);
        details = d;
    }

    /**
     * Returns a more details message(if any).
     */
    public String getDetailMessage() {
        return details;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return super.toString() + '[' + getDetailMessage() + ']';
    }
}
