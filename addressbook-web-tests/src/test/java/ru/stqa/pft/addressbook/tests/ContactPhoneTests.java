package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

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
  public void testConactPhones(){

    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contactInfoFromDetailsForm.getFullName(),equalTo(mergeNames(contactInfoFromEditForm)));

    assertThat(contactInfoFromDetailsForm.getHomePhone(), equalTo(contactInfoFromEditForm.getHomePhone()));
    assertThat(contactInfoFromDetailsForm.getMobile(), equalTo(contactInfoFromEditForm.getMobile()));
    assertThat(contactInfoFromDetailsForm.getWorkPhone(), equalTo(contactInfoFromEditForm.getWorkPhone()));

    //assertThat(contactInfoFromDetailsForm.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }


  private String mergeNames(ContactData contact) {
    return Arrays.asList(contact.getName(), contact.getMiddleName(), contact.getSurname())
            .stream()
            .filter((s) -> ! s.equals(""))
            .collect(Collectors.joining(" "));
  }


  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobile(), contact.getWorkPhone(), contact.getHomePhone2())
            .stream()
            .map(ContactPhoneTests::cleaned)
            .filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));

  }


  public static String cleaned (String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]]","");
  }
}
