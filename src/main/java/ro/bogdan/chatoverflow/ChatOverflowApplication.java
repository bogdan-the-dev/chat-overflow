package ro.bogdan.chatoverflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@SpringBootApplication
public class ChatOverflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatOverflowApplication.class, args);
    }

    @RequestMapping("/token")
    public Map<String,String> token(HttpSession session) {
        return Collections.singletonMap("token", session.getId());
    }


}
