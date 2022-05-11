package ru.stqa.pft.addressbook.tests;

import org.junit.Test;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() throws Exception {

      app.goToGroupPage();

      app.selectGroup();
      app.deleteSelectedGroups();
      app.returnToGroupPage();
    }

}

