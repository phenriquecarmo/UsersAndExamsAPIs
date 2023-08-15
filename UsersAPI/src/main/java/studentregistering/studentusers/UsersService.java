package studentregistering.studentusers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersService {
    UserRepository userRepository;

    @Autowired
    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUsers(Users user) { // POST Method
        // Create your conditionals here (If there are people with same name.. E.t.c)
        userRepository.save(user);
    }

    public List<Users> getUsers() { // GET Method
       return userRepository.findAll();
    }

    public Optional<Users> getSpecificUser(Long id) { // GET method consult user for a specific ID
        Users users = userRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(
                        "User with ID " + id + " does not exists"
                )
        );
        return userRepository.findById(id);
    }

    @Transactional // PUT Method
    public void updateUsers(Long userId, String userName, String dateOfBirth, String email) {

        Users users = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException(
                        "User with ID " + userId + " does not exists"
                )
        );

        if (userName != null && !userName.isEmpty() && !Objects.equals(users.getName(), userName)) {
            users.setName(userName);
        }

        if (dateOfBirth != null && !dateOfBirth.isEmpty() && !Objects.equals(users.getDateOfBirth(), dateOfBirth)) {
            users.setDateOfBirth(dateOfBirth);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(users.getEmail(), email)) {
            users.setEmail(email);
        }

    }

    public void deleteUsers(Long userId) { // DELETE Method
        boolean id_exists = userRepository.existsById(userId);
        if (!id_exists) {
            throw new IllegalStateException("Test with ID " + userId + " not found");
        } else {
            userRepository.deleteById(userId);
        }
    }






}
