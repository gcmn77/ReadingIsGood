package example.micronaut.readingisgood.entity.order;

import example.micronaut.readingisgood.entity.Book;
import example.micronaut.readingisgood.entity.Customer;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;

@MappedEntity
@Data
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Nullable
    private String orderDescription;
    @Relation(value = Relation.Kind.MANY_TO_ONE, cascade = Relation.Cascade.PERSIST)
    @NotNull
    private Customer customer;
    @Nullable
    private String address;
    @DateCreated
    Date orderDate;

    @Relation(value = Relation.Kind.MANY_TO_ONE, cascade = Relation.Cascade.PERSIST)
    @NotNull
    private Book book;

    @NotNull
    @Positive
    private int quantity;

    private BigDecimal totalAmount;

//    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "order", cascade = Relation.Cascade.PERSIST)
//    private List<OrderItem> orderItems;
}
