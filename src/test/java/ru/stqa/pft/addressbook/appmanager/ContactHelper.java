package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.ContactCreationTests;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd){
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getFirstName());

    if (creation == true){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());

    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void submitContactCreation() {
    wd.findElement(By.name("submit")).click();
  }

  public void selectContact() {
    wd.findElement(By.xpath("//*[@name='selected[]']")).click();
  }

  public void deleteSelectedContact() {
    wd.findElement(By.xpath("//input[@type='button' and @value='Delete']")).click();
  }

  public void closePopUp(){
    wd.switchTo().alert().accept();
  }

  public void editContact() {
    wd.findElement(By.xpath("//*[@title='Edit']")).click();
  }

  public void submitContactModification() {
    wd.findElement(By.xpath("//input[@type='submit' and @value='Update']")).click();
  }
}
