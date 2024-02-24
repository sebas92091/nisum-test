package com.technical.exercise.users.adapter.repository;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(updatable = false)
  private String uuid;

  @Column
  private String name;

  @Column
  private String email;

  @Column
  private String password;

  @Column(updatable = false)
  @CreatedDate
  private Date created;

  @Column
  @LastModifiedDate
  private Date modified;

  @Column
  @LastModifiedDate
  private Date lastLogin;

  @Column
  private String token;

  @Column
  private Boolean isactive;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<PhoneEntity> phones = new HashSet<>();

}
