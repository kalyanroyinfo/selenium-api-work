package com.learning.kr.appium;

/**
 * Created by kalyanroy on 23/11/20.
 */

 class A{
	public String a="text";

	public String getA()
	{
		return this.a;
	}
}

class B extends A{
	public String getA()
	{
		this.a="BBBBB";
		return this.a;
	}
}

public class Runner
{

	public static void main(String args[]){
		A b=new B();
		System.out.println(b.getA());
	}
}
