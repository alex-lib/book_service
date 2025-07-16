package com.app.book_service.services;
import com.app.book_service.entities.Book;
import java.util.List;

public interface BookService {

    Book createBook(Book book);

    Book updateBook(Long id, Book book);

    void deleteBook(Long id);

    Book findBookById(Long id);

    List<Book> findAllBooks();
}