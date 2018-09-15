package com.onemore.goodproduct.util;/**
 * Created by Administrator on 2017/9/28.
 */

import java.security.MessageDigest;

/**
 * 思路过程：
 * 1、str.getBytes()：将字符串转化为字节数组。字符串中每个字符转换为对应的ASCII值作为字节数组中的一个元素
 * 2、将字节数组通过固定算法转换为16个元素的有符号哈希值字节数组
 * 3、将哈希字节数组的每个元素通过0xff与运算转换为两位无符号16进制的字符串
 * 4、将不足两位的无符号16进制的字符串前面加0
 * 5、通过StringBuffer.append()函数将16个长度为2的无符号进制字符串合并为一个32位String类型的MD5码
 */
public class MD5 {

    public static String toMD5(String plainText) {
        String md5String32 = "";
        try {
            //生成实现指定摘要算法的 MessageDigest 对象。
            MessageDigest md = MessageDigest.getInstance("MD5");
            //使用指定的字节数组更新摘要。
            md.update(plainText.getBytes());
            //通过执行诸如填充之类的最终操作完成哈希计算。
            byte b[] = md.digest();
            //生成具体的md5密码到buf数组
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            md5String32 = buf.toString();
//            System.out.println("32位: " + buf.toString());// 32位的加密
//            System.out.println("16位: " + buf.toString().substring(8, 24));// 16位的加密，其实就是32位加密后的截取
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5String32;
    }

    public static void main(String agrs[]) {
        toMD5("123456");//加密LXD
    }

}
