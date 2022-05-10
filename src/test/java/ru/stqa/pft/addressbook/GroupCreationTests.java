package ru.stqa.pft.addressbook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class GroupCreationTests {
  private WebDriver wd;

  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "/Users/mikalaiskrabets/Work/chromedriver");
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
  }

  @Test
  public void testGroupCreation() throws Exception {
    wd.get("http://localhost/addressbook/index.php");
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys("admin");
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys("secret");
    wd.findElement(By.xpath("//input[@value='Login']")).click();
    wd.findElement(By.linkText("groups")).click();
    wd.get("http://localhost/addressbook/group.php");
    wd.findElement(By.name("new")).click();
    wd.findElement(By.name("group_name")).click();
    wd.findElement(By.name("group_name")).clear();
    wd.findElement(By.name("group_name")).sendKeys("test");
    wd.findElement(By.name("group_header")).click();
    wd.findElement(By.name("group_header")).clear();
    wd.findElement(By.name("group_header")).sendKeys("test");
    wd.findElement(By.name("group_footer")).click();
    wd.findElement(By.name("group_footer")).clear();
    wd.findElement(By.name("group_footer")).sendKeys("test");
    wd.findElement(By.name("submit")).click();
    wd.findElement(By.linkText("home")).click();
    wd.get("http://localhost/addressbook/");
    wd.findElement(By.linkText("Logout")).click();
  }

  @After
  public void tearDown() throws Exception {
    wd.quit();
  }
}
