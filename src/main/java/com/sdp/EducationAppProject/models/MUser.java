package com.sdp.EducationAppProject.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class MUser {

    @Id
   //@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "usr_id")
    String id;

    @Column(name = "full_name")
    @NotBlank
    String username;

    @Column(name = "username")
    @Email
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "isVerified")
    Boolean isVerified;

    @Column(name = "isTeacher")
    Boolean isTeacher;


    @ManyToMany(mappedBy = "users", targetEntity = Course.class)
    private List<Course> enrolledCourses;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public MUser(String id, String username, String email, String encode) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = encode;
    }

//
//    @ManyToOne
//    @JoinTable(name = "enrolledCourses",
//            joinColumns = { @JoinColumn(
//                    name = "crs_id",
//                    referencedColumnName = "crs_id"
//            ) },
//            inverseJoinColumns = { @JoinColumn(
//                    name = "user_id",
//                    referencedColumnName = "usr_id"
//            ) }
//    )
//    private XUser author;




}
