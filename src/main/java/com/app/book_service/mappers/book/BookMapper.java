package com.app.book_service.mappers.book;
import com.app.book_service.entities.Book;
import com.app.book_service.web.models.book.BookRequest;
import com.app.book_service.web.models.book.BookResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@DecoratedWith(BookMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    Book bookRequestToBook(BookRequest bookRequest);

    BookResponse bookToBookResponse(Book book);

    @Mapping(source = "bookId", target = "id")
    Book bookRequestToBook(Long bookId,BookRequest bookRequest);
}