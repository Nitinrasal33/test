package sample;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestScenarios {
   
    @SuppressWarnings("deprecation")
	@Test(priority = 1)
    public void dragAndDropImages() throws IOException {
    	
        ChromeDriver driver = new ChromeDriver();
    	
    	driver.get("https://www.globalsqa.com/demo-site/draganddrop/");
    	
    	driver.manage().window().maximize();
    	
    	// Set implicit wait time to 10 seconds
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	//Switch to frame
    	
    	  driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame lazyloaded']")));

    	// Locate the images to drag
      WebElement imgToDrag1 = driver.findElement(By.xpath("//*[@id='gallery']//img[contains(@alt,'The peaks of High Tatras')]"));
      WebElement imgToDrag2 = driver.findElement(By.xpath("//*[@id='gallery']//img[contains(@alt,'The chalet at the Green mountain lake')]"));
      WebElement imgToDrag3 = driver.findElement(By.xpath("//*[@id='gallery']//img[contains(@alt,'Planning the ascent')]"));
      WebElement imgToDrag4 = driver.findElement(By.xpath("//*[@id='gallery']//img[contains(@alt,'On top of Kozi kopka')]"));
        

        // Locate the trash bin
        
        WebElement trashBin = driver.findElement(By.xpath("//div[@id='trash']"));

        // Perform drag-and-drop action
        Actions act = new Actions(driver);
        act.dragAndDrop(imgToDrag1, trashBin).perform();
        act.dragAndDrop(imgToDrag2, trashBin).perform();
        act.dragAndDrop(imgToDrag3, trashBin).perform();
        act.dragAndDrop(imgToDrag4, trashBin).perform();
        
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        
        // Capture the screenshot as a file
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);

        // Specify the destination of the screenshot
        File destinationFile = new File("./target/screenshots/trashbin.png");

        // Copy the screenshot file to the specified destination
        FileHandler.copy(screenshotFile, destinationFile);
        
        // moving frame focus back 
        
        driver.switchTo().defaultContent();
        driver.close();
        
    }

    @SuppressWarnings("deprecation")
	@Test(priority = 2)
    public void chooseCountryFromDropdown() throws IOException {
    	ChromeDriver driver = new ChromeDriver();
        // Navigate to the webpage
        driver.get("http://www.globalsqa.com/demo-site/draganddrop/");
        driver.manage().window().maximize();

        // Move to drop down menu
        
        driver.findElement(By.xpath("//*[@id=\"menu-item-2803\"]/a/span[2]")).click();
        
        //store list box  in Variable as WebElement
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
       WebElement Countries=driver.findElement(By.tagName("select"));
       
       // To handle to list box create select class object and pass  WebElement argument
       
         Select s = new Select (Countries);
         
         s.selectByVisibleText("India");
         
         driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
         
         TakesScreenshot screenshot = (TakesScreenshot) driver;
         
         // Capture the screenshot as a file
         File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);

         // Specify the destination of the screenshot
         File destinationFile = new File("./target/screenshots/Country.png");

         // Copy the screenshot file to the specified destination
         FileHandler.copy(screenshotFile, destinationFile);
         
      // Create an instance of JavascriptExecutor
         JavascriptExecutor js = (JavascriptExecutor) driver;

         // Scroll down the page by a specific number of pixels (e.g., 1000 pixels)
         js.executeScript("window.scrollBy(0,600)");
         
       // Navigate to the Sample Page test
         
         driver.findElement(By.xpath("//*[@id=\"menu-item-2818\"]/a/span[2]")).click();
         
         driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
         driver.close();
    }

    @SuppressWarnings("deprecation")
	@Test(priority = 3)
    public void fillSamplePageForm() throws EncryptedDocumentException, IOException {
        // Navigate to the webpage
    	
        ChromeOptions options = new ChromeOptions();
    	
    	options.addExtensions(new File("C:\\Users\\ASUS\\Downloads\\cjpalhdlnbpafiamejdnhcphjbkeiagm-1.57.0-Crx4Chrome.com.crx"));
    	
        // Disable notifications
      

    	ChromeDriver driver = new ChromeDriver(options);
        driver.get("http://www.globalsqa.com/samplepagetest/");
        driver.manage().window().maximize();

     // Locate the file input element.
        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));

        // Specify the path of the image file to upload
        String filePath = "C:\\Users\\ASUS\\eclipse-workspace\\test\\src\\main\\resources\\nitinimage.jpg"; 

        // Send the file path to the file input element
        fileInput.sendKeys(filePath);
        // parametrization
   FileInputStream file=new  FileInputStream("C:\\Users\\ASUS\\eclipse-workspace\\test\\src\\test\\resources\\dataforinput.xlsx");
        
        String name= WorkbookFactory.create(file).getSheet("Sheet1").getRow(0).getCell(1).getStringCellValue();
        //String email= WorkbookFactory.create(file).getSheet("Sheet1").getRow(1).getCell(1).getStringCellValue();
      // String website= WorkbookFactory.create(file).getSheet("Sheet1").getRow(2).getCell(1).getStringCellValue();
      // String comment= WorkbookFactory.create(file).getSheet("Sheet1").getRow(3).getCell(1).getStringCellValue();
        
        //name x path
        
        WebElement nameField = driver.findElement(By.xpath("//*[@id=\"g2599-name\"]"));
        nameField.sendKeys(name);
        
        // Simulate pressing the tab key to move to the "Email" field
        nameField.sendKeys(Keys.TAB);
        
     // Find the "Email" field and fill in the information
        WebElement emailField = driver.findElement(By.xpath("//*[@id=\"g2599-email\"]"));
        emailField.sendKeys("nrsal088@gmail.com");
        
        
        WebElement websiteField = driver.findElement(By.xpath("//*[@id=\"g2599-website\"]"));
        websiteField.sendKeys("https://www.globalsqa.com/samplepagetest/");
        
      //store list box  in Variable as WebElement
        
        WebElement experience=driver.findElement(By.tagName("select"));
     // To handle to list box create select class object and pass  WebElement argument
        
        Select n = new Select (experience);
        
        n.selectByIndex(1);
        
     // Locate the checkbox element by its ID or other suitable locator
        WebElement checkbox = driver.findElement(By.xpath("//*[@id=\"contact-form-2599\"]/form/div[5]/label[2]/input"));

        // Check if the checkbox is not already selected
        if (!checkbox.isSelected()) {
            // Click on the checkbox to select it
            checkbox.click();
        }
        
        WebElement radioButton = driver.findElement(By.xpath("//*[@id=\"contact-form-2599\"]/form/div[6]/label[3]/input"));

        // Check if the radio button is not already selected
        if (!radioButton.isSelected()) {
            // Click on the radio button to select it
            radioButton.click();
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
        
        
     // Locate the button that triggers the alert
        driver.findElement(By.xpath("//button[@onclick='myFunction()']")).click();

     // Switch to the alert
        Alert alert = driver.switchTo().alert();
        
        // Accept the alert (click on ok button)
        alert.accept();
        
        alert.accept();
        
       // comment box 
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//textarea[@name='g2599-comment']")).sendKeys("Java is my passion");
        //     driver.findElement(By.xpath("//textarea[@name='g2599-comment']")).sendKeys("Java programming is my passion");

       driver.findElement(By.xpath("//button[@type='submit']")).click();
       
       driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       
       TakesScreenshot screenshot = (TakesScreenshot) driver;
       
       // Capture the screenshot as a file
       File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);

       // Specify the destination of the screenshot
       File destinationFile = new File("./target/screenshots/samplepage.png");

       // Copy the screenshot file to the specified destination
       FileHandler.copy(screenshotFile, destinationFile);
       driver.close();
    }

    
}