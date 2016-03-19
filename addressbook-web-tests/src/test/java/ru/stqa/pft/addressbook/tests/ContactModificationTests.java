package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().list().size()==0){
      app.contact().create(new ContactData()
              .withName("Name")
              .withMiddleName("Middle")
              .withSurname("Surname")
              .withEmail("name.surname@gmail.com")
              .withPhone("07511111111")
              .withAddress("Address line 1")
              .withGroup("group1"));
    }
  }

  @Test
  public void testContactModification(){

    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData()
            .withName("Name007")
            .withMiddleName("Middle1")
            .withSurname("Surname1")
            .withEmail("name.surname1@gmail.com")
            .withPhone("07511112222")
            .withAddress("Address line 22")
            .withId(before.get(index).getId());
    app.contact().modify(index, contact);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
