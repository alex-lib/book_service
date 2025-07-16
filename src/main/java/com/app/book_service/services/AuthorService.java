package com.app.book_service.services;
import com.app.book_service.entities.Author;
import com.app.book_service.web.models.author.AuthorFilter;

import java.util.List;

public interface AuthorService {

    Author createAuthor(Author author);

    Author findAuthorById(Long id);

    List<Author> findAllAuthors(AuthorFilter authorFilter);

    Author findAuthorByName(String name);
}