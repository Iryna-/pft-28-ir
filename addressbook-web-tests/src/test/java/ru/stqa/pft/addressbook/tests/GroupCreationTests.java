package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupcreation() {
    app.getNavigatioHelper().goToGroupsPage();
    app.getGroupHelper().createGroup(new GroupData("group1", "header1", "footer1"));
  }
}
