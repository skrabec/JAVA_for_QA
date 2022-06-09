package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("for_remove"));
    }
  }

  @Test
  public void testGroupDeletion() {
    Set<GroupData> before = app.group().all();
    GroupData group_for_remove = before.iterator().next();
    app.group().delete(group_for_remove);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(group_for_remove);
    Assert.assertEquals(before, after);
  }
}

