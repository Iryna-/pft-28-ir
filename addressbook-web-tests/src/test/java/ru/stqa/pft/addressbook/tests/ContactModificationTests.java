package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){

    app.getNavigatioHelper().goToHomePage();

    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Name","Middle", "Surname", "name.surname@gmail.com", "07511111111", "Address line 1", "group1"));
    }

    List<ContactData> before = app.getContactHelper().getContactsList();

    app.getContactHelper().initContactModification(before.size() - 1);

    ContactData contact = new ContactData("Name007","Middle1", "Surname007", "name.surname1@gmail.com", "07511112222", "Address line 22", null, before.get(before.size() - 1).getId());

    app.getContactHelper().fillOutContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigatioHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactsList();

    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
