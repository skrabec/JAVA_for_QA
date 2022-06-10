package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Groups before = app.group().all();
    GroupData group_for_modification = before.iterator().next();
    GroupData group = new GroupData().withId(group_for_modification.getId()).withName("edited");

    app.group().modify(group);
    Groups after = app.group().all();
    assertEquals(after.size(), before.size());

    assertThat(after, equalTo(before.withModified(group_for_modification, group)));
  }

}
