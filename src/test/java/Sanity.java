import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sudharsan_selvaraj.SpyDriver;
import io.github.sudharsan_selvaraj.audiologger.LogOptions;
import io.github.sudharsan_selvaraj.audiologger.SeleniumAudioLogger;
import io.github.sudharsan_selvaraj.autowait.SeleniumWaitOptions;
import io.github.sudharsan_selvaraj.autowait.SeleniumWaitPlugin;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Sanity {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        SpyDriver spyDriver = new SpyDriver(new ChromeDriver());
        SeleniumWaitPlugin waitPlugin = new SeleniumWaitPlugin(spyDriver, SeleniumWaitOptions.builder().defaultWaitTime(Duration.ofSeconds(10)).build());

        LogOptions options = new LogOptions();
        options.setLogAfterCommand(true);
        options.setLogBeforeCommand(true);
        SeleniumAudioLogger logger = new SeleniumAudioLogger(spyDriver, options);

        WebDriver driver = spyDriver.getSpyDriver();

        try {
            driver.get("https://www.amazon.in");
            driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 7");
            driver.findElement(By.id("nav-search-submit-text")).click();
            driver.findElement(By.partialLinkText("OnePlus 7 Pro")).click();
            driver.switchTo().window(driver.getWindowHandles().toArray(new String[]{})[1]);
            driver.findElement(By.id("add-to-cart-button")).click();

            driver.manage().window().maximize();
            driver.manage().window().setSize(new Dimension(1000, 700));

            ((JavascriptExecutor)driver).executeScript("console.log('hi')");
            ((JavascriptExecutor)driver).executeAsyncScript("(function(callback){ callback()})(arguments[0])");

            /* This will trigger the exception */
            driver.findElement(By.id("attach-view-cart-button-form")).sendKeys("sdfsdf");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

}
