package br.com.ipet.catalog.domain.model;

import br.com.ipet.catalog.domain.model.commons.exception.FieldCannotBeEmptyException;
import br.com.ipet.catalog.domain.model.service.StartTimeMustBeBeforeEndTimeException;
import org.springframework.util.StringUtils;

import java.time.OffsetDateTime;
import java.util.Collection;

public class FieldValidator {

    private FieldValidator(){}

    public static void requiresNonNull(String field, Object value) {
        if (value == null)
            throw new FieldCannotBeEmptyException(field);
    }

    public static void requiresNonBlank(String field, String value) {
        if (!StringUtils.hasText(value))
            throw new FieldCannotBeEmptyException(field);
    }

    public static void requiresNonEmpty(String field, Collection<?> value) {
        requiresNonNull(field, value);

        if (value.isEmpty())
            throw new FieldCannotBeEmptyException(field);
    }

    public static void requireStartDateTimeIsBeforeEndTime(OffsetDateTime startDateTime, OffsetDateTime endDateTime) {
        if (!startDateTime.isBefore(endDateTime))
            throw new StartTimeMustBeBeforeEndTimeException(startDateTime.toString(), endDateTime.toString());
    }

}
