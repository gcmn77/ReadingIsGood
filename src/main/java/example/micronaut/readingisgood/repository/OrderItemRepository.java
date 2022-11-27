package example.micronaut.readingisgood.repository;

import example.micronaut.readingisgood.entity.Customer;
import example.micronaut.readingisgood.entity.order.Order;
import example.micronaut.readingisgood.entity.order.OrderItem;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@JdbcRepository(dialect = Dialect.H2)
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
//    List<OrderItem> findAllByOrder(Order order);
    List<OrderItem> findAll();
}
