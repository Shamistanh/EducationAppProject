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
    private  String id;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "content_documents")
    @Lob
    private List<byte []> contentDocuments;

    @Column(name = "content_links")
    @Lob
    private List<String> contentLinks;

    @ManyToOne
    @JoinTable(name = "author",
            joinColumns = { @JoinColumn(
                    name = "crs_id",
                    referencedColumnName = "crs_id"
            ) },
            inverseJoinColumns = { @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "usr_id"
            ) }
    )
    private MUser author;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = MUser.class)
    @JoinTable(name = "learners",
            joinColumns = { @JoinColumn(
                    name = "crs_id",
                    referencedColumnName = "crs_id"
            ) },
            inverseJoinColumns = { @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "usr_id"
            ) }
    )
    private List<MUser> learners;












}
