package test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

public class day2 {

	@Test(groups= {"Smoke"})
	public void Ploan()
	{
		System.out.println("good");
	}
	
	@BeforeTest
	public void prerequisite()
	{
		System.out.println("I will execute first!!");
	}
}
