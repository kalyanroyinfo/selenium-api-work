package com.learning.kr.practice;

import java.util.Map;
import java.util.TreeMap;

public class MergeSortedArray {
    public static void main(String args[]){
        int arr1[] = {1, 3, 5, 7,10};
        int n1 = arr1.length;

        int arr2[] = {2, 4, 6, 8, 9};
        int n2 = arr2.length;
        int[] arr3=new int[n1+n2];

        int i=0,j=0,k=0;
        while(i<n1 && j<n2){
            if(arr1[i]<arr2[j]){
                arr3[k++]=arr1[i++];
            }else{
                arr3[k++]=arr2[j++];
            }
        }
        while(i<n1){
            arr3[k++]=arr1[i++];
        }
        while(j<n1){
            arr3[k++]=arr2[j++];
        }

        for(int ii=0;ii<arr3.length;ii++){
            System.out.println(arr3[ii]);
        }

        mergeArrays(arr1,arr2,n1,n2);

    }


    static void mergeArrays(int a[], int b[], int n, int m)
    {

        // Declaring a map.
        // using map as a inbuilt tool
        // to store elements in sorted order.
        Map<Integer,Boolean> mp = new TreeMap<Integer,Boolean>();

        // Inserting values to a map.
        for(int i = 0; i < n; i++)
        {
            mp.put(a[i], true);
        }
        for(int i = 0;i < m;i++)
        {
            mp.put(b[i], true);
        }

        // Printing keys of the map.
        for (Map.Entry<Integer,Boolean> me : mp.entrySet())
        {
            System.out.print(me.getKey() + " ");
        }
    }
}
