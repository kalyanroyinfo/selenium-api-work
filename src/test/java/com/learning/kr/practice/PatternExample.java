package com.learning.kr.practice;

public class PatternExample {
    public static void main(String args[]) {
        triangle(6);
    }

    public static void rightSpace(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void leftSpace(int row) {
        int i, j;
        for (i = 0; i < row; i++) {

            for (j = 2*(row - i); j >= 0; j--) {
                System.out.print(" ");
            }
            for (j = 0; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void triangle(int row) {
        int i, j;
        for (i = 0; i < row; i++) {

            for (j = (row - i); j >= 0; j--) {
                System.out.print(" ");
            }
            for (j = 0; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

}
