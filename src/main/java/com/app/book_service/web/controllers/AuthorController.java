package com.app.book_service.web.controllers;
import com.app.book_service.mappers.author.AuthorMapper;
import com.app.book_service.services.AuthorService;
import com.app.book_service.web.models.author.AuthorFilter;
import com.app.book_service.web.models.author.AuthorRequest;
import com.app.book_service.web.models.author.AuthorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    private final AuthorMapper authorMapper;

    @PostMapping
    public AuthorResponse createAuthor(@RequestBody @Valid AuthorRequest authorRequest) {
        return authorMapper.authorToAuthorResponse(authorService
                .createAuthor(authorMapper.authorRequestToAuthor(authorRequest)));
    }

    @GetMapping("/{id}")
    public AuthorResponse findAuthorById(@PathVariable Long id) {
        return authorMapper.authorToAuthorResponse(authorService.findAuthorById(id));
    }

    @GetMapping
    public List<AuthorResponse> findAllAuthors(@Valid AuthorFilter authorFilter) {
        return authorService.findAllAuthors(authorFilter).stream()
                .map(authorMapper::authorToAuthorResponse)
                .toList();
    }
}