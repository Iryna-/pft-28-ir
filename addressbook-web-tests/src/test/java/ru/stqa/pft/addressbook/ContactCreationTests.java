package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    
    @Test
    public void testContactCreation() {
        initContactCreation();
        fillOutContactForm(new ContactData("Name","Middle", "Surname", "name.surname@gmail.com", "07511111111", "Address line 1"));
        submitContactCreation();
        goToContactsPage();
    }

}
