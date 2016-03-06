package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String name;
  private final String surname;
  private final String email;
  private final String phone;
  private final String middleName;
  private final String address;
  private String group;

  public ContactData(String name, String middleName, String surname, String email, String phone, String address, String group) {
    this.name = name;
    this.middleName = middleName;
    this.surname = surname;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.group = group;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getAddress() {
    return address;
  }

  public String getGroup() {
    return group;
  }
}
