package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
     click(By.name("submit"));
  }

  public void fillOutContactForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"),contactData.getName());
      type(By.name("middlename"),contactData.getMiddleName());
      type(By.name("lastname"),contactData.getSurname());
      type(By.name("email"),contactData.getEmail());
      type(By.name("home"),contactData.getPhone());
      type(By.name("address2"),contactData.getAddress());

      if (creation){
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }

  }

  public void initContactCreation() {
      click(By.linkText("add new"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void selectContactById (int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath(".//*[@value='Delete']"));
    wd.switchTo().alert().accept();
  }


  public void initContactModificationById (int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillOutContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillOutContactForm(contact, false);
    submitContactModification();
    contactCache = null;
  }


  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
  }

  private Contacts contactCache = null;

  public Contacts all() {

    if (contactCache != null){
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows){
      List <WebElement> cells = row.findElements(By.tagName("td"));
      String surname = cells.get(1).getText();
      String name = cells.get(2).getText();
      String email = cells.get(4).getText();
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData()
              .withName(name)
              .withSurname(surname)
              .withEmail(email).withId(id);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }


}
