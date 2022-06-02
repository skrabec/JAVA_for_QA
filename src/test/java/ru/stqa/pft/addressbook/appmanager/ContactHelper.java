package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.ContactCreationTests;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd){
    super(wd);
  }

  public void fillContactForm(ContactData contactData) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
    wd.findElement(By.name("middlename")).clear();
    wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddleName());
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
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
