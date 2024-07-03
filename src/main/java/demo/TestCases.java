package demo;

import java.util.concurrent.TimeUnit;

import org.checkerframework.checker.units.qual.t;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.INFO);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Connect to the chrome-window running on debugging port
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
       // driver.manage().window().maximize();
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        driver.get("https://calendar.google.com/");
        String url=driver.getCurrentUrl();
        if(url.contains("calendar.")){
            System.out.println("url of the page contains calendar");
        }
        System.out.println("end Test case: testCase01");
    }
    public  void testCase02() throws InterruptedException{
        System.out.println("Start Test case: testCase02");
        driver.get("https://calendar.google.com/");
        Thread.sleep(5000);
        

         WebElement monthClick=driver.findElement(By.xpath("//span[contains(text(),'Month')]"));
        monthClick.click();
        Thread.sleep(2000);
        WebElement monthSelect = driver.findElement(By.xpath("//span[contains(text(),'Month')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

             js.executeScript("arguments[0].scrollIntoView();",monthSelect);
        monthSelect.click();
        String textcontain= monthClick.getText();
        if(textcontain.contains("Month")){
            System.out.println("switching to the Month view");
        }
        Thread.sleep(2000);
        WebElement dateSelect = driver.findElement(By.xpath("//div[@data-datekey='27875']"));     
         dateSelect.click();
        Thread.sleep(3000);
        WebElement taskBtn = driver.findElement(By.xpath("(//div[text()='Task'])[2]"));
        Thread.sleep(3000);
        taskBtn.click();
        WebElement title= driver.findElement(By.xpath("//input[@placeholder='Add title and time']"));
        title.sendKeys("Crio INTV Task Automation");
        WebElement description = driver.findElement(By.xpath("//textarea[@placeholder='Add description']"));
        description.sendKeys("Crio INTV Calendar Task Automation");
        
        Thread.sleep(3000);
        WebElement submitBtn = driver.findElement(By.xpath("(//div[@class='VfPpkd-dgl2Hf-ppHlrf-sM5MNb']/button)[14]"));
        submitBtn.click(); 
        Thread.sleep(5000);   
        System.out.println("end Test case: testCase02");
    }
    public  void testCase03() throws InterruptedException{
        System.out.println("Start Test case: testCase03");
        driver.get("https://calendar.google.com/");
        Thread.sleep(5000);
        WebElement textT= driver.findElement(By.xpath("(//span[@class='WBi6vc'])[1]"));
        textT.click();
        Thread.sleep(3000);
        WebElement updateBtn= driver.findElement(By.xpath("(//button[@class='VfPpkd-Bz112c-LgbsSe yHy1rc eT1oJ mN1ivc m2yD4b HfYfLe'])[1]"));
        updateBtn.click();
        Thread.sleep(2000);
        WebElement updateDesc=driver.findElement(By.xpath("//textarea[@placeholder='Add description']"));
        updateDesc.click();
        Thread.sleep(3000);
        updateDesc.clear();
        Thread.sleep(3000);
        updateDesc.sendKeys("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application");

       Thread.sleep(3000);
        WebElement submitBtn = driver.findElement(By.xpath("(//div[@class='VfPpkd-dgl2Hf-ppHlrf-sM5MNb']/button)[6]"));
        submitBtn.click();  
        Thread.sleep(5000);

        WebElement textT1= driver.findElement(By.xpath("(//span[@class='WBi6vc'])[1]"));
        textT1.click();
        Thread.sleep(5000);

        WebElement viewDesc = driver.findElement(By.id("xDetDlgDesc"));
        String text=viewDesc.getText();
        if(text.contains("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application")){
            System.out.println("The task description is successfully updated and displayed.");
        }
       WebElement crossBtn = driver.findElement(By.xpath("(//span[@class='VfPpkd-kBDsod meh4fc KU3dEf'])[2]"));
       crossBtn.click();
       Thread.sleep(5000);
       
        System.out.println("end Test case: testCase03");
    }

    public  void testCase04() throws InterruptedException{
        System.out.println("Start Test case: testCase04");
        driver.get("https://calendar.google.com/");
        Thread.sleep(5000);
        WebElement textT= driver.findElement(By.xpath("(//span[@class='WBi6vc'])[1]"));
        textT.click();
        Thread.sleep(3000);

        WebElement titleElement= driver.findElement(By.xpath("//div[@class='toUqff ']/span"));
        String text=titleElement.getText();
        if(text.contains("Crio INTV Task Automation")){
            System.out.println("verifying the title of the task is correct");
        }
        WebElement deleteBtn= driver.findElement(By.xpath("(//button[@class='VfPpkd-Bz112c-LgbsSe yHy1rc eT1oJ mN1ivc m2yD4b HfYfLe'])[2]"));
        deleteBtn.click();
        WebElement popupText = driver.findElement(By.xpath("//div[text()='Task deleted']"));
        String delteText = popupText.getText();
        if(delteText.contains("Task deleted")){
            System.out.println(" The task is successfully deleted, and the confirmation message indicates Task deleted");
        }

        System.out.println("end Test case: testCase04");
    }

}
