package com.isft194.gestin.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "career")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "turn")
    private String turn;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "career_subject", joinColumns = @JoinColumn(name = "career_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjects;
}
