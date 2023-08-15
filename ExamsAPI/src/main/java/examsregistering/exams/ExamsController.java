package examsregistering.exams;

import examsregistering.uservalidation.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/exams")
public class ExamsController {
    ExamsService examsService;
    @Autowired
    public ExamsController(ExamsService examsService) {
        this.examsService = examsService;
    }

    @Autowired
    UserValidationService userValidationService; // Service to handle communication with API 2

    @PostMapping
    public void createMockTests(@RequestBody Exams exams) {
        // RequestBody converts JSON to an instance of a Class
        examsService.createTests(exams);
    }

    @PostMapping(path = "/start")
    public ResponseEntity<String> startTest(@RequestBody StartExamRequest request) {
        // Check if user exists in API 2
        if (!userValidationService.userExists(request.getUserId())) { // Using userId of payload as id parameter to "userExists"
            return ResponseEntity.badRequest().body("User not found");
        }

        LocalDate currentTime = LocalDate.now();

        if (examsService.examExists(request.getTestId())) {
            Exams examsToUpdate = examsService.getSpecificExam(request.getTestId());

            examsToUpdate.setUserId(request.getUserId());
            examsToUpdate.setStartingDate(currentTime);
            examsToUpdate.setExamStatus("ongoing");

            examsService.updateExams(examsToUpdate);
        }


        return ResponseEntity.ok("Test started successfully");
    }

    @GetMapping
    public List<Exams> getTests() {
        return examsService.getTests();
    }

    @GetMapping(path = "/examsofuser/{studentId}")
    public ResponseEntity<List<Exams>> getExamTestsByUserId(@PathVariable("studentId") Long id) {
        List<Exams> exams = examsService.getExamsByUserId(id);

        return ResponseEntity.ok(exams);
    }


    @PutMapping(path = "{testId}")
    public void updateTests(@PathVariable("testId") Long testId,
                            @RequestParam(required = false) String testTitle,
                            @RequestParam(required = false) String testDescription,
                            @RequestParam(required = false) Double testDurationInMinutes) {
        examsService.updateTests(testId, testTitle, testDescription, testDurationInMinutes);
    }

    @DeleteMapping(path = "{testId}")
    public void deleteTest(@PathVariable("testId") Long testId) {
        examsService.deleteTests(testId);
    }


}
