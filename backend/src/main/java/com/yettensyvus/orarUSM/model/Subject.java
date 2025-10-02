package com.yettensyvus.orarUSM.model;

import com.yettensyvus.orarUSM.model.enums.SubjectTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subject")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "lessons")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // e.g. "Algorithms", "Databases"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubjectTypeEnum type; // COURSE, LABORATORY, SEMINAR, etc.

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Lesson> lessons = new HashSet<>();
}
