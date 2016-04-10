package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

public class PasswordChangeTests extends TestBase {


  @Test
  public void testPasswordChange (){
    String username = app.getProperty("web.adminUsername");
    String password = app.getProperty("web.adminPassword");
    app.admin().loginAs(username, password);
    app.admin().manageUsers();
  }
}
