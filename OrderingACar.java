package testNGdemo;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class OrderingACar {
	

    


timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		JavascriptExecutor js;

		// 1. Go to Lexus.com
		driver.get("https://www.lexus.com");

		// 2. Verify the title of the page contains “Experience Amazing”.
		driver.findElement(By.xpath("//h1[.='Experience Amazing']")).getText();

		assertEquals(driver.getTitle(), "Lexus | Experience Amazing");

		// Click on Do not sell my Personal information
		driver.findElement(By.xpath("//a[.='DO NOT SELL MY PERSONAL INFORMATION']")).click();

		// Verify the page title contains "Privacy Hub"

		String expectedTitle = "Toyota - Lexus Privacy Hub";

		String currentWindow = driver.getWindowHandle();
		//staus

		for (String handle : driver.getWindowHandles()) {

			driver.switchTo().window(handle);

			if (driver.getTitle().equals(expectedTitle)) {

				break;

			} else {
				driver.switchTo().window(currentWindow);
			}
		}
		assertEquals(driver.getTitle(), expectedTitle);
		driver.getTitle().contains("Privacy Hub");

		// Verify that the page url is “https://privacy.toyota.com/”.
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='card shadow-sm h-100']//div[@class='card-footer']")).click();

		driver.findElement(By.xpath("//a[contains(text(),'Do Not Sell My Personal Information')]")).click();
		String url = driver.getCurrentUrl();
		System.out.println(url);
		String ecpecedUrl = "https://privacy.toyota.com/privacy-hub/donotsell.html";
		assertEquals(url, ecpecedUrl);

		// Go back to the main window and click on Build your Lexus
		driver.switchTo().window(currentWindow);

		driver.findElement(By.xpath("//span[.='BUILD YOUR LEXUS']")).click();

		// Enter "22182" for zipcode and click on Enter on pop- up window
		driver.findElement(By.xpath("//input[@value='Enter Zip']")).sendKeys("22182" + Keys.ENTER);

		// Click on model GS
		driver.findElement(By.xpath("//*[@id=\"body-type-display-list\"]/span[1]/ul/li[3]/a[1]/div/img")).click();

		Thread.sleep(5000);
		js = (JavascriptExecutor) driver;

		js.executeScript("scroll(0, 3000)");

		// Choose 2020 GS 350 F Sport AWD. Before clicking, get the price of the vehicle
		// and save it into an int variable.
		Thread.sleep(5000);

		driver.findElement(
				By.xpath("//body/div/div/div/div/div/article/div/span/span/ul/li[2]/div[2]/ul[1]/li[2]/a[1]/span[2]"))
				.click();
		// driver.findElement(By.xpath("/span[@class='title-price-med
		// float-right'])[4]")).click();

		// On the next page, click on the price menu on top and retrieve and store the
		// base price, dp&h fee and msrp into separate int variables.
		driver.findElement(By.id("total-price")).click();

		int basePrice = 54505;
		
		int dph = 1025;
		
		int msrp = 55530;

		// Verify that the base price is the same as the price shown at Step 10.
		System.out.println(driver.getTitle());

		String expectedPrice = driver.findElement(By.xpath("//*[@id=\"price-breakup-list\"]/li[1]/div")).getText();

		expectedPrice = expectedPrice.replace("$", "").replace(",", "");

		String actual = String.valueOf(basePrice);

		assertEquals(expectedPrice, actual);

		// Verify that msrp equals to base price + dp&h fee;

		int total = dph + basePrice;

		String actual1 = String.valueOf(total);

		String expected = driver.findElement(By.id("total-amount")).getText();

		expected = expected.replace("$", "").replace(",", "");

		assertEquals(actual1, expected);

		// Close the menu and click on Ultrasonic Blue Mica color.

		driver.findElement(By.xpath("//*[@id=\"price-breakdown\"]/div/ul[1]/li/div/a")).click();

		driver.findElement(By.xpath("//*[@id=\"NonExclusiveColorSelection\"]/ul/li[6]")).click();

		// Click on the price menu on top again and retrieve the price for color and
		// store into int variable.
		driver.findElement(By.xpath("//span[@id='total-price']")).click();

		int bluePrice = 56125;

		// Retrieve msrp one more time and verify that msrp now equals to base price +
		// dp&h fee + color
		int blueColor = 595;

		int newMsrp = dph + basePrice + blueColor;

		String actual2 = String.valueOf(newMsrp);
		// Thread.sleep(3000);
		String expected2 = driver.findElement(By.id("total-amount")).getText();

		expected2 = expected2.replace("$", "").replace(",", "");

		assertEquals(actual2, expected2);

		// Close the price menu and click on Next:Interior button
		driver.findElement(By.xpath("//span[@id='total-price']")).click();

		// Choose “Rioja Red leather with Naguri Aluminum trim” from the options .Click
		// on Next:packages
		driver.findElement(By.id("configurator-interior-color-selected")).click();

		// In the next menu, click on add button for Mark Levinson sound system.
		driver.findElement(By.id("configurator-packages-selected")).click();

		// Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"individualSelectionView\"]//li[1]/div/div[1]/a")).click();

		// Click on price menu again and retrieve and store the price for Sound system
		// into int variable.
		// Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@id='total-price']")).click();

		// Retrieve msrp once again and verify that msrp now equals to base price + dp&h
		// fee + color+sound system.
		int soundSysPrice = 1380;

		int newMsrp1 = dph + basePrice + blueColor + soundSysPrice;

		String actual3 = String.valueOf(newMsrp1);

		String expected3 = driver.findElement(By.id("total-amount")).getText();

		expected3 = expected3.replace("$", "").replace(",", "");

		assertEquals(actual3, expected3);

		// Click on Next:Accessories , on the next menu don’t add anything and click on
		// Next:summary
		// Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"FooterLink\"]/div/a[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"FooterLink\"]/div/a")).click();

		// On the next page, retrieve msrp and verify that it is equal to the final msrp
		// from step 17.
		// Thread.sleep(3000);
		String finalPrice = driver.findElement(By.className("title-price")).getText();

		finalPrice = finalPrice.replace("$", "").replace(",", "").replace("*", "");

		assertEquals(actual3, finalPrice);

		// Then click on Send to Dealer
		driver.findElement(By.xpath("//a[contains(text(),'Send to Dealer')]")).click();

		Thread.sleep(5000);
		String expectedTitle2 = "Build Your Own Lexus | Lexus Configurator";

		String current = driver.getWindowHandle();

		String toyotaWin = "Toyota - Lexus Privacy Hub";

		for (String i : driver.getWindowHandles()) {

			if (!(current.equals(i)) && !(current.equals(toyotaWin))) {

				driver.switchTo().window(i);
			}
		}
		// Next, first verify that the page contains “Send us Your Dream Car”
		String actualDreamCar = driver.findElement(By.xpath("//*[@class='title-intro-overlay']")).getText();

		String expectedDreamCar = "SEND US YOUR DREAM CAR";

		assertEquals(actualDreamCar, expectedDreamCar);

		// Then enter the below information to the form fields,
		// name-John;lname-Doe;email- anymail@gmail.com;phone-3127250272

		driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("John");

		driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("Doe");

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("anymail@gmail.com");

		driver.findElement(By.id("phone")).sendKeys("5712975417");

		WebElement findElement = driver.findElement(By.xpath("//*[@id=\"64504\"]"));

		js.executeScript("arguments[0].click();", findElement);

		js.executeScript("scroll(0, 1000)");

		driver.findElement(By.xpath("(//button[@class='btn-rev'])[1]")).click();

		String actualPageTextdriver = driver.findElement(By.xpath("//*[@id=\"byl-dealer-form\"]/div/div/header/div"))
				.getText();

		String expectedPageTextdriver = "WE'LL BE IN TOUCH SHORTLY";

		assertEquals(actualPageTextdriver, expectedPageTextdriver);

	}

	
}
