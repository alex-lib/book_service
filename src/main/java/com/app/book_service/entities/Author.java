package com.app.book_service.entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_year", nullable = true)
    private Integer birthYear;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Book> books = new ArrayList<>();
}