package test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;

public class day1 {

			// TODO Auto-generated method stub
	
	@AfterTest
	public void lastexec()
	{
		System.out.println("I will execute at the end");
	}
	
	@Parameters({"URL"})
	@Test
	public void demo(String urlname)
	{
		System.out.println("Hello");//automation code
		//driver.get("http://www.google.co.in");
		System.out.println(urlname);
	}
	@Test
	public void secondtest()
	{
		System.out.println("Bye");
	}

	@AfterSuite
	public void afsuite()
	{
		System.out.println("Execute last program");
	}
	
	@AfterMethod
	public void afmethod()
	{
		System.out.println("Execute at the end of every test!!!");
	}
	
}