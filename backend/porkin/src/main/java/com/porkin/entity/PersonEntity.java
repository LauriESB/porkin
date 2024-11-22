package com.porkin.entity;

import com.porkin.dto.PersonDTO;
import com.porkin.paymentMethods.entity.PayPalEntity;
import com.porkin.paymentMethods.entity.PixEntity;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "person")
public class PersonEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idUser;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String mobileNumber;

  @OneToMany(mappedBy = "fkPersonUser")
  private Set<FriendshipEntity> friendships = new HashSet<>();

  @OneToMany(mappedBy = "idExpenseCreator")
  private List<ExpenseEntity> expenses;

  @ElementCollection
  @CollectionTable(name = "friendIDs", joinColumns = @JoinColumn(name = "idUser"))
  @Column(name = "friendID")
  private Set<Long> friendIDs = new HashSet<>();

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "pixId")
  private PixEntity pix;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "paypalId")
  private PayPalEntity paypal;

  // pessoaEntity constructors

  public PersonEntity(PersonDTO personDTO) {
    BeanUtils.copyProperties(personDTO, this);
  }

  public PersonEntity() {

  }

  // getters and setters

  public Long getId() {
    return idUser;
  }

  public void setId(Long id) {
    this.idUser = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public Set<FriendshipEntity> getFriendships() {
    return friendships;
  }

  public void setFriendships(FriendshipEntity friendships) {
    this.friendships.add(friendships);
  }

  public List<ExpenseEntity> getExpenses() {
    return expenses;
  }

  public void setExpenses(List<ExpenseEntity> expenses) {
    this.expenses = expenses;
  }

  public void setFriendships(Set<FriendshipEntity> friendships) {
    this.friendships = friendships;
  }

  public Set<Long> getFriendIDs() {
    return friendIDs;
  }

  public void setFriendIDs(Set<Long> friendIDs) {
    this.friendIDs = friendIDs;
  }

  public String getPix() {
    return pix.getPixKey();
  }

  public void setPix(PixEntity pix) {
    this.pix = pix;
  }

  public PayPalEntity getPaypal() {
    return paypal;
  }

  public void setPaypal(PayPalEntity paypal) {
    this.paypal = paypal;
  }

  // hashCode and equals

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PersonEntity that = (PersonEntity) o;
    return Objects.equals(idUser, that.idUser);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(idUser);
  }

}
