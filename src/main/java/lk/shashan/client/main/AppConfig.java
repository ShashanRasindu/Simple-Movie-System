package lk.shashan.client.main;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("lk.shashan.client")
@Import(JpaConfig.class)
public class AppConfig {


}
