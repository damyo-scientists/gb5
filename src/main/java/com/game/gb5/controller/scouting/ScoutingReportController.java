package com.game.gb5.controller.scouting;


import com.game.gb5.model.user.User;
import com.game.gb5.model.report.ScoutingReport;
import com.game.gb5.model.scouting.Scouter;
import com.game.gb5.service.user.UserService;
import com.game.gb5.service.scouting.ScoutingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scouting-reports")
@Tag(name = "Scouting Reports")
public class ScoutingReportController {
    @Autowired
    private ScoutingService scoutingService;

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity makeScoutingReport(@RequestParam("scouter_id") Long scouterId,
                                             @RequestParam("user_id") Long userId) {
        Scouter scouter = scoutingService.getScouterById(scouterId);
        User user = userService.getById(userId);
        ScoutingReport scoutingReport = scoutingService.makeNewScoutingReport(scouter, user);
        if (scoutingReport != null) {
            return new ResponseEntity<>(scoutingReport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Scouting Report is null", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
