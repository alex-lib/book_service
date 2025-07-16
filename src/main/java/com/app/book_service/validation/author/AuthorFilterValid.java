package com.app.book_service.validation.author;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AuthorFilterValidValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorFilterValid {

    String message() default "Fields of pagination must be pointed!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}