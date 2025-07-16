package com.app.book_service.mappers.author;
import com.app.book_service.entities.Author;
import com.app.book_service.web.models.author.AuthorRequest;
import com.app.book_service.web.models.author.AuthorResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@DecoratedWith(AuthorMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorMapper {

    Author authorRequestToAuthor(AuthorRequest authorRequest);

    AuthorResponse authorToAuthorResponse(Author author);
}
