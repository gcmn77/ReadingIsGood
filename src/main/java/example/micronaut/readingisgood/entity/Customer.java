package example.micronaut.readingisgood.entity;

import io.micronaut.context.annotation.Primary;
import lombok.Data;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@MappedEntity
@Data
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @Primary
    private String email; //todo must unique
//    @NotNull
//    private String password;
    private String name;
}
