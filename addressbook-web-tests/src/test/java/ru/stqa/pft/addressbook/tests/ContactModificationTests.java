package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigatioHelper().goToContactsPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillOutContactForm(new ContactData("Name1","Middle1", "Surname1", "name.surname1@gmail.com", "07511112222", "Address line 22", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigatioHelper().goToContactsPage();
  }
}
