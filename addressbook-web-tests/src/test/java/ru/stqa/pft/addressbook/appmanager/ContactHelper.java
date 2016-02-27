package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
     click(By.name("submit"));
  }

  public void fillOutContactForm(ContactData contactData) {
      type(By.name("firstname"),contactData.getName());
      type(By.name("middlename"),contactData.getMiddleName());
      type(By.name("lastname"),contactData.getSurname());
      type(By.name("email"),contactData.getEmail());
      type(By.name("home"),contactData.getPhone());
      type(By.name("address2"),contactData.getAddress());
  }

  public void initContactCreation() {
      click(By.linkText("add new"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedContact() {
    click(By.xpath(".//*[@value='Delete']"));
  }

  public void initContactModification() {
    click(By.xpath(".//*[@title='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }
}
