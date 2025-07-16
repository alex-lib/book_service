package com.app.book_service.web.models.book;
import com.app.book_service.web.models.author.AuthorResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {

    private Long id;

    private String title;

    private int year;

    private String genre;

    private AuthorResponse author;
}