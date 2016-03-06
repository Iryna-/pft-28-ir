package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    
    @Test
    public void testContactCreation() {
        app.getNavigatioHelper().goToHomePage();
        app.getContactHelper().createContact(new ContactData("Name","Middle", "Surname", "name.surname@gmail.com", "07511111111", "Address line 1", "group1"));
        app.getNavigatioHelper().goToHomePage();
    }

}
