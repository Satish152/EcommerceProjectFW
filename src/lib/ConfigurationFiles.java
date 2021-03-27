package lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigurationFiles {

	public Properties properties;
	
	
	public ConfigurationFiles() throws IOException{
		File file=new File("H:\\workspace\\ECommerce_TestCases\\configuration\\Config.property");
		FileInputStream fis=new FileInputStream(file);
		properties=new Properties();
		properties.load(fis);
	}
	
	public String getChromePath(){
		String Chrome=properties.getProperty("ChromeDriver");
		return Chrome;
	}
	
	public String getFirefoxPath(){
		String FireFox=properties.getProperty("FireFoxDriver");
		return FireFox;
	}
	
	public String getIEPath(){
		String IE=properties.getProperty("IEDriver");
		return IE;
	}
	
	public String getApplicationURL(){
		String URL=properties.getProperty("URL");
		return URL;
	}
	
}
