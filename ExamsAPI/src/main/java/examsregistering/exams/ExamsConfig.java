package examsregistering.exams;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


// Configuration classes are used to configure beans, various application settings, and external components

@Configuration
public class ExamsConfig {

    @Bean // Instantiating a class managed by Spring IoC containers
    CommandLineRunner commandLineRunner(ExamsRepository examsRepository) {
        return args -> {
            Exams english = new Exams(
                    1L,
                    "English Test",
                    "grammar and linguistics",
                    60.0
            );

            Exams math = new Exams(
                    2L,
                    "Math Test",
                    "algebra",
                    90.0
            );

            Exams portuguese = new Exams(
                    3L,
                    "Portuguese Test",
                    "portuguese foreign language",
                    60.0
            );

            Exams science = new Exams(
                    4L,
                    "Science Test",
                    "human body and its organs",
                    90.0
            );

            Exams japanese = new Exams(
                    5L,
                    "Japanese Test",
                    "japanese foreign language",
                    60.0
            );

            Exams history = new Exams(
                    6L,
                    "History Test",
                    "ancient egypt",
                    60.0
            );

            Exams geography = new Exams(
                    7L,
                    "Geography Test",
                    "weather worldwide",
                    60.0
            );

            Exams physicalActivity = new Exams(
                    8L,
                    "PE Test",
                    "volleyball",
                    30.0
            );

            examsRepository.saveAll(
                    List.of(english, math, portuguese, science, japanese, history, geography, physicalActivity)
            );


        };
    }
}
