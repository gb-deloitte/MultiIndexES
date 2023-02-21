package com.hashedin.broadcast.searchengine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeSkillDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(targetEntity = Skill.class,cascade = CascadeType.DETACH)
    @JoinColumn(name ="skill_fk",referencedColumnName = "id")
    Skill skill;

    @Column
    private String rating;

}
