package com.app.book_service.mappers.author;
import com.app.book_service.entities.Author;
import com.app.book_service.web.models.author.AuthorRequest;
import com.app.book_service.web.models.author.AuthorResponse;

public abstract class AuthorMapperDelegate implements AuthorMapper {

    @Override
    public Author authorRequestToAuthor(AuthorRequest authorRequest) {
        Integer birthYear = authorRequest.getBirthYear() == null ? null : authorRequest.getBirthYear();
        return Author.builder()
                .name(authorRequest.getName())
                .birthYear(birthYear)
                .build();
    }

    @Override
    public AuthorResponse authorToAuthorResponse(Author author) {
        Integer birthYear = author.getBirthYear() == null ? null : author.getBirthYear();
        return AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .birthYear(birthYear)
                .build();
    }
}