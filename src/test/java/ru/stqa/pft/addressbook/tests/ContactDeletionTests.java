package ru.stqa.pft.addressbook.tests;

import org.junit.Test;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion(){
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().closePopUp();
    app.getNavigationHelper().gotoHomePage();
  }
}
