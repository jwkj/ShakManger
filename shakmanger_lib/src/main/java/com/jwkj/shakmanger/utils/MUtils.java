package com.jwkj.shakmanger.utils;

/**
 * 工具类
 * Created by HDL on 2017/11/15.
 */

public class MUtils {
    /**
     * 是否可以取某一位的值
     *
     * @param num   msgversion
     * @param index num的某一位，从0开始
     * @return 0 表示不可以取，1表示可以取
     */
    public static boolean isCanGetValue(int num, int index) {
        return getNumIndexValue(num, index) == 1;
    }

    /**
     * 获取32位无符号整形的某一位的值
     *
     * @param num
     * @param index 从0开始，eg：2,-->0100000...0000000-->getNumIndexValue(2,1)=1
     * @return
     */
    public static int getNumIndexValue(int num, int index) {
        return (num & (0x1 << index)) >> index;
    }

    /**
     * byte数组翻转输出
     *
     * @param b
     * @return
     */
    public static byte[] bytesReverseOrder(byte[] b) {
        int length = b.length;
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            result[length - i - 1] = b[i];
        }
        return result;
    }
    /**
     * 转换byte表示的Mac地址
     *
     * @param macSrc 原始地址
     * @param splite 分隔符
     * @return Mac地址
     */
    public static String getMacAddress(byte[] macSrc, String splite) {
        StringBuilder mac = new StringBuilder();
        int sum = macSrc.length;
        for (int i = 0; i < macSrc.length; i++) {
            String macSub = Integer.toHexString(macSrc[i] & 0xff);
            if (macSub.length() == 1) {
                macSub = "0" + macSub;
            }
            mac.append(macSub);
            if (i >= sum - 1) {
                return mac.toString();
            }
            mac.append(splite);
        }
        return mac.toString();
    }
}
