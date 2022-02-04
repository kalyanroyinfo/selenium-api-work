package com.learning.kr.practice;

/**
 * Created by kalyanroy on 13/06/21.
 */
public class StringExample
{
	protected int test1=100;

public static void main(String args[]){
//	String str1 = new String("Paul");
//	String str2 = new String("Paul");
//	System.out.println(str1==str2);
//	System.out.println(str1.equals(str2));


	String str3 = "Harry";
	String str4 = "Harry";

	System.out.println(str3==str4);
	System.out.println(str3.equals(str4));

	StringExample obj = new StringExample();

//	String str = "Geeks for Geeks";
//	obj.reverse(str);

	String resultantSting1 = obj.reverseString("JAVATPOINT");
	System.out.println(resultantSting1);

}

	/* Function to print reverse of the passed string */
	void reverse(String str)
	{
		if ((str==null)||(str.length() <= 1))
			System.out.println(str);
		else
		{
			System.out.print(str.charAt(str.length()-1));
			reverse(str.substring(0,str.length()-1));
		}
	}

	//recursive function to reverse a string
	public String reverseString(String str) {
		//checks if the string is empty
		if (str.isEmpty()) {
			System.out.println("String is empty.");
			//if the above condition is true returns the same string
			return str;
		} else {
			return reverseString(str.substring(1)) + str.charAt(0);
		}
	}

}
