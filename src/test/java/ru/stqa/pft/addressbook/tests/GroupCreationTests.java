package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsFromCsv () throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
    String line = reader.readLine();
    while (line != null){
      String split [] = line.split(";");
      list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson () throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null){
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
    return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validGroupsFromJson")
  public void testGroupCreation(GroupData group) throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    assertThat(app.group().getGroupCount(), equalTo(before.size()+1));
    Groups after = app.group().all();
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
  }
}
