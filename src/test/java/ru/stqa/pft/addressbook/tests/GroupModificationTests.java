package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("for_edit"));
    }
  }

  @Test
  public void testGroupModification(){

    Set<GroupData> before = app.group().all();
    GroupData group_for_modification = before.iterator().next();
    GroupData group = new GroupData().withId(group_for_modification.getId()).withName("edited");

    app.group().modify(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(group_for_modification);
    before.add(group);
    Assert.assertEquals(before, after);

  }

}
