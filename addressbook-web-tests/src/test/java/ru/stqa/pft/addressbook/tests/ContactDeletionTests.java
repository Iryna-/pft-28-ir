package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getNavigatioHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Name","Middle", "Surname", "name.surname@gmail.com", "07511111111", "Address line 1", "group1"));
    }
    List<ContactData> before = app.getContactHelper().getContactsList();

    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getNavigatioHelper().goToHomePage();

    List<ContactData> after = app.getContactHelper().getContactsList();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);

  }
}
