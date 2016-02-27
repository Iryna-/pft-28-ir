package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupcreation() {
    app.goToGroupsPage();
    app.initGroupCreation();
    app.fillOutGroupForm(new GroupData("group1", "header1", "footer1"));
    app.submitGroupCreation();
    app.returnToGroupsPage();
  }

}
