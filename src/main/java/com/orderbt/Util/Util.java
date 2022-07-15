package com.orderbt.Util;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static  List <String> makeForeach (String str, String sep){
        List<String> list =   new ArrayList<String>();
        String[] aCode = str.split(sep);
     
        if (str == null || "".equals(str)) {
            return null;
        }
        for(int i=0; i< aCode.length; i++){
            list.add(aCode[i].toString().trim());
        }
     
        return list;
    }
}