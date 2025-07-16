package com.app.book_service.mappers.book;
import com.app.book_service.entities.Author;
import com.app.book_service.entities.Book;
import com.app.book_service.mappers.author.AuthorMapper;
import com.app.book_service.services.AuthorService;
import com.app.book_service.web.models.author.AuthorResponse;
import com.app.book_service.web.models.book.BookRequest;
import com.app.book_service.web.models.book.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BookMapperDelegate implements BookMapper {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public Book bookRequestToBook(BookRequest bookRequest) {
        Author author = authorService.findAuthorByName(bookRequest.getAuthorName());
        return Book.builder()
                .title(bookRequest.getTitle())
                .year(bookRequest.getYear())
                .genre(bookRequest.getGenre())
                .author(author)
                .build();
    }

    @Override
    public BookResponse bookToBookResponse(Book book) {
        AuthorResponse authorResponse = authorMapper.authorToAuthorResponse(book.getAuthor());
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .year(book.getYear())
                .genre(book.getGenre())
                .author(authorResponse)
                .build();
    }

    @Override
    public Book bookRequestToBook(Long bookId, BookRequest bookRequest) {
        Book book = bookRequestToBook(bookRequest);
        book.setId(bookId);
        return book;
    }
}