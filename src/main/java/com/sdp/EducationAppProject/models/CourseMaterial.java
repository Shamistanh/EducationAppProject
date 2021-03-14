package com.sdp.EducationAppProject.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class CourseMaterial {

    @Id
    @Column(name = "ln_id")
    private String lnId;

    @Column(name = "created_date")
    private Date date;

    @Column(name = "usr_id")
    private String userId;

    @Column(name = "is_document")
    private Boolean isDocument;

    @Column(name = "crs_id")
    private String courseId;

    @Column(name = "link")
    private String link;

    @Column(name = "document")
    @Lob
    private byte[] document;



}
