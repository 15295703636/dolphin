package org.cs.dolphin.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 包装字符串的相关操作。提供字符串操作的相关接口。
 *
 * @version 1.0
 * @since 1.0
 */
public class StringUtil {
    /**
     * 1字节表示的最大数的16进制表示
     */
    public static final int MAX_8 = 0X000000FF;

    /**
     * 2字节表示的最大数的16进制表示
     */
    public static final int MAX_16 = 0X0000FF00;

    /**
     * 3字节表示的最大数的16进制表示
     */
    public static final int MAX_24 = 0X00FF0000;

    /**
     * 4字节表示的最大数的16进制表示
     */
    public static final int MAX_32 = 0XFF000000;

    /**
     * 16进制表示的字符串分隔符
     */
    public static final String STR_SEP = ":\t";

    /**
     * 左方括号标识
     */
    public static final String LEFT_BRACKET = "[";

    /**
     * 右方括号标识
     */
    public static final String RIGHT_BRACKET = "]";

    /**
     * 点号
     */
    public static final String DOT = ".";

    /**
     * 连接字段名称和字段值
     */
    public static final String LINK = ":";

    /**
     * 空格
     */
    public static final String BLANK = " ";

    /**
     * ' '
     */
    public static final char CHAR_BLANK = ' ';

    /**
     * byte
     */
    public static final String BYTE_TYPE = "byte";

    /**
     * char
     */
    public static final String CHAR_TYPE = "char";

    /**
     * short
     */
    public static final String SHORT_TYPE = "short";

    /**
     * int
     */
    public static final String INT_TYPE = "int";

    /**
     * long
     */
    public static final String LONG_TYPE = "long";

    /**
     * boolean
     */
    public static final String BOOLEAN_TYPE = "boolean";

    /**
     * float
     */
    public static final String FLOAT_TYPE = "float";

    /**
     * double
     */
    public static final String DOUBLE_TYPE = "double";

    /**
     * int
     */
    public static final String STRING_TYPE = "String";

    /**
     * StringBuffer
     */
    public static final String STRINGBUFFER_TYPE = "StringBuffer";

    /**
     * 打印的字段信息缩进级别
     */
    public static final int INDENT_NUM = 4;

    /**
     * 左边填充
     */
    public static final int FILL_LEFT = 0;

    /**
     * 右边填充
     */
    public static final int FILL_RIGHT = 1;

    /**
     * 整数所占的字节数
     */
    public static final int INT_LEN = 4;

    /**
     * 整数所占的字节数
     */
    public static final int SHORT_INT_LEN = 2;

    /**
     * 整数所占的字节数
     */
    public static final int INT_LEN1 = 1;

    /**
     * 代表转换出来的数无效
     */
    public static final int INVALID_FLAG = -1;

    /**
     * 字节型的值上限
     */
    public static final int MAX_BYTE = 256;

    /**
     * 表示整数2
     */
    public static final int INT_2 = 2;

    /**
     * 表示整数3
     */
    public static final int INT_3 = 3;

    /**
     * 表示整数4
     */
    public static final int INT_4 = 4;

    /**
     * 表示整数5
     */
    public static final int INT_5 = 5;

    /**
     * 表示整数6
     */
    public static final int INT_6 = 6;

    /**
     * 表示整数9
     */
    public static final int INT_9 = 9;

    /**
     * 表示整数8
     */
    public static final int INT_8 = 8;

    /**
     * 表示整数16
     */
    public static final int INT_16 = 16;

    /**
     * 表示整数24
     */
    public static final int INT_24 = 24;

    /**
     * 表示整数10
     */
    public static final int INT_10 = 10;

    /**
     * 表示整数32
     */
    public static final int INT_32 = 32;

    /**
     * 表示整数34
     */
    public static final int INT_34 = 34;

    /**
     * 表示整数38
     */
    public static final int INT_38 = 38;

    /**
     * 表示整数39
     */
    public static final int INT_39 = 39;

    /**
     * 表示整数44
     */
    public static final int INT_44 = 44;

    /**
     * 表示整数48
     */
    public static final int INT_46 = 46;

    /**
     * 表示整数58
     */
    public static final int INT_58 = 58;

    /**
     * 表示整数48
     */
    public static final int INT_59 = 59;

    /**
     * 表示整数60
     */
    public static final int INT_60 = 60;

    /**
     * 表示整数62
     */
    public static final int INT_62 = 62;

    /**
     * 表示整数48
     */
    public static final int INT_63 = 63;

    /**
     * 字符‘0’的整数表示
     */
    public static final int INT_48 = 48;

    /**
     * 字符‘A'的整数表示
     */
    public static final int INT_65 = 65;

    /**
     * 整数表示255
     */
    public static final int INT_255 = 255;

    /**
     * 表示整数256
     */
    private static final int INT_256 = 256;

    /**
     * 日志
     */
    private static final Log log = LogFactory.getLog(StringUtil.class);

    /**
     * 0xFF
     */
    private static final int INT_0XFF = 0XFF;

    // 标示整数15
    private static final int MAX_15 = 15;

    /**
     * ACII 中文空格
     */
    private static final int SPACAE_CH_ACII = 12288;

