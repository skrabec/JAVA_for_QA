package ru.stqa.pft.addressbook;

import org.junit.Test;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test", "test", "test"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
