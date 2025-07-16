package com.app.book_service.web.models.book;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequest {

    private static final String YEAR_WRONG_POINTED_MESSAGE = "Book release year must be between 1 and 2025";

    private static final String MUST_CONTAIN = "must contain between {min} and {max} characters";

    @NotBlank(message = "Title must be pointed")
    @Size(min = 1, max = 20, message = "Title " + MUST_CONTAIN)
    private String title;

    @Min(value = 1, message = YEAR_WRONG_POINTED_MESSAGE)
    @Max(value = 2025, message = YEAR_WRONG_POINTED_MESSAGE)
    private Integer year;

    @NotBlank(message = "Genre must be pointed")
    @Size(min = 5, max = 20, message = "Genre " + MUST_CONTAIN)
    private String genre;

    @NotBlank(message = "Author's name must be pointed")
    @Size(min = 2, max = 20, message = "Author's name " + MUST_CONTAIN)
    private String authorName;
}