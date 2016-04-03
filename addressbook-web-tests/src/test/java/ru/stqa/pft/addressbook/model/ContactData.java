package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;


@XStreamAlias("contact")

@Entity
@Table (name = "addressbook")

public class ContactData {

  @Expose
  @Column(name = "firstname")
  private String name;



  @Expose
  @Column(name = "middlename")
  private String middleName;

  @Expose
  @Column(name = "lastname")
  private String surname;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Transient
  private String email2;

  @Transient
  private String email3;

  @Transient
  private String allEmails;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;


  @Expose
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable (name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"),
          inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;

  @Column(name = "mobile")
  @Type(type = "text")
  private String mobile;

  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;

  @Transient
  private String homePhone2;

  @Transient
  private String allPhones;

  @Transient
  private String fullName;

  @Expose
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;



  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getHomePhone2() {
    return homePhone2;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getFullName() {
    return fullName;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getAddress() {
    return address;
  }

  public int getId() {
    return id;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  public File getPhoto() {
    return new File(photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
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

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withHomePhone2(String homePhone2) {
    this.homePhone2 = homePhone2;
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


  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withMobilePhone (String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return  this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return  this;
  }

  public ContactData withFullName(String fullName) {
    this.fullName = fullName;
    return this;
  }


  @Override
  public String toString() {
    return "ContactData{" +
            "name='" + name + '\'' +
            ", middleName='" + middleName + '\'' +
            ", surname='" + surname + '\'' +
            ", email='" + email + '\'' +
            ", address='" + address + '\'' +
            ", id=" + id +
            ", homePhone='" + homePhone + '\'' +
            ", mobile='" + mobile + '\'' +
            ", workPhone='" + workPhone + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
    if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    return homePhone != null ? homePhone.equals(that.homePhone) : that.homePhone == null;

  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
    result = 31 * result + (surname != null ? surname.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + id;
    result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
    return result;
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }

  public ContactData outOfGroup(GroupData group) {
    groups.remove(group);
    return this;
  }
}
