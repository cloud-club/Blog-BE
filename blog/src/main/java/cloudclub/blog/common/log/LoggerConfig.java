package cloudclub.blog.common.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class LoggerConfig {

    @Bean
    public BlogLogger blogLogger() {
        return new BlogLogger();
    }

}
