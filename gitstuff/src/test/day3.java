package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class day3 {

	@Parameters({"URL"})	
	@Test
	public void Weblogin(String urlname) {
		//
		System.out.println("Weblogin1 Car");
		System.out.println(urlname);
	}

	@Parameters({"URL"})	
	@Test
	public void Moblogin(String urlname) {
		//
		System.out.println("Mobile login");
		System.out.println(urlname);
		
	}
	@Test
	public void MobLogout() {
		System.out.println("Mob Logout");
	}
	

	@BeforeClass
	public void b4class() {
		System.out.println("Execute before Class");
	}

	@AfterClass
	public void aftercls() {
		System.out.println("Execute at the end of all classes");
	}
	
	@Test
	public void Login() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis =new FileInputStream("C:\\Users\\pphatank\\eclipse-workspace\\TestNGTutorial\\src\\test\\datadrive.properties");
		prop.load(fis);
		
		System.out.println(prop.getProperty("username"));
		
	}

	@Test (dependsOnMethods= {"Weblogin","MobLogout"})
	public void LoginAPI() 
	{
		//
		System.out.println("API Login");
	}

	@BeforeMethod

	public void bevery() {
		System.out.println("Execute before every methods of day 3!!!");
	}
	
	@Test(groups= {"Smoke"})
	public void FBlogin() {
		System.out.println("FB Login");
	}

	@Test(groups= {"Smoke"})
	public void FBlogout() {
		System.out.println("FB logout");
	}

	@Test(timeOut=40000)
	public void yahoologin() {
		System.out.println("loging to yahoo.com");
	}
	
	@Test(enabled=true)
	public void yahoologout() {
		System.out.println("logout from yahoo.com");
	}
}
