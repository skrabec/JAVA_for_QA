package ru.stqa.pft.addressbook.tests;

import org.junit.Test;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() throws Exception {

      app.getNavigationHelper().goToGroupPage();

      app.getGroupHelper().selectGroup();
      app.getGroupHelper().deleteSelectedGroups();
      app.getGroupHelper().returnToGroupPage();
    }

}

