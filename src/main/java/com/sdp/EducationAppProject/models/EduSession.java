package com.sdp.EducationAppProject.models;


import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class EduSession {

    @Id
    private String id;

    @Column(name = "roles")
    String roles;

    @Column(name = "accessToken")
    String accessToken;

    @Column(name = "userId")
    String userId;

    @Column(name = "created_date")
    Date createdDate;

    @Column(name = "username")
    String username;

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }

    public EduSession(){}
}
