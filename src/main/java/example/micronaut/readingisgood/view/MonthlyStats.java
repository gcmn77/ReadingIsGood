package example.micronaut.readingisgood.view;

import java.math.BigDecimal;

public record MonthlyStats(String month,
    Long totalOrderCount,
    Long totalBookCount,
    BigDecimal totalAmount) {
}
