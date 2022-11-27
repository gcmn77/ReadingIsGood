package example.micronaut.readingisgood.repository;

import example.micronaut.readingisgood.view.MonthlyStats;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

public interface StatisticsRepository extends CrudRepository<MonthlyStats, Long> {
    @Query("SELECT MONTH(order.dateCreated) as Month, COUNT(order.id) as totalOrderCount, SUM(orderItem.quantity) as totalBookCount, SUM(orderItem.totalAmount) as totalPurchasedAmount FROM Order order LEFT JOIN orderItem orderItem ON order.id = orderItem.order_id GROUP BY Month(order.dateCreated) order By MONTH(order.dateCreated)")
    List<MonthlyStats> getMonthStats();
}
