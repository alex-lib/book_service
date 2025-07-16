package com.app.book_service.web.models.author;
import com.app.book_service.validation.author.AuthorFilterValid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AuthorFilterValid
@Getter
@Setter
@NoArgsConstructor
public class AuthorFilter {

    private Integer page;

    private Integer size;
}