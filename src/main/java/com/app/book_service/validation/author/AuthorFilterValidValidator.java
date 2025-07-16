package com.app.book_service.validation.author;
import com.app.book_service.web.models.author.AuthorFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

public class AuthorFilterValidValidator implements ConstraintValidator<AuthorFilterValid, AuthorFilter> {

    @Override
    public boolean isValid(AuthorFilter authorFilter, ConstraintValidatorContext constraintValidatorContext) {
        return !ObjectUtils.anyNull(authorFilter.getPage(), authorFilter.getSize());
    }
}