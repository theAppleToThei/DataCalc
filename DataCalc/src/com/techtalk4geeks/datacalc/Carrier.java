package com.techtalk4geeks.datacalc;

public class Carrier
{
	private String myName;
	private String myPrice;
	private String mySize;
	
	public Carrier(String name, String price, String size)
	{
		myName = name;
		myPrice = price;
		mySize = size;
	}

	String getName()
	{
		return myName;
	}

	String getPrice()
	{
		return myPrice;
	}

	String getSize()
	{
		return mySize;
	}

}
