package com.app.book_service.controllers;
import com.app.book_service.AbstractTestController;
import com.app.book_service.StringTestUtils;
import com.app.book_service.entities.Author;
import com.app.book_service.mappers.author.AuthorMapper;
import com.app.book_service.services.AuthorService;
import com.app.book_service.web.models.author.AuthorRequest;
import com.app.book_service.web.models.author.AuthorResponse;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import net.javacrumbs.jsonunit.JsonAssert;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthorControllerTest extends AbstractTestController {

    @MockBean
    private AuthorService authorService;

    @MockBean
    private AuthorMapper authorMapper;

    @Test
    @DisplayName("Test for creating author")
    public void whenCreateAuthor_thenReturnNewAuthor() throws Exception {
        Author author = new Author();
        author.setName("Author 1");
        author.setBirthYear(1801);

        Author createdAuthor = createAuthor(1L);
        AuthorResponse authorResponse = createAuthorResponse(1L);
        AuthorRequest authorRequest = new AuthorRequest("Author 1", 1801);

        Mockito.when(authorMapper.authorRequestToAuthor(refEq(authorRequest))).thenReturn(author);
        Mockito.when(authorService.createAuthor(refEq(author))).thenReturn(createdAuthor);
        Mockito.when(authorMapper.authorToAuthorResponse(refEq(createdAuthor))).thenReturn(authorResponse);

        String actualResponse = mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorRequest)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource("responses/create_author_response.json");
        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);

        verify(authorMapper, Mockito.times(1)).authorRequestToAuthor(refEq(authorRequest));
        verify(authorService, Mockito.times(1)).createAuthor(refEq(author));
        verify(authorMapper, Mockito.times(1)).authorToAuthorResponse(createdAuthor);
    }

    @Test
    @DisplayName("Test for getting author's data by existed id")
    public void whenFindAuthorById_thenReturnAuthor() throws Exception {
        Author author = new Author();
        author.setName("Author 1");
        author.setBirthYear(1801);

        AuthorResponse authorResponse = createAuthorResponse(1L);

        Mockito.when(authorService.findAuthorById(1L)).thenReturn(author);
        Mockito.when(authorMapper.authorToAuthorResponse(author)).thenReturn(authorResponse);

        String actualResponse = mockMvc.perform(MockMvcRequestBuilders.get("/authors/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource("responses/find_author_by_id_response.json");

        Mockito.verify(authorService, Mockito.times(1)).findAuthorById(1L);
        Mockito.verify(authorMapper, Mockito.times(1)).authorToAuthorResponse(author);
        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);
    }
}