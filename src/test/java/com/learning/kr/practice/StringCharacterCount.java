package com.learning.kr.practice;

import java.util.HashMap;
import java.util.Map;

/*
Java program to count frequencies of
characters in string using Hashmap
*/
public class StringCharacterCount {
    public static void main(String args[]){
        String val="kalyan roy";

        char arr[]=val.toCharArray();
        HashMap<Character, Integer> charCountMap= new HashMap<Character, Integer>();

        for(char c:arr){
            if(charCountMap.containsKey(c)){
                charCountMap.put(c,charCountMap.get(c)+1);
            }else
                charCountMap.put(c,1);
        }

        for(Map.Entry<Character,Integer> map:charCountMap.entrySet()){
            System.out.println(map.getKey()+" "+ map.getValue());
        }

    }
}
