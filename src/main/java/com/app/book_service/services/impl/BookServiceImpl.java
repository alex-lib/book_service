package com.app.book_service.services.impl;
import com.app.book_service.entities.Book;
import com.app.book_service.exceptions.EntityNotFoundException;
import com.app.book_service.repositories.BookRepository;
import com.app.book_service.services.BookService;
import com.app.book_service.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    @Override
    public Book findBookById(Long id) {
        log.info("Call method findBookById to find book with id: {}", id);
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
    }

    @Transactional
    @Override
    public Book createBook(Book book) {
        log.info("Created new book: {}", book);
        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book updateBook(Long id, Book book) {
        Book existedBook = findBookById(id);
        BeanUtils.copyNonNullProperties(book, existedBook);
        log.info("Updated book: {}", existedBook);
        return bookRepository.save(existedBook);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAllBooks() {
        log.info("Call method findAllBooks");
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
        log.info("Call method deleteBook to delete book with id: {}", id);
        bookRepository.deleteById(id);
    }
}