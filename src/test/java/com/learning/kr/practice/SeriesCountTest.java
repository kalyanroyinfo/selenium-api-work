package com.learning.kr.practice;

public class SeriesCountTest {
    /*
        5 ² + 4 ² - 3 ² + 2 ² - 1 ² = 35
        6 ² + 5 ² - 4 ² + 3 ² - 2 ² + 1 ² = 51
    */
    static int count = 1;
    static int sum = 0;

    static double testMethod2(int num){
        for(int i = num; i > 0; i = i- 2){
            if ((count == 1)) {
                sum =  ((i * i) + (i-1) * (i-1));
            } else {
                sum =  (sum - (i * i) + (i-1) * (i-1));
            }
            count++;
        }

        return sum;
    }

    public static void main(String[] args) {

        System.out.println(testMethod2(6));

    }
}
