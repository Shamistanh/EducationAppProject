package com.sdp.EducationAppProject.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class XUser {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    String id;

    @Column(name = "full_name")
    @NotBlank
    String fullName;

    @Column(name = "username")
    @Email
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "isVerified")
    Boolean isVerified;

    @Column(name = "isTeacher")
    Boolean isTeacher;




}
