package Amazon;

import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class AmazonSearch 
{
	public static void main (String args[]) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./BrowserUtil/chromedriver.exe");
		
		WebDriver Search=new ChromeDriver();
		
		Search.manage().window().maximize();
		
		Search.get("https://www.amazon.in/");
		
		//Searching for a particular product in Amazon Website
		
		WebElement STag = Search.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		
		String Product="iphone 14 pro";
		
		STag.sendKeys(Product);
		
		STag.sendKeys(Keys.RETURN);
		
		//Clicking on the searched product
	
		Search.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal'][1]")).click();
		
		Search.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Navigating to Product page 
		
		ArrayList<String> tab1 = new ArrayList<String>(Search.getWindowHandles());
		
		Search.switchTo().window((String)tab1.get(1));
		
		System.out.println("Page title of active tab: " + Search.getCurrentUrl());
			
		// adding the searched product to cart
		
		Search.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		
		Search.findElement(By.xpath("//span[@id='attach-sidesheet-view-cart-button']")).click();
		
		Search.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Viewing the product in the cart
		
		ArrayList<String> tab2 = new ArrayList<String>(Search.getWindowHandles());
		
		Search.switchTo().window((String)tab2.get(1));
		
		System.out.println("Page title of active tab: " + Search.getCurrentUrl());
		
		WebElement CartName=Search.findElement(By.xpath("//span[contains(@class,'a-truncate-full')]"));
		
		WebElement CartQuan=Search.findElement(By.xpath("//span[@class=\"a-dropdown-prompt\"]"));
		
		
		String Name=CartName.getAttribute("innerHTML").toString().toLowerCase();
		
		int Quantity=Integer.valueOf(CartQuan.getAttribute("innerHTML"));
		boolean iscontains=Name.contains(Product.toLowerCase());
		
		if(iscontains==true && (Quantity == 1|| Quantity>1))
		{
			System.out.println("Product added successfully");
		}
		else
		{
			System.out.println("NOt Added");
		}
		Thread.sleep(5000);
		
		Search.quit();
	}

}
