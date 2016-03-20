package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().all().size()== 0){
      app.contact().create(new ContactData()
              .withName("Name")
              .withMiddleName("Middle")
              .withSurname("Surname")
              .withEmail("name.surname@gmail.com")
              .withHomePhone("07511111111")
              .withAddress("Address line 1")
              .withGroup("group1"));
    }
  }

  @Test
  public void testContactAddress(){

    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }

}
