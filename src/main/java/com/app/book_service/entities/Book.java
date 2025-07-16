package com.app.book_service.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "release_year")
    private int year;

    @Column(name = "genre")
    private String genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}