    private static final String[] HEX_DIGITS =
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 16进制编码函数setHex()使用的内部常量。
     */
    private static final byte[] HEX_NUMBER =
            {(byte) '0', (byte) '1', (byte) '2', (byte) '3', (byte) '4', (byte) '5', (byte) '6', (byte) '7', (byte) '8', (byte) '9',
                    (byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D', (byte) 'E', (byte) 'F'};

    /**
     * 防止外部实例化该类
     */
    private StringUtil() {
    }

    /**
     * 统计指定字符串中的指定字符个数
     *
     * @param strSource 指定的待统计字符串
     * @param c         待统计字符
     * @return 指定字符出现的次数
     */
    public static int countChar(String strSource, char c) {
        int iResult = 0;
        if (null == strSource)// 如果入参为null则返回0
        {
            return iResult;
        }

        int iCharIndex = -1;
        // 迭代查找每个字符出现位置并赋值给指定变量
        while ((iCharIndex = strSource.indexOf(c, iCharIndex + 1)) > -1) {
            iResult++;
        }
        return iResult;
    }

    /**
     * 格式化指定的字符串，根据提供的格式化后的字符串长度、用以填充的字符、 填充的位置来进行格式化
     *
     * @param src   待格式化的整数
     * @param ch    填充字符
     * @param iLen  格式化后的字符串长度
     * @param iFlag 标识填充位置
     * @return 格式化后的字符串，如果指定的字符串长度大于指定的长度，则直接返回
     */
    public static String format(int src, char ch, int iLen, int iFlag) {
        StringBuffer sbResult = new StringBuffer(iLen);
        String strSrc = Integer.toString(src);

        if (null == strSrc) // 如果指定的字符串为null，则直接填充指定个数的字符
        {
            for (int i = 0; i < iLen; i++) // 迭代填充字符
            {
                sbResult.append(ch);
            }
        } else if (strSrc.length() > iLen) // 如果待格式化的字符串长度大于，则截取后面的iLen长度
        {
            sbResult.append(strSrc.substring(strSrc.length() - iLen));
        } else
        // 如果待格式化的字符串长度小于指定长度则按指定的填充标志位进行填充
        {
            int iNeedCharNum = iLen - strSrc.length(); // 获取需要填充的字符数量
            if (FILL_LEFT == iFlag) // 如果填充指定字符在左边
            {
                sbResult = fillChar(sbResult, ch, iNeedCharNum);
                sbResult.append(strSrc);
            } else
            // 否则填充指定字符在右边
            {
                sbResult.append(strSrc);
                sbResult = fillChar(sbResult, ch, iNeedCharNum);
            }
        }

        return sbResult.toString();
    }

    /**
     * 格式化指定的字符串，根据提供的格式化后的字符串长度、用以填充的字符、 填充的位置来进行格式化
     *
     * @param strSrc 待格式化的字符串
     * @param ch     填充字符
     * @param iLen   格式化后的字符串长度
     * @param iFlag  标识填充位置
     * @return 格式化后的字符串，如果指定的字符串长度大于指定的长度，则直接返回
     */
    public static String format(String strSrc, char ch, int iLen, int iFlag) {
        StringBuffer sbResult = new StringBuffer(iLen);
        if (null == strSrc) // 如果指定的字符串为null，则直接填充指定个数的字符
        {
            for (int i = 0; i < iLen; i++) // 迭代填充字符
            {
                sbResult.append(ch);
            }
        } else if (strSrc.length() > iLen) // 如果待格式化的字符串长度大于指定的长度则直接返回原字符串
        {
            sbResult.append(strSrc);
        } else
        // 如果待格式化的字符串长度小于指定长度则按指定的填充标志位进行填充
        {
            int iNeedCharNum = iLen - strSrc.length(); // 获取需要填充的字符数量
            if (FILL_LEFT == iFlag) // 如果填充指定字符在左边
            {
                sbResult = fillChar(sbResult, ch, iNeedCharNum);
                sbResult.append(strSrc);
            } else
            // 否则填充指定字符在右边
            {
                sbResult.append(strSrc);
                sbResult = fillChar(sbResult, ch, iNeedCharNum);
            }
        }

        return sbResult.toString();
    }

    /**
     * 将指定的StringBuffer填充指定个数的指定字符
     *
     * @param sb   待填充的StringBuffer
     * @param ch   指定的填充字符
     * @param iNum 填充字符的个数
     * @return 填充后的StringBuffer，返回填充后的StringBuffer 主要为了使用链式表达式
     */
    private static StringBuffer fillChar(StringBuffer sb, char ch, int iNum) {
        if (null == sb)// 如果传进的入参为null，则新建一个实例
        {
            sb = new StringBuffer();
        }

        for (int i = 0; i < iNum; i++)// 填充iNum个指定的字符
        {
            sb.append(ch);
        }

        return sb;
    }

    /**
     * 对于字符串类型的值两侧增加单引号并在结尾添加逗号
     *
     * @param strValue 待格式化的字符串值
     * @return 格式化后的字符串值
     */
    public static String formatColumn(String strValue) {
        if (null == strValue)// 如果入参为null则直接返回null值
        {
            return null;
        }

        StringBuffer sbResult = new StringBuffer();
        sbResult.append("\'").append(strValue.trim()).append("\'");// 两侧添加单引号
        return sbResult.toString();
    }

    /**
     * 获取指定字符串的格式化表示
     *
     * @param strSrc   待格式化的字符
     * @param strLeft  将加在待格式化字符串左边的字符串
     * @param strRight 将加在待格式化字符串右边的字符串
     * @return 格式化后的字符
     */
    public static String format(String strSrc, String strLeft, String strRight) {
        StringBuffer sbResult = new StringBuffer();
        if (null != strSrc)// 如果待格式化字符为null
        {
            sbResult.append(strLeft).append(strSrc);
            sbResult.append(strRight);
        } else
        // 不为null的话
        {
            sbResult.append(strLeft).append(strRight);
        }
        return sbResult.toString();
    }

    /**
     * 返回字节的16进制表示
     *
     * @param pByte 传进的字节
     * @return 传进参数的16进制字符串表示
     */
    public static String toHexStr(byte pByte) {
        int iTmp = 0;
        if (pByte < 0)// 如果该字节表示小于零的数
        {
            iTmp = MAX_BYTE + pByte;
        } else
        // 如果该字节表示大于零的数
        {
            iTmp = pByte;
        }

        int iHigh = iTmp / INT_16;// 取高四位的整数表示
        int iLow = iTmp - iHigh * INT_16;// 低四位的整数表示
        char cHigh;// 存放高位的字符表示
        if (iHigh > INT_9)// 如果大于9的话用字母表示
        {
            cHigh = (char) ((iHigh - INT_10) + INT_65);
        } else
        // 否则直接用数字表示
        {
            cHigh = (char) (iHigh + INT_48);
        }

        char cLow;// 存放低四位的字符表示
        if (iLow > INT_9)// 如果大于9的话用字母表示
        {
            cLow = (char) ((iLow - INT_10) + INT_65);
        } else
        // 否则直接用数字表示
        {
            cLow = (char) (iLow + INT_48);
        }
        StringBuffer sbResult = new StringBuffer();
        sbResult.append(cHigh);
        sbResult.append(cLow);
        return sbResult.toString();
    }

    /**
     * 获取指定数组指定范围元素的字符串表示
     *
     * @param arrSrc 待获取16进制表示的源数组
     * @return 指定数组中指定范围元素的16进制字符表示
     */
    public static String getHex(byte[] arrSrc) {
        if (null == arrSrc) {
            return null;
        }

        return getHex(arrSrc, arrSrc.length);
    }

    /**
     * 获取指定数组指定范围元素的字符串表示
     *
     * @param arrSrc 待获取16进制表示的源数组
     * @param iEnd   待获取数组中元素的结束位置
     * @return 指定数组中指定范围元素的16进制字符表示
     */
    public static String getHex(byte[] arrSrc, int iEnd) {
        String strLine = "";// 存放每一行的数据
        StringBuffer sbResult = new StringBuffer();
        int iCount = 0;// 记录每行中已经打印的字节个数
        int iRow = 0;// 记录行号
        for (int l = 0; l < iEnd; l++)// 迭代处理每个待获取的字节
        {
            strLine += toHexStr(arrSrc[l]) + " ";// 组合每行中的获取字节的字符表示
            if (++iCount > MAX_15)// 如果每行中组合的字符超过15个
            {
                sbResult.append(++iRow + STR_SEP + strLine);
                sbResult.append(Constants.LINE_SEPARATOR);// 添加每行的换行符
                iCount = 0;
                strLine = "";
            }
        }

        if (iCount > 0)// 如果iCount大于零说明还有未添加到转换后的字符变量中的字符
        {
            sbResult.append(++iRow + STR_SEP + strLine);
            sbResult.append(Constants.LINE_SEPARATOR);// 添加每行的换行符
        }
        return sbResult.toString();
    }

    /**
     * 检验字符串的内容是否是在整型范围内的数字
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean checkString2Int(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    /**
     * 检验字符串的内容是否是在double范围内的数字
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean checkString2Double(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    /**
     * 根据输入的字符串来计算 文件的大小
     *
     * @param bytes
     * @return
     */
    public static String bytes2kb(String bytes) {
        try {
            BigDecimal filesize = new BigDecimal(bytes);
            BigDecimal megabyte = new BigDecimal(1024 * 1024);
            float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP)
                    .floatValue();
            if (returnValue > 1024) {
                BigDecimal gbyte = new BigDecimal(1024 * 1024 * 1024);
                returnValue = filesize.divide(gbyte, 2, BigDecimal.ROUND_UP)
                        .floatValue();
                return (returnValue + "GB");
            }
            if (returnValue > 1) {
                return (returnValue + "MB");
            } else {
                BigDecimal kilobyte = new BigDecimal(1024);
                returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP)
                        .floatValue();
                return (returnValue + "KB");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 检验字符串的内容是否是在整型范围内的非负整数
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean checkStringNotNegativeInt(String str) {
        int checkInt;
        try {
            checkInt = Integer.parseInt(str);
            if (0 > checkInt) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            log.error(e);
            return false;
        }

    }

    /**
     * 检验字符串的内容是否是在整形范围内的数字
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean checkString2Long(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            log.error(e);
            return false;
        }

    }

    /**
     * 检验字符串的内容是否是浮点格式
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean checkString2Float(String str) {
        float fResult = 0.0f;

        try {
            fResult = Float.parseFloat(str);
            if (fResult > 0) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            log.error(e);
            return false;
        }

    }

    /**
     * 给字符串去掉空格
     *
     * @param arg 字符串
     * @return String
     */
    public static String trim(String arg) {
        if (null == arg) {
            return null;
        } else {
            return arg.trim();
        }
    }

    /**
     * 检查字符串是否为空，字符串为null，或者长度为0都认为为空
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNull(String str) {
        if (null == str) {
            return true;
        }

        if (0 == str.trim().length()) {
            return true;
        }

        return false;
    }

    /**
     * 获取跟踪中的记录信息间的分隔符
     *
     * @return 跟踪中的记录信息间的分隔符
     */
    public static String getSeparator() {
        return StringUtil.format(Constants.RECORD_FIELD_SEP, BLANK, BLANK);
    }

    /**
     * 判断所传类型的字符串表示是否为简单类型
     *
     * @param strType 指定类型的字符串表示
     * @return 是简单类型则返回true，否则返回false
     */
    private static boolean isSimpleType(String strType) {
        boolean blnResult = true;
        if (!BOOLEAN_TYPE.equals(strType) && (!BYTE_TYPE.equals(strType)) && (!CHAR_TYPE.equals(strType))
                && (!SHORT_TYPE.equals(strType)) && (!FLOAT_TYPE.equals(strType)))// 如果不是几种基本类型
        {
            if ((!DOUBLE_TYPE.equals(strType)) && (!STRING_TYPE.equals(strType))
                    && (!STRINGBUFFER_TYPE.equals(strType)) && (!INT_TYPE.equals(strType)) && (!LONG_TYPE.equals(strType))) {
                blnResult = false;
            }
        }
        return blnResult;
    }

    /**
     * 删除指定字符串指定位置的连续指定字符
     *
     * @param strSrc 指定的源字符串
     * @param c      指定的待删除字符
     * @param iLoc   指定的位置
     * @return 返回删除指定字符后的字符串
     */
    public static String trim(String strSrc, char c, int iLoc) {
        StringBuffer sbResult = new StringBuffer(strSrc);
        // char ctmp = '\0';
        if (FILL_LEFT == iLoc)// 删除左边
        {
            while (c == (sbResult.charAt(0))) {
                sbResult.deleteCharAt(0);
                // System.out.println(sbResult.length());
            }
        } else if (FILL_RIGHT == iLoc)// 删除右边
        {
            while (c == sbResult.charAt(sbResult.length() - 1)) {
                sbResult.deleteCharAt(sbResult.length() - 1);
            }
        }

        return sbResult.toString();
    }

    /**
     * 从消息体中获得beginning之后， separator之间的字段
     *
     * @param msg       消息体。
     * @param beginning 字段开始位置
     * @param separator 分隔标志
     * @return 取得的字段
     */
    public static String getValueFromMsg(String msg, String beginning, char separator) {
        String res = null;
        String msgtmp = null;
        if (StringUtil.isNull(msg) || null == beginning) {
            return res;
        }

        int part = msg.indexOf(beginning);// beginning的位置
        if (-1 != part) {
            msgtmp = msg.substring(part);
            int part1 = msgtmp.indexOf(separator);
            if (-1 != part1) {
                msgtmp = msgtmp.substring(part1 + 1);// 去掉第一个分隔符
                int part2 = msgtmp.indexOf(separator);
                if (-1 != part2) {
                    res = msgtmp.substring(0, part2);// 去掉第二个分隔符
                }
            }
        }

        return StringUtil.trim(res);
    }

    /**
     * 从QueryString中获得name字段
     *
     * @param queryString HTTP地址中的queryString。
     * @param name        字段名
     * @return 字段值
     */
    public static String getQueryStr(String queryString, String name) {
        if ((isNull(queryString)) || (isNull(name))) {
            return null;
        }
        String tmp = null;
        int part = queryString.indexOf(name + "=");// 是否包含name=
        if (-1 == part) {
            return tmp;
        }

        tmp = queryString.substring(part + name.length() + 1).trim();// 去掉name=
        int part1 = tmp.indexOf("&");// 是否有&
        if (-1 != part1)// "&"存在
        {
            tmp = tmp.substring(0, part1).trim();// 去掉&之后的
        }

        return tmp;
    }

    /**
     * 二行制转字符串
     *
     * @param b byte[]
     * @return String
     */
    public static String byte2hexstr(byte[] b) {
        StringBuffer sbRst = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & INT_0XFF);
            if (stmp.length() == 1) {
                sbRst.append("0").append(stmp);
            } else {
                sbRst.append(stmp);
            }
        }

        return toUpperCase(sbRst.toString());
    }

    /**
     * 校验IP
     *
     * @param ip String
     * @return boolean
     */
    public static boolean isValidIPAddress(String ip) {
        StringTokenizer token = new StringTokenizer(ip, ".");
        int intToken[] = new int[INT_4];
        String s = "";
        for (int i = INT_3; i >= 0; i--) {
            if (token.hasMoreTokens()) {
                s = token.nextToken();
                if (s.length() > 1 && "0".equals(s.substring(0, 1))) {
                    return false;
                }
                intToken[i] = Integer.parseInt(s);
                if (intToken[i] > INT_255 || intToken[i] < 0) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * 是否为合法的IP
     *
     * @param ip IP地址
     * @return 是否为合法IP
     */
    public static boolean isValidIP(String ip) {
        StringTokenizer token = new StringTokenizer(ip, ".");
        int intToken[] = new int[INT_4];
        for (int i = INT_3; i >= 0; i--) {
            if (token.hasMoreTokens()) {
                intToken[i] = Integer.parseInt(token.nextToken());
                // 修改IP地址格式的校验，允许输入
                if (intToken[i] > INT_255 || intToken[i] < 0) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * 转换数字IP为字符串IP
     *
     * @param ip 数字IP
     * @return 字符串IP
     */
    public static String ip2String(int ip) {
        int i;
        String str;
        i = (ip >> INT_24) & INT_0XFF;
        str = i + ".";
        i = (ip >> INT_16) & INT_0XFF;
        str += i + ".";
        i = (ip >> INT_8) & INT_0XFF;
        str += i + ".";
        i = ip & INT_0XFF;
        str += i;
        return str;
    }

    /**
     * 转换IP为数字
     *
     * @param ip 需要转换的ip
     * @return IP数字
     */
    public static int getIntIP(String ip) {
        int ipaddr;
        StringTokenizer token = new StringTokenizer(ip, ".");
        int intToken[] = new int[INT_4];

        for (int i = INT_3; i >= 0; i--) {
            if (token.hasMoreTokens()) {
                intToken[i] = Integer.parseInt(token.nextToken());
            }
        }
        try {
            ipaddr = (intToken[0]) | (intToken[1] << INT_8) | (intToken[INT_2] << INT_16) | (intToken[INT_3] << INT_24);
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("error");
            return -1;
        }
        return ipaddr;
    }

    /**
     * 转换IP为数字
     *
     * @param ip 需要转换的ip
     * @return IP数字
     */
    public static long getLongIP(String ip) {
        long ipaddr;
        StringTokenizer token = new StringTokenizer(ip, ".");
        long intToken[] = new long[INT_4];

        for (int i = INT_3; i >= 0; i--) {
            if (token.hasMoreTokens()) {
                intToken[i] = Integer.parseInt(token.nextToken());
            }
        }
        try {
            ipaddr = (intToken[0]) | (intToken[1] << INT_8) | (intToken[INT_2] << INT_16) | (intToken[INT_3] << INT_24);
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("error");
            return -1;
        }
        return ipaddr;
    }

    /**
     * 比较两个IP的大小
     *
     * @param ip1 比较ip1
     * @param ip2 比较ip2
     * @return ip1>ip2 返回true; ip1<=ip2返回false;
     */
    public static boolean compareIP(String ip1, String ip2) {
        int intIp1 = getIntIP(ip1.trim());
        int intIp2 = getIntIP(ip2.trim());
        return ((intIp1 >= 0) && (intIp2 < 0)) ? false : (intIp2 >= 0 && intIp1 < 0) ? true : (intIp1 > intIp2);
    }

    /**
     * 将输入流转换为字符串输出
     *
     * @param is InputStream
     * @return String
     */
    public static String toString(InputStream is) {
        StringBuffer str = new StringBuffer("");

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();

            while (line != null) {
                str = str.append(line + "\n");
                line = br.readLine();
            }
        } catch (IOException e) {
            // e.printStackTrace();
            return null;
        }
        return str.toString();
    }

    /**
     * 将字符串str转换成InputStream
     *
     * @param str String
     * @return InputStream
     */
    public static InputStream transStream(String str) {
        return new java.io.ByteArrayInputStream(str.getBytes());
    }

    /**
     * 由Content-Type得到boundary
     *
     * @param contentType String
     * @return String
     */
    public static String getBoundary(String contentType) {
        StringBuffer sb = new StringBuffer("--");
        int index = contentType.indexOf("boundary=");
        String tmp;
        if (index == -1) {
            return null;
        } else {
            tmp = contentType.substring(index);
            int k = tmp.indexOf(";");
            if (k == -1) {
                k = tmp.indexOf("\r\n");
                if (k == -1) {
                    sb.append(contentType.substring(index + INT_9));
                    return sb.toString();
                }
            }
            sb.append(contentType.substring(index + INT_9, index + k));
        }
        return sb.toString();
    }

    /**
     * 返回时间格式:HH:MM:SS
     *
     * @param sTime String
     * @return 时间格式
     */
    public static String stringToTime(String sTime) {
        if (sTime.length() != INT_6) {
            return sTime;
        }
        return sTime.substring(0, INT_2) + ":" + sTime.substring(INT_2, INT_4) + ":" + sTime.substring(INT_4, INT_6);
    }

    /**
     * byteArrayToHexString
     *
     * @param b byte[]
     * @return String
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuffer result = new StringBuffer("");
        for (int i = 0; i < b.length; i++) {
            result = result.append(byteToHexString(b[i]));
        }
        return result.toString();
    }

    /**
     * byteToHexString
     *
     * @param b byte
     * @return String
     */
    public static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = INT_256 + n;
        }
        int d1 = n / INT_16;
        int d2 = n % INT_16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }

    /**
     * 在字节数组中查找某个字节的位置。
     *
     * @param src 被查询的字节数组。
     * @param b   待查询的字节。
     * @return 返回查找到的位置，如果没找到返回-1。
     */
    public static int indexOf(byte[] src, byte b) {
        return indexOf(src, 0, src.length, b);
    }

    /**
     * 在字节数组的一个片段中查找某个字节的位置。
     *
     * @param src    被查询的字节数组。
     * @param offset 从哪个位置开始查找。
     * @param length 查找的长度。
     * @param b      待查询的字节。
     * @return 返回查找到的位置，如果没找到返回-1。
     */
    public static int indexOf(byte[] src, int offset, int length, byte b) {
        if (src == null) {
            return -1;
        }
        if (src.length < offset + length) {
            return -1;
        }
        if (offset > src.length - 1 || offset < 0) {
            return -1;
        }
        for (int i = offset; i < offset + length; i++) {
            if (src[i] == b) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 在字节数组的一个片段中查找某个字节的位置，但忽略包含在双引号对里的。
     *
     * @param src    被查询的字节数组。
     * @param offset 从哪个位置开始查找(offset>=0，包括该位置)。
     * @param length 查找的长度。
     * @param b      待查询的字节。
     * @return 返回查找到的位置，如果没找到返回-1。
     */
    public static int indexOfBesideQuote(byte[] src, int offset, int length, byte b) {

        boolean beside = true; // 当前搜索位置在引号对外面
        if (src == null) {
            return -1;
        }
        if (src.length < offset + length) {
            return -1;
        }
        if (offset > src.length - 1 || offset < 0) {
            return -1;
        }
        for (int i = offset; i < offset + length; i++) {
            byte cur = src[i];
            // if (cur == (byte)'\"') { //遇到一个引号进入，再遇到一个引号出来
            if ((cur == (byte) '\"') && (i == offset || (src[i - 1] != (byte) '\\'))) { // 遇到一个非转义的引号进入，再遇到一个非转义的引号出来
                beside = !beside;
                continue;
            }
            if (cur == b && beside) { // 在引号外发现匹配字符
                return i;
            }
        }
        return -1;
    }

    /**
     * 在字符串中查找某个字符的位置，但忽略包含在双引号对里的。
     *
     * @param src    被查询的字符串。
     * @param offset 从哪个位置开始查找(包括该位置)(offset>=0，包括该位置)。
     * @param c      待查询的字符。
     * @return 返回查找到的位置，如果没找到返回-1。
     */
    public static int indexOfBesideQuote(String src, int offset, char c) {

        boolean beside = true; // 当前搜索位置在引号对外面
        if (src == null) {
            return -1;
        }
        int srcLength = src.length();
        if (offset >= srcLength || offset < 0) {
            return -1;
        }
        for (int i = offset; i < srcLength; i++) {
            char cur = src.charAt(i);
            // if (cur == '\"') { //遇到一个引号进入，再遇到一个引号出来
            if ((cur == '\"') && ((i == offset) || (src.charAt(i - 1) != '\\'))) { // 遇到一个非转义的引号进入，再遇到一个非转义的引号出来
                beside = !beside;
                continue;
            }
            if (cur == c && beside) { // 在引号外发现匹配字符
                return i;
            }
        }
        return -1;
    }

    /**
     * 在字符串中查找某个子字符串的位置，但忽略包含在双引号对里的。
     *
     * @param src        被查询的字符串。
     * @param offset     从哪个位置开始查找(包括该位置)(offset>=0，包括该位置)。
     * @param sub        String
     * @param ignoreCase boolean
     * @return 返回查找到的位置，如果没找到返回-1。
     */
    public static int indexOfBesideQuote(String src, int offset, String sub, boolean ignoreCase) {

        boolean beside = true; // 当前搜索位置在引号对外面
        if (src == null) {
            return -1;
        }
        int srcLength = src.length();
        int subLength = sub.length();
        if (offset >= srcLength || offset < 0) {
            return -1;
        }
        for (int i = offset; i < srcLength; i++) {
            char cur = src.charAt(i);
            // if (cur == '\"') { //遇到一个引号进入，再遇到一个引号出来
            if ((cur == '\"') && (i == offset || (src.charAt(i - 1) != '\\'))) { // 遇到一个非转义的引号进入，再遇到一个非转义的引号出来
                beside = !beside;
                continue;
            }
            if (beside && src.regionMatches(ignoreCase, i, sub, 0, subLength)) {
                return i; // 在引号外发现匹配字符
            }
        }
        return -1;
    }

    /**
     * 在字符串中查找某个字符的位置，但忽略包含在双引号对里的。
     *
     * @param src    被查询的字符串。
     * @param offset 从哪个位置开始向前查找(offset>=0，包括该位置)。
     * @param c      待查询的字符。
     * @return 返回查找到的位置，如果没找到返回-1。
     */
    public static int lastIndexOfBesideQuote(String src, int offset, char c) {

        boolean beside = true; // 当前搜索位置在引号对外面
        if (src == null) {
            return -1;
        }
        int srcLength = src.length();
        if (offset > srcLength - 1 || offset < 0) {
            return -1;
        }
        for (int i = offset; i >= 0; i--) {
            char cur = src.charAt(i);
            // if (cur == '\"') { //遇到一个引号进入，再遇到一个引号出来
            if ((cur == '\"') && (i == 0 || (src.charAt(i - 1) != '\\'))) { // 遇到一个非转义的引号进入，再遇到一个非转义的引号出来
                beside = !beside;
                continue;
            }
            if (cur == c && beside) { // 在引号外发现匹配字符
                return i;
            }
        }
        return -1;
    }

    /**
     * 比较字节数组data1中从start1到end1（包括start1,不包括end1）内容和 data2是否相同。
     *
     * @param data1   待比较的字节数组1。
     * @param offset1 字节数组1比较开始的位置。
     * @param data2   待比较的字节数组2。
     * @param offset2 字节数组2比较开始的位置。
     * @param length  比较的长度。
     * @return boolean
     */
    public static boolean equals(byte[] data1, int offset1, byte[] data2, int offset2, int length) {
        for (int i = 0; i < length; i++) {
            if (data1[offset1 + i] != data2[offset2 + i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将字节数组的一部分按16进制ASCII字符0~9abcdef编码组合成整数。
     *
     * @param src    待解析内容的字节数组。
     * @param offset 待解析内容起始位置（包含该位置）。
     * @param length 待解析内容的长度。
     * @return int
     */
    public static int parseHex(byte[] src, int offset, int length) {
        int result = 0;
        for (int i = offset; i < offset + length; i++) {
            int h = src[i];
            if (h >= '0' && h <= '9') {
                h -= '0'; // 0~9
            } else if (h >= 'A' && h <= 'F') {
                h -= 'A' - INT_10; // A~F
            } else if (h >= 'a' && h <= 'f') {
                h -= 'a' - INT_10; // a~f
            } else {
                throw new NumberFormatException();
            }
            result = (result << INT_4) + h;
        }
        return result;
    }

    /**
     * 将整数按16进制字符编码放到字节数组某个位置（0~9，a~f小写字母）。
     *
     * @param src     保存16进制字符的字节数组。
     * @param lowByte 最低字节的位置（注意是最低字节的位置，在地址高字节端）。
     * @param value   待转换成16进制数的整数值。
     */
    public static void setHex(byte[] src, int lowByte, int value) {
        for (int i = 0; i < INT_8; i++) {
            src[lowByte - i] = HEX_NUMBER[value & INT_0XFF]; // 取低4位
            value >>>= INT_4; // 将value逻辑右移4bit
            if (value == 0) {
                break;
            }
        }
    }

    /**
     * 将字节数组的一部分取出，组成一个单独的字节数组。
     *
     * @param b      所取内容所在数组。
     * @param offset 所取内容起始位置。
     * @param length 所取内容的长度。
     * @return 新的字节数组拷贝。
     */
    public static byte[] subarray(byte[] b, int offset, int length) {
        byte[] sub = new byte[length];
        System.arraycopy(b, offset, sub, 0, length);
        return sub;
    }

    /**
     * 去掉字符串首尾的空格和双引号。（引号内部的引号和空格均不变）
     *
     * @param str 前后可能带有双引号和空格的字符串。
     * @return 去掉后的字符串。若输入为null或字符全被去掉了，返回""， 任何情况均不返回null。
     */
    public static String trimQuote(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        // 去掉参数值前后的空格和引号
        int begin = 0; // 包括该位置
        int end = str.length() - 1; // 包括该位置
        begin = trimgetBegin(str, begin, end);
        end = trimgetEnd(str, begin, end);
        if (begin <= end) {
            return str.substring(begin, end + 1);
        }
        return "";
    }

    /**
     * 获取开头位置，去掉空格，单双引号
     *
     * @param str   源字符串
     * @param begin 开头位置
     * @param end   结尾位置
     * @return 开头位置
     * @see [类、类#方法、类#成员]
     */
    private static int trimgetEnd(String str, int begin, int end) {
        while (begin <= end && str.charAt(end) == ' ') { // 去掉所有末尾空格
            end--;
        }
        if (begin <= end && str.charAt(end) == '\"') { // 只去一次末尾双引号
            end--;
        } else if (begin <= end && str.charAt(end) == '\'') { // 只去一次末尾单引号
            end--;
        }
        return end;
    }

    /**
     * 获取结尾位置，去掉空格，单双引号
     *
     * @param str   源字符串
     * @param begin 开头位置
     * @param end   结尾位置
     * @return 结尾位置
     * @see [类、类#方法、类#成员]
     */
    private static int trimgetBegin(String str, int begin, int end) {
        while (begin <= end && str.charAt(begin) == ' ') { // 去掉所有开头空格
            begin++;
        }
        if (begin <= end && str.charAt(begin) == '\"') { // 只去一次开头双引号
            begin++;
        } else if (begin <= end && str.charAt(begin) == '\'') { // 只去一次开头单引号
            begin++;
        }
        return begin;
    }

    /**
     * 查找替换字符串中的子串。
     *
     * @param text    待处理字符串。
     * @param find    待替换的子串。
     * @param replace 替换成的子串。
     * @return 返回text替换后的结果。
     */
    public static String replace(String text, String find, String replace) {
        if (text == null || find == null || replace == null) {
            return text;
        }
        int findLen = find.length();
        int textLen = text.length();
        if (textLen == 0 || findLen == 0) {
            return text;
        }
        StringBuffer sb = new StringBuffer();
        int begin = 0; // 下次检索开始的位置
        int i = text.indexOf(find); // 找到的子串位置
        while (i != -1) {
            sb.append(text.substring(begin, i));
            sb.append(replace);
            begin = i + findLen;
            i = text.indexOf(find, begin);
        }
        if (begin < textLen) {
            sb.append(text.substring(begin));
        }
        return sb.toString();
    }

    /**
     * 处理乱码。如果因字符集问题导致某字符串显示为乱码或问号，可用该方法搞一把， 可能会有效。该方法先判断字串中是否可能存在乱码，因此效率可能不佳，但比较 保险，不会把好字串变成乱码。
     *
     * @param str 可能出现乱码的字符串。
     * @return 更正后的字符串。
     */
    public static String correctEncoding(String str) {
        if (str == null) {
            return null;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > '\u00ff') {
                return str;
            }
        }
        try {
            return new String(str.getBytes("iso8859-1"));
        } catch (Exception ex) {
            return str;
        }
    }

    /**
     * toLowerCase
     *
     * @param str String
     * @return String
     */
    public static String toLowerCase(String str) {
        if (null == str) {
            return str;
        }
        return str.toLowerCase(Locale.getDefault());
    }

    /**
     * toUpperCase
     *
     * @param str String
     * @return String
     */
    public static String toUpperCase(String str) {
        if (null == str) {
            return str;
        }
        return str.toUpperCase(Locale.getDefault());
    }

    /**
     * 将Java中字符串转换成JS变量值 其实是将转义字符输出,并去掉字符串中回车换行
     *
     * @param str String
     * @return String
     */
    public static String toJsValue(String str) {
        if (null == str || "".equals(str)) {
            return str;
        }
        String hstr = str;
        // 转义斜线
        hstr = hstr.replaceAll("\\x5C", "\\\\\\\\");

        // 转义回车
        hstr = hstr.replaceAll("\r", "\\\\\\r");
        hstr = hstr.replaceAll("[\n]", "\\\\\\n");

        // 转义双引号
        hstr = hstr.replaceAll("\"", "\\\\\"");

        // 转义单引号
        hstr = hstr.replaceAll("\'", "\\\\\'");
        return hstr;
    }

    /**
     * ascii码大于255的字符长度按2位计算
     *
     * @param value String
     * @return int
     */
    public static int getDisplayLen(String value) {
        int i;
        int len;
        if (value == null) {
            return 0;
        }
        len = 0;
        for (i = 0; i < value.length(); i++) {
            if (value.charAt(i) > INT_255) {
                len += INT_2;
            } else {
                len++;
            }
        }
        return len;
    }

    /**
     * ascii码大于255的字符长度按3位计算
     *
     * @param value String
     * @return int
     */
    public static int getStrLen(String value) {
        int i;
        int len;
        if (value == null) {
            return 0;
        }
        len = 0;
        for (i = 0; i < value.length(); i++) {
            if (value.charAt(i) > INT_255) {
                len += INT_3;
            } else {
                len++;
            }
        }
        return len;
    }

    /**
     * 截取字符. ascii码大于255的字符长度按3位计算
     *
     * @param htmlValue  字符串
     * @param displayLen 截取的长度(单位:字节 )
     * @return String 截取后的字符串
     * @author zhanghao or rongyuehua
     */
    public static String getSubStr(String htmlValue, int displayLen) {
        int i;
        int len;

        // 最后一个字符的位置
        int pol = 0;
        if (null == htmlValue || "".equals(htmlValue)) {
            return htmlValue;
        }
        len = 0;
        for (i = 0; i < htmlValue.length(); i++) {
            if (htmlValue.charAt(i) > INT_255) {
                len += INT_3;
            } else {
                len++;
            }

            if (len > displayLen) {
                break;
            }
            pol = i;
        }

        return htmlValue.substring(0, pol + 1);

    }

    /**
     * 页面上显示长度超长时,通过些方法过滤
     *
     * @param htmlValue  String
     * @param displayLen int
     * @return String
     */
    public static String getDisplayStr(String htmlValue, int displayLen) {
        if (htmlValue == null) {
            return "";
        }
        // if (displayLen < 64)
        // {
        // displayLen = 64;
        // }
        int len = getDisplayLen(htmlValue);

        int subLen = len;

        if (len > displayLen) {
            subLen = getsubLen(len, displayLen, htmlValue);
            int point = 0;
            for (int i = 0; i < htmlValue.length(); i++) {
                char c = htmlValue.charAt(i);
                if (point >= subLen) {
                    subLen = i;
                    break;
                }
                if (c > INT_255) {
                    point += INT_2;
                } else {
                    point++;
                }
            }

        }

        if (subLen < len) {
            if (subLen < htmlValue.length()) {
                return htmlValue.substring(0, subLen) + "...";
            }

        }
        return htmlValue;
    }

    /**
     * 字符串长度大于页面显示长度，取得需要截取的长度 <功能详细描述>
     *
     * @param len        字符串长度
     * @param displayLen 页面显示长度
     * @param htmlValue  页面展示值
     * @return 需要截取的长度
     * @see [类、类#方法、类#成员]
     */
    private static int getsubLen(int len, int displayLen, String htmlValue) {
        int descSubLen = displayLen;
        int ascSubLen = displayLen;
        int subLen = len;

        // 从第30字符向左右取值，取到空格截取字符串
        while (true) {
            if (descSubLen >= len) {
                subLen = displayLen + 1;
                break;
            }

            if (descSubLen > displayLen + INT_10) {
                subLen = displayLen;
                break;
            }

            char descCode = getChar(descSubLen, htmlValue);
            char ascCode = getChar(ascSubLen, htmlValue);
            if (isSubLenToDescSubLen(descCode)) {
                subLen = descSubLen;
                break;
            }

            if (isSubLenToAscSubLen(ascCode)) {
                subLen = ascSubLen + 1;
                break;
            }

            descSubLen++;
            ascSubLen--;
        }

        return subLen;
    }

    /**
     * 是否从AscSubLen截取长度
     *
     * @param ascCode ascCode
     * @return 是否
     * @see [类、类#方法、类#成员]
     */
    private static boolean isSubLenToAscSubLen(char ascCode) {
        if (ascCode > INT_255 || ascCode == INT_32 || ascCode == INT_44 || ascCode == INT_46) {

            return true;

        }
        if (ascCode == INT_58 || ascCode == INT_59 || ascCode == INT_63) {
            return true;
        }
        return false;
    }

    /**
     * 是否从DescSubLen截取长度
     *
     * @param descCode descCode
     * @return 是否
     * @see [类、类#方法、类#成员]
     */
    private static boolean isSubLenToDescSubLen(char descCode) {
        if (descCode > INT_255 || descCode == INT_32 || descCode == INT_44 || descCode == INT_46) {

            return true;

        }
        if (descCode == INT_58 || descCode == INT_59 || descCode == INT_63) {
            return true;
        }
        return false;
    }

    /**
     * 取给定字符长度,ascii码大于255的字符占3位
     *
     * @param value  String
     * @param length int
     * @return String
     */
    public static String getUncodeSubString(String value, int length) {
        if (value == null) {
            return "";
        }

        int len = getStrLen(value);

        if (len > length) {
            int count = 0;
            char c;
            for (int i = 0; i < value.length(); i++) {

                c = value.charAt(i);
                if (c > INT_255) {
                    count += INT_3;
                } else {
                    count++;
                }

                if (count + INT_5 >= length) {
                    return value.substring(0, i) + "...";
                }
            }

        }

        return value;
    }

    private static char getChar(int index, String value) {
        int len = 0;
        char c = 0;
        for (int i = 0; i < value.length(); i++) {
            c = value.charAt(i);
            if (len == index) {
                return c;
            }
            if (c > INT_255) {
                len += INT_2;
            } else {
                len++;
            }
        }
        return c;
    }

    /**
     * 转换页面中特殊字符(用于显示)
     *
     * @param value String
     * @return String
     */
    public static String toHtmlValue(String value) {

        if (null == value) {
            return null;
        }
        value = paramToHtmlValue(value);
        char a;
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < value.length(); i++) {
            a = value.charAt(i);
            switch (a) {
                // 空格
                case INT_32:
                    buf.append("&nbsp;");
                    break;
                default:
                    buf.append(a);
                    break;
            }
        }
        return buf.toString();
    }

    /**
     * 转换页面中特殊字符,用于提交,不能转空格
     *
     * @param value String
     * @return String
     */
    public static String paramToHtmlValue(String value) {
        if (null == value) {
            return null;
        }
        char a;
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < value.length(); i++) {
            a = value.charAt(i);
            switch (a) {
                // 双引号
                case INT_34:
                    buf.append("&#034;");
                    break;
                // &号
                case INT_38:
                    buf.append("&amp;");
                    break;
                // 单引号
                case INT_39:
                    buf.append("&#039;");
                    break;
                // 小于号
                case INT_60:
                    buf.append("&lt;");
                    break;
                // 大于号
                case INT_62:
                    buf.append("&gt;");
                    break;
                default:
                    buf.append(a);
                    break;
            }
        }
        return buf.toString();
    }

    /**
     * oracle数据库的like语句中 % _ 转义处理
     *
     * @param keyword String 查询的关键字
     * @param sql     String
     * @return String 转义后的字符串 单引号用两个单引号替换 如果查询的关键字中包含了'%'或'_'，如关键字为："x_"则转义后形如：'%x/_%' escape '/'
     * 如果查询的关键字中没有包含了'%'或'_'，如关键字为："xx"则转义后形如：'%xx%'
     */
    public static String getDBQueryKey(String keyword, String sql) {
        return getDBQueryKeys(new String[]{keyword}, sql)[0].toString();
    }

    /**
     * oracle数据库的like语句中 % _ 转义处理
     *
     * @param args Object[]
     * @param sql  String
     * @return Object[]
     */
    public static Object[] getDBQueryKeys(Object[] args, String sql) {
        if (args == null || args.length == 0) {
            return args;
        }

        Object[] newArgs = new Object[args.length];
        System.arraycopy(args, 0, newArgs, 0, args.length);

        String strs[] = StringUtil.toLowerCase(sql).split("[?]");
        for (int i = 0; i < args.length; i++) {
            if (!(strs[i].trim().indexOf("like") > -1)) {
                continue;
            }

            if (args[i] == null || !(args[i] instanceof String)) {
                continue;
            }

            if (-1 != args[i].toString().indexOf("%") || -1 != args[i].toString().indexOf("/")
                    || -1 != args[i].toString().indexOf("_")) {
                newArgs[i] = args[i].toString().replaceAll("/", "//").replaceAll("%", "/%").replaceAll("_", "/_");
            } else if (-1 != args[i].toString().indexOf("％") || -1 != args[i].toString().indexOf("/")
                    || -1 != args[i].toString().indexOf("＿")) {
                newArgs[i] = args[i].toString().replaceAll("/", "//").replaceAll("％", "/%").replaceAll("＿", "/_");
            }
        }
        return newArgs;
    }

    /**
     * 获取随机字符串
     *
     * @return 随机字符串
     */
    public static String getRandomString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < INT_4; i++) {
            sb.append(StringUtil.byteToHexString((byte) Math.round(Math.random() * INT_255)));
        }
        int i = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        // 生成当天的字符串
        String days = String.valueOf(i);
        // 
        if (days.length() < INT_2) {
            // 给个位天数补0
            sb.append("-0");
        } else {
            sb.append("-");
        }
        // 生成随机字符串
        sb.append(i);
        // 返回随机字符串
        return sb.toString();
    }

    /**
     * 写数据库时对' 进行转换
     *
     * @param text String
     * @return string
     * @author haozhang3
     */
    public static String toDB(String text) {
        if (null == text || "".equals(text.trim())) {
            return text;
        }

        int l = text.length();
        StringBuffer strb = new StringBuffer();
        for (int i = 0; i < l; i++) {
            char c = text.charAt(i);
            switch (c) {
                case '\'':
                    strb.append('\'').append('\'');
                    break;
                default:
                    strb.append(c);
                    break;
            }
        }
        return strb.toString();
    }

    /** */
    /**
     * 由于String.subString对汉字处理存在问题（把一个汉字视为一个字节)，因此在 包含汉字的字符串时存在隐患，现调整如下：
     *
     * @param src      要截取的字符串
     * @param startIdx 开始坐标（包括该坐标)
     * @param endIdx   截止坐标（包括该坐标）
     * @return 截取完成的String
     */
    public static String substring(String src, int startIdx, int endIdx) {
        if (StringUtil.isNull(src)) {
            return null;
        }
        byte[] b = src.getBytes();

        if (startIdx < 0) {
            startIdx = 0;
        }

        StringBuffer tgt = new StringBuffer("");
        for (int i = startIdx; i <= endIdx; i++) {
            if (i < b.length) {
                tgt = tgt.append((char) b[i]);
            } else {
                break;
            }
        }
        return tgt.toString();
    }

    /**
     * 判断字符串对象值是否为中文空格,如果为null或是为空的直接返回,否则返回去空格(前后的中/英文空格)后的字符串值
     *
     * @param value 需要判断的字符串对象
     * @return tempValue 返回转换后的字符串值
     */
    public static String transNull(String value) {
        String tempValue;
        // 参数为null直接返回
        if (null == value) {
            return value;
        }

        // 参数为空格，直接返回""
        if ("".equals(value.trim())) {
            return "";
        }
        tempValue = value.trim();
        while (tempValue.codePointAt(0) == SPACAE_CH_ACII) {
            tempValue = tempValue.substring(1, tempValue.length()).trim();
        }
        while (tempValue.codePointAt(0) == SPACAE_CH_ACII) {
            tempValue = tempValue.substring(0, tempValue.length() - 1).trim();
        }
        return tempValue;
    }

    /**
     * 为null直接返回空,否则返回字符串
     *
     * @param value 需要判断的字符串对象
     * @return 返回转换后的字符串值
     */
    public static String strNull2Empty(String value) {
        // 参数为null直接返回空
        if (null == value) {
            return "";
        }
        return value;
    }

    /**
     * 处理空字符串，当字符为空时，设置其值为空字符串
     *
     * @param param 需要处理的字符串
     * @return 为空处理后的字符串
     */
    public static String strNull2EmptyTrim(String param) {
        if (null == param) {
            param = "";
        }
        // 取消字符串首尾的空格
        param = param.trim();

        return param;
    }

    /**
     * 判断字符串对象值是否为中文空格,如果为null或是为空的直接返回空,否则返回去空格(前后的中/英文空格)后的字符串值
     *
     * @param value 需要判断的字符串对象
     * @return 返回转换后的字符串值
     */
    public static String transEmpty(String value) {
        return transNull(strNull2Empty(value));
    }

    /**
     * 该方法用于去除中文空格
     *
     * @param value 字符串值
     * @return String
     */
    public static String trimBlank(String value) {
        // 参数为null直接返回
        if (isNull(value)) {
            return value;
        }

        // 去首尾空格,包括全角、半角
        return value.replaceAll("(^[ 　]*|[ 　]*$)", "");
    }

    /**
     * 该方法用于判断是否为数字
     *
     * @param value 字符串值
     * @return String
     */
    public static boolean isDigit(String value) {
        String str = "^[0-9]*$";
        Pattern pattern = Pattern.compile(str);
        Matcher isNum = pattern.matcher(value);
        if (!isNum.matches()) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * 转换脚本字符串
     *
     * @param cmd
     * @param server
     * @return
     */
    public static String convertShell(String cmd, String server) {
        String c = "shellexecutor " + "\"" + cmd.replaceAll("\"", "\\\\\"")
                + "\"" + " " + server;
        return c;
    }

    /**
     * 替换url中的ip和端口号
     *
     * @param domain
     * @param port
     * @param url
     * @return
     */
    public static String replaceDomainAndPort(String domain, String port, String url) {
        String url_bak = "";
        if (url.indexOf("//") != -1) {
            String[] splitTemp = url.split("//");
            url_bak = splitTemp[0] + "//";
            if (port != null) {
                url_bak = url_bak + domain + ":" + port;
            } else {
                url_bak = url_bak + domain;
            }

            if (splitTemp.length >= 1 && splitTemp[1].indexOf("/") != -1) {
                String[] urlTemp2 = splitTemp[1].split("/");
                if (urlTemp2.length > 1) {
                    for (int i = 1; i < urlTemp2.length; i++) {
                        url_bak = url_bak + "/" + urlTemp2[i];
                    }
                }
                //System.out.println("url_bak:"+url_bak);
            } else {
                //System.out.println("url_bak:"+url_bak);
            }
        }
        return url_bak;
    }

    public static String arrayToString(Object[] objs) {
        if (null != objs) {
            StringBuffer objsBuffer = new StringBuffer();
            objsBuffer.append("{");
            for (int i = 0; i < objs.length; i++) {
                objsBuffer.append((null == objs[i]) ? "null" : objs[i]);
                if (i < objs.length - 1) {
                    objsBuffer.append(",");
                }
            }
            objsBuffer.append("}");
            return objsBuffer.toString();
        }
        return "{null}";
    }

    public static String arrayToString(String[] objs) {
        if (null != objs) {
            StringBuffer objsBuffer = new StringBuffer();
            for (int i = 0; i < objs.length; i++) {
                objsBuffer.append((null == objs[i]) ? "null" : objs[i]);
                if (i < objs.length - 1) {
                    objsBuffer.append(",");
                }
            }
            return objsBuffer.toString();
        }
        return "";
    }

    public static String arrayToString(String[] objs, String separator) {
        if (null != objs) {
            StringBuffer objsBuffer = new StringBuffer();
            for (int i = 0; i < objs.length; i++) {
                objsBuffer.append((null == objs[i]) ? "null" : objs[i]);
                if (i < objs.length - 1) {
                    objsBuffer.append(separator);
                }
            }
            return objsBuffer.toString();
        }
        return "";
    }

    /**
     * 字符串转换成十六进制值
     *
     * @param bin String 我们看到的要转换成十六进制的字符串
     * @return
     */
    public static String bin2hex(String bin) {
        char[] digital = "0123456789ABCDEF".toCharArray();
        StringBuffer sb = new StringBuffer("");
        byte[] bs = bin.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(digital[bit]);
            bit = bs[i] & 0x0f;
            sb.append(digital[bit]);
        }
        return sb.toString();
    }

    /**
     * 十六进制转换字符串
     *
     * @param hex String 十六进制
     * @return String 转换后的字符串
     */
    public static String hex2bin(String hex) {
        String digital = "0123456789ABCDEF";
        char[] hex2char = hex.toCharArray();
        byte[] bytes = new byte[hex.length() / 2];
        int temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = digital.indexOf(hex2char[2 * i]) * 16;
            temp += digital.indexOf(hex2char[2 * i + 1]);
            bytes[i] = (byte) (temp & 0xff);
        }
        return new String(bytes);
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static String toStringHex(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            s = new String(baKeyword, "utf-8 ");//UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    /**
     * arrayToString
     *
     * @param objs int[]
     * @return String
     */
    public static String arrayToString(int[] objs) {
        if (null != objs) {
            StringBuffer objsBuffer = new StringBuffer();
            objsBuffer.append("{");
            for (int i = 0; i < objs.length; i++) {
                objsBuffer.append(objs[i]);
                if (i < objs.length - 1) {
                    objsBuffer.append(",");
                }
            }
            objsBuffer.append("}");
            return objsBuffer.toString();
        }
        return "{null}";
    }

    public static String beanToString(Object bean) {
        StringBuffer buffer = new StringBuffer();
        if (null == bean) {
            return buffer.toString();
        }

        Class c = bean.getClass();
        Field[] fields = c.getDeclaredFields();

        buffer.append(c.getName().substring(c.getName().lastIndexOf(".") + 1) + '[');
        try {
            AccessibleObject.setAccessible(fields, true);

            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                buffer.append(f.getName());
                buffer.append('=');
                Object field = f.get(bean);
                if (field instanceof Object[]) {
                    Object[] obj = (Object[]) field;
                    buffer.append(arrayToString(obj));
                } else {
                    if ("password".equals(toLowerCase(f.getName()))
                            || "pwd".equals(toLowerCase(f.getName()))
                            || "newpswd".equals(toLowerCase(f.getName()))
                            || "oldpswd".equals(toLowerCase(f.getName()))) {
                        buffer.append("******");
                    } else {
                        buffer.append(field);
                    }
                }
                if (i + 1 < fields.length) {
                    buffer.append(',');
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        buffer.append(']');
        return buffer.toString();

    }


    /**
     * join挂载点
     *
     * @return
     */
    public static String joinPath(String path1) {

        String path = path1;

        if (path.indexOf("//") > 0) {
            path = path.replace("//", "/");

        }
        return path;

    }


    /**
     * join挂载点
     *
     * @return
     */
    public static String joinPath(String path1, String path2) {

        String path = path1 + path2;

        if (path.indexOf("//") > 0) {
            path = path.replace("//", "/");

        }
        return path;

    }


}