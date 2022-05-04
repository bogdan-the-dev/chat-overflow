package ro.bogdan.chatoverflow.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Moderator1").password(passwordEncoder().encode("mode123!")).roles("MODERATOR");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/moderator/**").hasRole("MODERATOR")
                .antMatchers("/login").permitAll()
                .antMatchers("/questions/create-question").authenticated()
                .antMatchers("/questions/delete-question").authenticated()
                .antMatchers("/answers/edit").authenticated()
                .antMatchers("/answers/create").authenticated()
                .antMatchers("/answers/delete").authenticated()
                .antMatchers("/users/ban-user").hasRole("MODERATOR")
                .antMatchers("/users/deleteUser").hasRole("USER")
                .antMatchers("/users/editUser").authenticated()
                .anyRequest().permitAll();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


