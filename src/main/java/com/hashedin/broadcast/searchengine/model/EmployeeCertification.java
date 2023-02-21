package com.hashedin.broadcast.searchengine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeCertification {
    String name;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    LocalDateTime accomplishedDate;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    LocalDateTime validityDate;
}
