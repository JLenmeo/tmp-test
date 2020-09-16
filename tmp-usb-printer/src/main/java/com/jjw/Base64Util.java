package com.jjw;

import java.nio.charset.Charset;
import java.util.Base64;

public class Base64Util {

    public static String encode(String str) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] textByte = str.getBytes(Charset.forName("GBK"));
        return encoder.encodeToString(textByte);
    }

    public static String encode(byte[] bytes) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }

    public static String decode(String str) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] textByte = decoder.decode(str);
        return new String(textByte, Charset.forName("GBK"));
    }

    public static byte[] decodeForByte(String str) {
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(str);
    }

}
