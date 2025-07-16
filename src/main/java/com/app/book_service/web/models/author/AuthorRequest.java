package com.app.book_service.web.models.author;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.*;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequest {

    private static final String BIRTH_YEAR_WRONG_POINTED_MESSAGE = "Birth year must be between 0 and 2007 or it can be unspecified";

    @NotBlank(message = "Name must be pointed")
    @Size(min = 2, max = 20, message = "Name must contain between {min} and {max} characters")
    private String name;

    @Nullable
    @Max(value = 2007, message = BIRTH_YEAR_WRONG_POINTED_MESSAGE)
    @Min(value = 0, message = BIRTH_YEAR_WRONG_POINTED_MESSAGE)
    private Integer birthYear;
}