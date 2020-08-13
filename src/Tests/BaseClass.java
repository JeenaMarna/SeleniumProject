package Tests;
import java.io.FileInputStream;
import java.util.Properties;

public class BaseClass {
	public static String username;
	public static String password;
	public static String url;
	public static String brand;
	public static String phone_model;
	
	public static void getValues() throws Exception {
		Properties data=new Properties();
		System.out.println(data);
		FileInputStream file= new FileInputStream(".\\Test Data\\config.properties");
		System.out.println(file);
		data.load(file);
		url=data.getProperty("url");
		username=data.getProperty("username");
		password=data.getProperty("password"); 
		brand=data.getProperty("brand");
		phone_model=data.getProperty("phone_model");
		System.out.println(url);
	}
	public static void getTime() throws InterruptedException {
		Thread.sleep(7000);
	}
}
