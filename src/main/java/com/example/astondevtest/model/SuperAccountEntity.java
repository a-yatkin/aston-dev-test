package com.example.astondevtest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "super_account", schema = "aston_test")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SuperAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "pincode")
    String pinCode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "superAccount")
    @JsonManagedReference
    List<AccountNumberEntity> accountNumbers;
}
