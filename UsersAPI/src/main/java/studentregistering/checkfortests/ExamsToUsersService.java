package studentregistering.checkfortests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExamsToUsersService {
    private final RestTemplate restTemplate;

    @Autowired
    public ExamsToUsersService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean examsExist() {
        String url = "http://localhost:8081/api/v1/exams";

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return true;
        } catch (Exception e) {
            System.err.println("Exception caught: " + e.getMessage());
            return false;
        }

    }

    public ResponseEntity<List<?>> getUserExams(Long id) {
        String url = "http://localhost:8081/api/v1/exams/examsofuser/" + id; // Exam API endpoint must include a GET request mapped for ID of User
        ResponseEntity<List<?>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<?>>() {}
        );
        List<?> userExams = response.getBody();
        if (userExams != null) {
            // Process the list of exams as needed
            return ResponseEntity.ok(userExams);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
