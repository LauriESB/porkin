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

  @Column(nullable = false, unique = true)
  private String username;

  @OneToMany(mappedBy = "fkPersonUser", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<FriendshipEntity> friendships = new HashSet<>();

  @OneToMany(mappedBy = "idExpenseCreator", cascade = CascadeType.ALL)
  private List<ExpenseEntity> expenses;

  @OneToMany(mappedBy = "person")
  private List<ExpenseSplitEntity> splitExpenses;

  @ElementCollection
  @CollectionTable(name = "friendIDs", joinColumns = @JoinColumn(name = "username"))
  @Column(name = "friendID")
  private Set<String> friendsUsernames = new HashSet<>();

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "pixId")
  private PixEntity pix;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "paypalId")
  private PayPalEntity payPal;

  @OneToOne
  @JoinColumn
  private ImageData userProfilePicture;

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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public List<ExpenseSplitEntity> getSplitExpenses() {
    return splitExpenses;
  }

  public void setSplitExpenses(List<ExpenseSplitEntity> splitExpenses) {
    this.splitExpenses = splitExpenses;
  }

  public void setFriendships(Set<FriendshipEntity> friendships) {
    this.friendships = friendships;
  }

  public Set<String> getFriendsUsernames() {
    return friendsUsernames;
  }

  public void setFriendsUsernames(Set<String> friendsUsernames) {
    this.friendsUsernames = friendsUsernames;
  }

  public String getPix() {
    return pix != null ? pix.getPix() : "Chave pix não cadastrada";
  }

  public void setPix(PixEntity pix) {
    this.pix = pix;
  }

  public String getPaypal() {
    return payPal != null ? payPal.getPayPalKey() : "PayPal não cadastrado";
  }

  public void setPaypal(PayPalEntity paypal) {
    this.payPal = paypal;
  }

  public ImageData getUserProfilePicture() {
    return userProfilePicture;
  }

  public void setUserProfilePicture(ImageData userProfilePicture) {
    this.userProfilePicture = userProfilePicture;
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
