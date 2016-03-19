package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupcreation() {
    app.goTo().groupsPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData()
            .withName("group1")
            .withHeader("header1")
            .withFooter("footer1");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    //anticipating the result
    before.add(group);
    Assert.assertEquals(before,after);
  }
}
