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
      type(By.name("home"),contactData.getHomePhone());
      type(By.name("address"),contactData.getAddress());
      attach(By.name("photo"),contactData.getPhoto());
      if (creation){
        new Select(wd.findElement(By.name("new_group")))
                .selectByVisibleText(contactData.getGroup());
      } else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
  }

  public void fillOutContactFormForModification (ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getName());
    type(By.name("middlename"),contactData.getMiddleName());
    type(By.name("lastname"),contactData.getSurname());
    type(By.name("email"),contactData.getEmail());
    type(By.name("home"),contactData.getHomePhone());
    type(By.name("address"),contactData.getAddress());
    if (creation){
      new Select(wd.findElement(By.name("new_group")))
              .selectByVisibleText(contactData.getGroup());
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


  private void checkContactDetailsById(int id) {
    wd.findElement(By.cssSelector("a[href='view.php?id=" + id + "']")).click();
  }

  public void submitContactModification() {
    click(By.xpath(".//*[@value='Update']"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillOutContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillOutContactFormForModification(contact, false);
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
      String address = cells.get(3).getText();
      String email = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData()
              .withName(name)
              .withSurname(surname)
              .withEmail(email)
              .withId(id)
              .withAllPhones(allPhones)
              .withAllEmails(allEmails)
              .withAddress(address);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String name = wd.findElement(By.name("firstname")).getAttribute("value");
    String middleName = wd.findElement(By.name("middlename")).getAttribute("value");
    String surname = wd.findElement(By.name("lastname")).getAttribute("value");
    String homePhone = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String workPhone = wd.findElement(By.name("work")).getAttribute("value");
    String homePhone2 = wd.findElement(By.name("phone2")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");

    wd.navigate().back();
    return new ContactData()
            .withId(contact.getId())
            .withName(name)
            .withMiddleName(middleName)
            .withSurname(surname)
            .withHomePhone(homePhone)
            .withMobilePhone(mobile)
            .withWorkPhone(workPhone)
            .withHomePhone2(homePhone2)
            .withEmail(email)
            .withEmail2(email2)
            .withEmail3(email3)
            .withAddress(address);
  }

  public ContactData infoFromDetailsForm(ContactData contact) {

    checkContactDetailsById(contact.getId());
    String fullName = wd.findElement(By.cssSelector("#content>b")).getText();
    String [] phones = wd.findElements(By.cssSelector("#content>a"))
             .stream()
             .filter((p)-> p.getText() !=null)
             .map((p)-> p.getText())
             .toArray((p)-> new String[p] );

    wd.navigate().back();
    return new ContactData()
            .withId(contact.getId())
            .withFullName(fullName)
            .withHomePhone(phones[0])
            .withMobilePhone(phones[1])
            .withWorkPhone(phones[2]);
            //.withAddress(address);
  }


}
