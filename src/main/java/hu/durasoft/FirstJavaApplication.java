package hu.durasoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"hu.durasoft.controller","hu.durasoft.domain","hu.durasoft.repository","hu.durasoft.service"})
@EntityScan(basePackages = "hu.durasoft.domain")
@EnableJpaRepositories(basePackageClasses = {hu.durasoft.repository.BloggerRepository.class, hu.durasoft.repository.StoryRepository.class})
public class FirstJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstJavaApplication.class, args);
    }

}
