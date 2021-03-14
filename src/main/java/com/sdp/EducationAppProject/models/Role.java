package com.sdp.EducationAppProject.models;


import com.sdp.EducationAppProject.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, targetEntity = MUser.class)
    private List<MUser> users;

    public Role (String id, ERole erole) {
        this.id = id;
        this.name = erole;
    }


}