package com.learning.kr.practice;

public class StringBlankSpaceRemove {
    public static void main(String args[]){
        String str = "      Geeks     for    Geeks        ";

        // Call the replaceAll() method
        str = str.replaceAll("\\s", "");

        System.out.println(str);
    }
}
