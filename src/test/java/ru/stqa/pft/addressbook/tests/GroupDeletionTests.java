package ru.stqa.pft.addressbook.tests;

import org.junit.Assert;
import org.junit.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {

    app.getNavigationHelper().goToGroupPage();
    int before = app.getGroupHelper().getGroupCount();

    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before - 1);
  }

}

