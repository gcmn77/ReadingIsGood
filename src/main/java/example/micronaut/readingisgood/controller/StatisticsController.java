package example.micronaut.readingisgood.controller;

import example.micronaut.readingisgood.repository.OrderRepository;
import example.micronaut.readingisgood.view.MonthlyStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticsController {
    @Autowired
    private OrderRepository orderRepository;
    
    @GetMapping("/stats")
    public List<MonthlyStats> getMonthStats() {
        return orderRepository.getMonthStats();
    }
}
