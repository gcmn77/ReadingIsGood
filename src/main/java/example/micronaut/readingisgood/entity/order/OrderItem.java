package example.micronaut.readingisgood.entity.order;

import example.micronaut.readingisgood.entity.Book;
import io.micronaut.data.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@MappedEntity("orderItems")
@Data
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

//    @Relation(value = Relation.Kind.MANY_TO_ONE)
//	@MappedProperty(value = "order_id")
	private Order order;

//    @Relation(value = Relation.Kind.MANY_TO_ONE, cascade = Relation.Cascade.PERSIST)
    private Book book;

    @NotNull
    private int quantity;

    private BigDecimal totalAmount;
}
