package examsregistering.exams;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExamsService {
    ExamsRepository examsRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public ExamsService(ExamsRepository examsRepository) {
        this.examsRepository = examsRepository;
    }

    public void createTests(Exams exams) {
        examsRepository.save(exams);
    }

    public List<Exams> getTests() {
        return examsRepository.findAll();
    }


    public List<Exams> getExamsByUserId(Long userId) {
        List<Exams> exams = examsRepository.findByUserId(userId);

        if (exams.isEmpty()) {
            throw new EntityNotFoundException("Exams with User ID " + userId + " not found");
        } else {
            return exams;
        }

    }


    public boolean examExists(Long testId) {
        return examsRepository.existsById(testId); // Return true if test exists
    }

    public Exams getSpecificExam(Long examId) {
        return examsRepository.findById(examId)
                .orElseThrow(() -> new EntityNotFoundException("Exam not found"));
    }

    public void updateExams(Exams exams) {
        examsRepository.save(exams);
    }

    public void deleteTests(Long testId) {
        boolean testIsPresent = examsRepository.existsById(testId);
        if (!testIsPresent) {
            throw new IllegalStateException("Test with ID " + testId + " does not exists");
        } else {
            examsRepository.deleteById(testId);
        }
    }

    @Transactional
    public void updateTests(Long testId, String testTitle, String testDescription, Double testDurationInMinutes) {
        Exams exams = examsRepository.findById(testId).orElseThrow(
                () -> new IllegalStateException(
                        "Test ID " + testId + " does not exists"
                )
        );

        if (testTitle != null && !testTitle.isEmpty() && !Objects.equals(exams.getTestTitle(), testTitle)) {
            // If name is not null, and it has more than 0 characters, and it is not equals to the one that exists already:
            exams.setTestTitle(testTitle);
        }

        if (testDescription != null && !testDescription.isEmpty() && !Objects.equals(exams.getTestDescription(), testDescription)) {
            exams.setTestDescription(testDescription);
        }

        if (testDurationInMinutes != null && testDurationInMinutes > 0 && !Objects.equals(exams.getTestDurationInMinutes(), testDurationInMinutes)) {
            exams.setTestDurationInMinutes(testDurationInMinutes);
        }



    }



}
