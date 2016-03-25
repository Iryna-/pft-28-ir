package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups (){

    //Filling out a list of arrays.
    // Each array contains a set of data for one launch of the test method

    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new GroupData()
                              .withName("group1")
                              .withHeader("header1")
                              .withFooter("footer1")});
    list.add(new Object[]{new GroupData()
                              .withName("group2")
                              .withHeader("header2")
                              .withFooter("footer2")});
    list.add(new Object[]{new GroupData()
                              .withName("group3")
                              .withHeader("header3")
                              .withFooter("footer3")});

    //Test framework uses the iterator to extract each array and use it for the test

    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
    app.goTo().groupsPage();
    Groups before = app.group().all();
    app.group().create(group);
    assertThat(app.group().count(),equalTo((before.size() + 1)));
    Groups after = app.group().all();
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }
}
