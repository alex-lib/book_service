package com.app.book_service;
import com.app.book_service.entities.Author;
import com.app.book_service.entities.Book;
import com.app.book_service.web.models.author.AuthorResponse;
import com.app.book_service.web.models.book.BookResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AbstractTestController {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected Author createAuthor(Long id) {
        return new Author(id, "Author " + id, (int) (1800 + id), new ArrayList<>());
    }

    protected Book createBook(Long id, Author author) {
        return new Book(id, "Book " + id, (int) (1830 + id), "Story", author);
    }

    protected AuthorResponse createAuthorResponse(Long id) {
        return new AuthorResponse(id, "Author " + id, (int) (1800 + id));
    }

    protected BookResponse createBookResponse(Long id, AuthorResponse authorResponse) {
        return new BookResponse(id, "Book " + id, (int) (1830 + id), "Story", authorResponse);
    }
}