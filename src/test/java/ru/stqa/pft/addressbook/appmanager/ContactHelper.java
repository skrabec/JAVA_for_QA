package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd){
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());

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

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContact() {
    wd.findElement(By.xpath("//input[@type='button' and @value='Delete']")).click();
  }

  public void closePopUp(){
    wd.switchTo().alert().accept();
  }

  public void editContact(int index) {
    wd.findElements(By.xpath("//*[@title='Edit']")).get(index).click();
  }

  public void submitContactModification() {
    wd.findElement(By.xpath("//input[@type='submit' and @value='Update']")).click();
  }

  public void createContact(ContactData contact, Boolean creation) {
    initContactCreation();
    fillContactForm(contact, creation);
    submitContactCreation();
    returnToContactPage();
  }

  public void returnToContactPage() {
    click(By.linkText("home"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> getContactList() {
    List <ContactData> contacts = new ArrayList<>();
    List <WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement cell : rows){
      List<WebElement> cells = cell.findElements(By.tagName("td"));
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      int id = Integer.parseInt(cell.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(firstName, lastName);
      contacts.add(contact);
    }
    return contacts;
  }
}
