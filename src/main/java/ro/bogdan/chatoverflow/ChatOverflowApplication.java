package ro.bogdan.chatoverflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"ro.bogdan.chatoverflow.service"})
public class ChatOverflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatOverflowApplication.class, args);
    }

}
