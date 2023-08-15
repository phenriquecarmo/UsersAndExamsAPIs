package studentregistering.studentusers;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


// Configuration classes are used to configure beans, various application settings, and external components

@Configuration
public class UserConfig {

    @Bean // Instantiating a class managed by Spring IoC containers
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            Users paulo = new Users(
                    1L,
                    "Paulo",
                    "January 15, 2004",
                    "henryphcog@gmail.com"
            );

            Users levi = new Users(
                    2L,
                    "Levi",
                    "June 19, 2004",
                    "levilima18@gmail.com"
            );

            Users washington = new Users(
                    3L,
                    "Paulo Washington",
                    "March 2, 1984",
                    "paulopws@yahoo.com"
            );

            Users juliana = new Users(
                    4L,
                    "Juliana",
                    "October 30, 1983",
                    "jhulyph@hotmail.com"
            );

            Users billy = new Users(
                    5L,
                    "Billy the Dog",
                    "March 10, 2017",
                    "littlebilly@gmail.com"
            );

            userRepository.saveAll(
                    List.of(paulo, levi, washington, juliana, billy)
            );


        };
    }
}
