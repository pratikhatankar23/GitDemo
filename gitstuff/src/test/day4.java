package test;

import org.testng.annotations.Test;

public class day4 {


	@Test(groups= {"Smoke"})
	public void Webloginhome() {
		//
		System.out.println("Weblogin Car");
	}

	@Test
	public void Mobloginhome() {
		//
		System.out.println("Mobile1 login");
	}

	@Test
	public void LoginAPIhome() {
		//
		System.out.println("API1 Login");
	}

}
