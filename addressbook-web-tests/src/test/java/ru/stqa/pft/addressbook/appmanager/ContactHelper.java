package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
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

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath(".//*[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath(".//*[@title='Edit']")).get(index).click();
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

  public List<ContactData> getContactsList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows){
      List <WebElement> cells = row.findElements(By.tagName("td"));
      String surname = cells.get(1).getText();
      String name = cells.get(2).getText();
      String email = cells.get(4).getText();
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(name, null, surname, email, null, null, null, id);
      contacts.add(contact);
    }
    return contacts;
  }
}
