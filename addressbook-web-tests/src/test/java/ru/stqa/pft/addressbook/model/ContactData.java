package ru.stqa.pft.addressbook.model;

public class ContactData {
  private String name;
  private String surname;
  private String email;
  private String phone;
  private String middleName;
  private String address;
  private String group;
  private int id = Integer.MAX_VALUE;


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

  public int getId() {
    return id;
  }

  public ContactData withName(String name) {
    this.name = name;
    return this;
  }

  public ContactData withSurname(String surname) {
    this.surname = surname;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public ContactData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", id=" + id +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    return surname != null ? surname.equals(that.surname) : that.surname == null;

  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (surname != null ? surname.hashCode() : 0);
    return result;
  }

}
