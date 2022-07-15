package com.orderbt.Util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class Encrypt {

    @SuppressWarnings("finally") 
    public static String setSHA512(String password, String password_key){

        String sha512 = null;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(password_key.getBytes());
            md.update(password.getBytes());
            sha512 = String.format("%0128x", new BigInteger(1, md.digest()));
        }catch (Exception e) {
           e.printStackTrace();
        }finally{
            return sha512;
        }
        
    }
    @SuppressWarnings("finally") 
    public static String getSaltKey(){
        String salt = null;
        try{
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            byte[] bytes = new byte[16];
            random.nextBytes(bytes);
            salt = new String(Base64.getEncoder().encode(bytes));
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return salt;
        }
    }
    
}