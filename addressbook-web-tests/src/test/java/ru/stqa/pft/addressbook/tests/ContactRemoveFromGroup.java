package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroup extends TestBase {

  @Test
  public void testDeleteContactFromGroup(){

    Contacts contacts = app.db().contacts();

    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();

    app.goTo().homePage();
    app.contact().selectGroupWithContacts(group.getName());
    app.contact().checkSelectedGroupFilterApplied();
    Contacts filteredGroupContacts = app.contact().all();
    ContactData selectedContact = filteredGroupContacts.iterator().next();
    app.contact().removeFromGroup(selectedContact);
    app.goTo().homePage();

    //Predicting the expected result
    selectedContact.outOfGroup(group);

    //Getting the actual result
    ContactData dbContact = app.db()
            .contacts()
            .stream()
            .filter(c->c.getId() == selectedContact.getId())
            .findFirst()
            .get();

    assertThat(app.contact().count(), equalTo(contacts.size()));
    assertThat(selectedContact.getGroups(), equalTo(dbContact.getGroups()));

  }
}
