package example.micronaut.readingisgood.repository;

import example.micronaut.readingisgood.entity.Customer;
import example.micronaut.readingisgood.entity.order.Order;
import example.micronaut.readingisgood.view.MonthlyStats;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

@JdbcRepository(dialect = Dialect.H2)
@Join(value = "customer", type = Join.Type.FETCH)
//@Join(value = "orderItems", type = Join.Type.LEFT_FETCH)
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByCustomer(Customer customer);
    List<Order> findAll();
    List<Order> findAllByOrderDateAfterAndOrderDateBefore(Date startDate, Date endDate);
    //TODO AG:  Query: Unable to prepare query
    @Query("SELECT new (MONTH(o.orderDate), COUNT(o.id), SUM(o.quantity), SUM(o.totalAmount)) FROM Order o GROUP BY MONTH(o.orderDate) ORDER BY MONTH(o.orderDate)")
    List<MonthlyStats> getMonthStats();
}
