package studentregistering.studentusers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studentregistering.checkfortests.ExamsToUsersService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @Autowired
    ExamsToUsersService examsToUsersService;

    @PostMapping
    public void postUser(@RequestBody Users user) {
        usersService.createUsers(user);
    }

    @GetMapping
    public List<Users> getUser() {
        return usersService.getUsers();
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<Users> getSpecificUser(@PathVariable("userId") Long id) {
        Optional<Users> user = usersService.getSpecificUser(id);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

//        The expression above is the same as this:
//        if (user.isPresent()) {
//            return ResponseEntity.ok(user.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }

    }

    @GetMapping(path = "/testsofuser/{id}")
    public ResponseEntity<List<?>> getUserTests(@PathVariable("id") Long id) {
            if (examsToUsersService.examsExist()) {
                return examsToUsersService.getUserExams(id);
            } else {
                return null;
            }

        }


    @PutMapping(path = "{userId}")
    public void putUser(@PathVariable("userId") Long userId,
                        @RequestParam(required = false) String userName,
                        @RequestParam(required = false) String dateOfBirth,
                        @RequestParam(required = false) String email) {

        usersService.updateUsers(userId, userName, dateOfBirth, email);

    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        usersService.deleteUsers(userId);
    }



}
