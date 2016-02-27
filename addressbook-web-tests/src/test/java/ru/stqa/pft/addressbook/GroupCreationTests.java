package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupcreation() {
    goToGroupsPage();
    initGroupCreation();
    fillOutGroupForm(new GroupData("group1", "header1", "footer1"));
    submitGroupCreation();
    returnToGroupsPage();
  }

}
