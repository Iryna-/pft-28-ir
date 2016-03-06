package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

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

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedContact() {
    click(By.xpath(".//*[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void initContactModification() {
    click(By.xpath(".//*[@title='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void createContact(ContactData contact) {
   initContactCreation();
   fillOutContactForm(contact, true);
   submitContactCreation();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
}
