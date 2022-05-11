package ru.stqa.pft.addressbook.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ContactCreationTests {
  private WebDriver wd;

  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "/Users/mikalaiskrabets/Work/chromedriver");
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/index.php");
  }

  @Test
  public void testContactCreation() throws Exception {
    login();
    initContactCreation();
    fillContactForm(new ContactData("Arthur", "Conan", "Doyle"));
    submitContactCreation();
    gotoHomePage();
  }

  private void gotoHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  private void fillContactForm(ContactData contactData) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
    wd.findElement(By.name("middlename")).clear();
    wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddleName());
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
  }

  private void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  private void submitContactCreation() {
    wd.findElement(By.name("submit")).click();
  }

  private void login() {
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys("admin");
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys("secret");
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @After
  public void tearDown() throws Exception {
    wd.quit();
  }
}
