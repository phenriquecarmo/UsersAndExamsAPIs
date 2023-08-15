package examsregistering.exams;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StartExamRequest { // DTO Class to represent the payload of POST Request

    @Id
    private Long userId;

    @Id
    private Long testId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }


}
