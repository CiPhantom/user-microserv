package usermicroservice.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository){
        return args -> {
            log.info("Creating default user : " + userRepository.save(
                    new User("admin user", "admin", "admin", "admin@admin.admin", "admin")));
        };
    }
}
