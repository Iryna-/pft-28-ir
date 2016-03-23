package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData>{

  //create an object that everything will be delegated to
  private Set<GroupData> delegate;

  //Create a constructor that takes groups as an input
  public Groups(Groups groups) {
      this.delegate = new HashSet<GroupData>(groups.delegate());
  }

  //blank constructor
  public Groups() {
    this.delegate = new HashSet<>();
  }


  //the default method will return this object
  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }

  //Let’s add our own methods. To allow chaining, we have to return an object of Groups type, but we need both the old and the new Groups
  public Groups withAdded(GroupData group){
    Groups groups = new Groups(this);
    groups.add(group);
    return groups;
  }

  public Groups without(GroupData group){
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;
  }
}
