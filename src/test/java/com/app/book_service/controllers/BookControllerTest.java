package com.app.book_service.controllers;
import com.app.book_service.AbstractTestController;
import com.app.book_service.StringTestUtils;
import com.app.book_service.entities.Book;
import com.app.book_service.mappers.book.BookMapper;
import com.app.book_service.services.BookService;
import com.app.book_service.web.models.book.BookRequest;
import com.app.book_service.web.models.book.BookResponse;
import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookControllerTest extends AbstractTestController {

    @MockBean
    private BookService bookService;

    @MockBean
    private BookMapper bookMapper;
    
    @Test
    @DisplayName("Test for creating book")
    public void whenCreateBook_thenReturnNewBook() throws Exception {
        Book book = new Book();
        book.setTitle("Book 1");
        book.setYear(1831);
        book.setAuthor(createAuthor(1L));

        Book createdBook = createBook(1L, createAuthor(1L));
        BookRequest bookRequest = new BookRequest("Book 1", 1831,"Story", "Author 1");
        BookResponse bookResponse = createBookResponse(1L, createAuthorResponse(1L));

        Mockito.when(bookMapper.bookRequestToBook(refEq(bookRequest))).thenReturn(book);
        Mockito.when(bookService.createBook(refEq(book))).thenReturn(createdBook);
        Mockito.when(bookMapper.bookToBookResponse(refEq(createdBook))).thenReturn(bookResponse);

        String actualResponse = mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequest)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource("responses/create_book_response.json");
        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);

        verify(bookMapper, Mockito.times(1)).bookRequestToBook(refEq(bookRequest));
        verify(bookService, Mockito.times(1)).createBook(refEq(book));
        verify(bookMapper, Mockito.times(1)).bookToBookResponse(createdBook);
    }

    @Test
    @DisplayName("Test for getting book's data by existed id")
    public void whenFindBookById_thenReturnBook() throws Exception {
        Book book = new Book();
        book.setTitle("Book 1");
        book.setYear(1831);
        book.setAuthor(createAuthor(1L));

        BookResponse bookResponse = createBookResponse(1L, createAuthorResponse(1L));

        Mockito.when(bookService.findBookById(1L)).thenReturn(book);
        Mockito.when(bookMapper.bookToBookResponse(book)).thenReturn(bookResponse);

        String actualResponse = mockMvc.perform(MockMvcRequestBuilders.get("/books/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource("responses/find_book_by_id_response.json");

        Mockito.verify(bookService, Mockito.times(1)).findBookById(1L);
        Mockito.verify(bookMapper, Mockito.times(1)).bookToBookResponse(book);
        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);
    }
}