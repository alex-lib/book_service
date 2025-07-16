package com.app.book_service.web.controllers;
import com.app.book_service.mappers.book.BookMapper;
import com.app.book_service.services.BookService;
import com.app.book_service.web.models.book.BookRequest;
import com.app.book_service.web.models.book.BookResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

   private final BookService bookService;

   private final BookMapper bookMapper;

   @ResponseStatus(HttpStatus.CREATED)
   @PostMapping
   public BookResponse createBook(@RequestBody @Valid BookRequest bookRequest) {
        return bookMapper.bookToBookResponse(bookService
                .createBook(bookMapper.bookRequestToBook(bookRequest)));
   }

   @GetMapping("/{id}")
    public BookResponse findBookById(@PathVariable Long id) {
       return bookMapper.bookToBookResponse(bookService
               .findBookById(id));
   }

   @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable Long id, @RequestBody @Valid BookRequest bookRequest) {
        return bookMapper.bookToBookResponse(bookService
                .updateBook(id, bookMapper.bookRequestToBook(id, bookRequest)));
   }

   @ResponseStatus(HttpStatus.NO_CONTENT)
   @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
       bookService.deleteBook(id);
   }

   @GetMapping
    public List<BookResponse> findAllBooks() {
      return bookService.findAllBooks().stream()
              .map(bookMapper::bookToBookResponse)
              .toList();
   }
}