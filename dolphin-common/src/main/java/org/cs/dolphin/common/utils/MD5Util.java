package org.cs.dolphin.common.utils;

import java.security.MessageDigest;

/**
 * @ClassName MD5Util
 * @Description MD5加密工具
 * @Author Liujt
 * @Date 2019/11/21 9:47
 **/
public class MD5Util {

    public static boolean matches(CharSequence charSequence , String s) {
        return MD5(charSequence.toString()).equals(s);
    }

    /**
     * md5加密过程
     */
    public static String MD5(String key) {
        char hexDigits[] = {
                '0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9' , 'a' , 'b' , 'c' , 'd' , 'e' , 'f'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[ j * 2 ];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[ i ];
                str[ k++ ] = hexDigits[ byte0 >>> 4 & 0xf ];
                str[ k++ ] = hexDigits[ byte0 & 0xf ];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

}
