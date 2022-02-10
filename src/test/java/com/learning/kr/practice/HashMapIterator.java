package com.learning.kr.practice;

import java.util.*;

public class HashMapIterator {
    public static void main(String args[]){
        // Creating hash map
        Map<Character, String> charType
                = new HashMap<Character, String>();

        // Inserting data in the hash map.
        charType.put('J', "Java");
        charType.put('H', "Hibernate");
        charType.put('P', "Python");
        charType.put('A', "Angular");

        // Iterating HashMap through for loop
        for (Map.Entry<Character, String> set :
                charType.entrySet()) {

            // Printing all elements of a Map
            System.out.println(set.getKey() + " = "
                    + set.getValue());
        }

        // Iterating HashMap through forEach and
        // Printing all. elements in a Map
        charType.forEach(
                (key, value)
                        -> System.out.println(key + " = " + value)
        );

        // Iterating every set of entry in the HashMap, and
        // printing all elements of it
        charType.entrySet().stream().forEach(
                input -> System.out.println(input.getKey() + " : " + input.getValue())
        );
    }
}
