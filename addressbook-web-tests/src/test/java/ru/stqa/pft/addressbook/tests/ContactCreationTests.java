package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withName("Name")
            .withMiddleName("Middle")
            .withSurname("Surname")
            .withEmail("name.surname@gmail.com")
            .withPhone("07511111111")
            .withAddress("Address line 1")
            .withGroup("group1");
    app.contact().create(contact);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    //anticipating the result
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
