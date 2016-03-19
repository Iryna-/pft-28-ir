package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupsPage();
    if (app.group().all().size()==0){
      app.group().create(new GroupData()
              .withName("group1")
              .withHeader("header1")
              .withFooter("footer1"));
    }
  }


  @Test
  public void testGroupModification () {

    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())
            .withName("group_v.1.1")
            .withHeader("header_v.1.1")
            .withFooter("footer_v1.1");

    app.group().modify(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());


    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before,after);

  }


}
