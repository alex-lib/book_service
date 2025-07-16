package com.app.book_service.services.impl;
import com.app.book_service.entities.Author;
import com.app.book_service.exceptions.EntityIsExistedException;
import com.app.book_service.exceptions.EntityNotFoundException;
import com.app.book_service.repositories.AuthorRepository;
import com.app.book_service.services.AuthorService;
import com.app.book_service.web.models.author.AuthorFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public Author createAuthor(Author author) {
        if (authorRepository.findByName(author.getName()).isPresent())
            throw new EntityIsExistedException("Author with name already exists: " + author.getName());
        log.info("Created new author: {}",author);
        return authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    @Override
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> findAllAuthors(AuthorFilter authorFilter) {
        log.info("Call method findAllAuthors");
        return authorRepository.findAll(PageRequest.of(authorFilter.getPage(),
                authorFilter.getSize())).getContent();
    }

    @Override
    public Author findAuthorByName(String name) {
        return authorRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with name: " + name
                        + ". Before creating book i must create author."));
    }
}