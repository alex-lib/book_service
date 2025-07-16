package com.app.book_service.web.models.errors;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErrorResponse {

    private String errorMessage;
}