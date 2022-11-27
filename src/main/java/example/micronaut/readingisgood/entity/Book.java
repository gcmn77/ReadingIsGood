package example.micronaut.readingisgood.entity;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@MappedEntity
@Data
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @Nullable
    private String author;
    @Nullable
    private String description;

    @NotNull
    @Positive
    private int stock = 0;
}
