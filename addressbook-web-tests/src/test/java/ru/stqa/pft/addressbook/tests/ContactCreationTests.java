package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    app.getNavigatioHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactsList();
    ContactData contact = new ContactData("Name", "Middle", "Surname", "name.surname@gmail.com", "07511111111", "Address line 1", "group1");
    app.getContactHelper().createContact(contact);
    app.getNavigatioHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactsList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
