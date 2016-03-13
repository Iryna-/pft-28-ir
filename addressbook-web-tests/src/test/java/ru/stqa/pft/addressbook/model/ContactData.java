package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String name;
  private final String surname;
  private final String email;
  private final String phone;
  private final String middleName;
  private final String address;
  private String group;
  private int id;




  public ContactData(String name, String middleName, String surname, String email, String phone, String address, String group) {
    this.name = name;
    this.middleName = middleName;
    this.surname = surname;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.group = group;
    this.id = Integer.MAX_VALUE;

  }

  public ContactData(String name, String middleName, String surname, String email, String phone, String address, String group, int id) {
    this.name = name;
    this.middleName = middleName;
    this.surname = surname;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.group = group;
    this.id = id;
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

  public int getId() {
    return id;
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
