package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminHelper extends HelperBase {

  public AdminHelper(ApplicationManager app) {
    super(app);
  }

  public void loginAs(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"),username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void manageUsers() {
    if (isElementPresent(By.cssSelector("input[value='Create New Account']"))){
      return;
    }
    click(By.linkText("Manage Users"));
  }

}

