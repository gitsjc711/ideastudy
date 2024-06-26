package com.study.idea.demos.web.util;

import org.springframework.util.DigestUtils;

import java.lang.reflect.Array;
import java.security.DigestException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class MD5Util {
    public static String generateSalt(String s) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[15];
        random.nextBytes(bytes);
        String salt = Base64.getEncoder().encodeToString(bytes).substring(0,6);
        return salt;
    }
    public static String inputPassToDBPass(String inputPass, String salt) {
        String str=inputPass+salt;
        String result=DigestUtils.md5DigestAsHex(str.getBytes());
        return result;
    }
}
