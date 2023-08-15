package examsregistering.uservalidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// Service to handle communication with API 2
@Service
public class UserValidationService {
    private final RestTemplate restTemplate;

    @Autowired
    public UserValidationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean userExists(Long userId) {
        // Appending userId to base url
        String url = "http://localhost:8080/api/v1/users/" + userId;

        try {
            // Sending GET Request
            restTemplate.getForObject(url, String.class);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
