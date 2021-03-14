package com.sdp.EducationAppProject.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Course {

    @Id
    @Column(name = "crs_id")
    private  String crsId;

    @Column(name = "usr_id")
    private  String usrId;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "created_date")
    private Date createdDate;


//    @ManyToOne
//    @JoinTable(name = "author",
//            joinColumns = { @JoinColumn(
//                    name = "crs_id",
//                    referencedColumnName = "crs_id"
//            ) },
//            inverseJoinColumns = { @JoinColumn(
//                    name = "user_id",
//                    referencedColumnName = "usr_id"
//            ) }
//    )
//    private MUser author;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = MUser.class)
    @JoinTable(name = "crs_usr",
            joinColumns = { @JoinColumn(
                    name = "crs_id",
                    referencedColumnName = "crs_id"
            ) },
            inverseJoinColumns = { @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "usr_id"
            ) }
    )
    private List<MUser> users;












}
