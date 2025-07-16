package com.app.book_service.web.models.author;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AuthorResponse {

    private Long id;

    private String name;

    private Integer birthYear;
}