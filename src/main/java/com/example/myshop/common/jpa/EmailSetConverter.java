package com.example.myshop.common.jpa;

import com.example.myshop.common.model.Email;
import com.example.myshop.common.model.EmailSet;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EmailSetConverter implements AttributeConverter<EmailSet, String> {

    @Override
    public String convertToDatabaseColumn(EmailSet attribute) {
        if (attribute == null)
            return null;

        return attribute.getEmails().stream()
                .map(Email::getAddress)
                .collect(Collectors.joining(","));
    }

    @Override
    public EmailSet convertToEntityAttribute(String value) {
        if (value == null)
            return null;

        String[] emails = value.split(",");
        Set<Email> emailsSet = Arrays.stream(emails)
                .map(Email::new)
                .collect(Collectors.toSet());
        return new EmailSet(emailsSet);

    }
}
