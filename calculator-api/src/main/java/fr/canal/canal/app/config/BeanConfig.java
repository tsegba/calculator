package fr.canal.canal.app.config;

import fr.canal.canal.domain.service.CalculatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public CalculatorFactory calculatorFactory() {
        return new CalculatorFactory();
    }

}
