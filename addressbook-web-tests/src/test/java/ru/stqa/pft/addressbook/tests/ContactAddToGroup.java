package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroup extends TestBase{

  @BeforeMethod
  public void ensurePreconditionsGroups(){
    if (app.db().groups().size() == 0){
      app.goTo().groupsPage();
      app.group().create(new GroupData()
              .withName("group1")
              .withHeader("header1")
              .withFooter("footer1"));
    }
    if (app.db().contacts().size() == 0){
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withName("Name")
              .withMiddleName("Middle")
              .withSurname("Surname")
              .withEmail("name.surname@gmail.com")
              .withHomePhone("07511111111")
              .withAddress("Address line 1"));
    }
  }

  @Test
  public void testAddContactToGroup(){
    Contacts contacts = app.db().contacts();
    ContactData selectedContact = contacts.iterator().next();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();

  //Changing the group name in UI to reflect id
    GroupData validGroup = new GroupData()
            .withId(group.getId())
            .withName("group " + group.getId());
    app.goTo().groupsPage();
    app.group().modify(validGroup);

    app.goTo().homePage();
    app.contact().selectContactById(selectedContact.getId());
    app.contact().selectGroupForAdding(validGroup.getName());
    app.contact().addToGroup();
    app.goTo().homePage();

    //Predicting the expected result
    group.withName("group " + group.getId());
    selectedContact.inGroup(group);

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
