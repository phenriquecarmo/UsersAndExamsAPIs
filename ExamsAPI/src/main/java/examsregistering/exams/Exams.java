package examsregistering.exams;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Exams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testId;
    private Long userId;
    private String testTitle;
    private String testDescription;
    @Column(nullable = true)
    private Double testDurationInMinutes;
    private LocalDate startingDate;
    private String examStatus;

    public Exams() {
    }

    public Exams(Long testId, Long userId, LocalDate startDate, String status) {
        this.testId = testId;
        this.userId = userId;
        this.startingDate = startDate;
        this.examStatus = status;
    }

    public Exams(Long testId, String testTitle, String testDescription, Double testDurationInMinutes) {
        this.testId = testId;
        this.testTitle = testTitle;
        this.testDescription = testDescription;
        this.testDurationInMinutes = testDurationInMinutes;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTestDurationInMinutes(Double testDurationInMinutes) {
        this.testDurationInMinutes = testDurationInMinutes;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public String getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(String examStatus) {
        this.examStatus = examStatus;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public double getTestDurationInMinutes() {
        return testDurationInMinutes;
    }

    public void setTestDurationInMinutes(double testDurationInMinutes) {
        this.testDurationInMinutes = testDurationInMinutes;
    }

    @Override
    public String toString() {
        return "MockTests{" +
                "testId=" + testId +
                ", testTitle='" + testTitle + '\'' +
                ", testDescription='" + testDescription + '\'' +
                ", testDurationInMinutes=" + testDurationInMinutes +
                '}';
    }
}
