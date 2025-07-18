package cs.backend.controller;

import cs.backend.dto.ResponseMessage;
import cs.backend.pojo.Statistics;
import cs.backend.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;
    @GetMapping("/getAllNumber")
    public ResponseMessage<Statistics> getAllNumber() {
        Statistics statistics = statisticsService.getAllNumber();
        return ResponseMessage.success(statistics);
    }
}
