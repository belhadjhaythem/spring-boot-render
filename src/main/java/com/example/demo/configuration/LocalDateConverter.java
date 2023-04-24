package com.example.demo.configuration;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {
 
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return Optional.ofNullable(localDate != null ? localDate.plusDays(1) : localDate)
          .map(Date::valueOf)
          .orElse(new Date(Calendar.getInstance().getTime().getTime()));
    }
 
    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return Optional.ofNullable(date)
          .map(Date::toLocalDate)
          .orElse(LocalDate.now());
    }
}