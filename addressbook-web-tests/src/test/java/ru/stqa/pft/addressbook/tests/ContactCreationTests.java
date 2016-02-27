package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    
    @Test
    public void testContactCreation() {
        app.initContactCreation();
        app.fillOutContactForm(new ContactData("Name","Middle", "Surname", "name.surname@gmail.com", "07511111111", "Address line 1"));
        app.submitContactCreation();
        app.goToContactsPage();
    }

}
