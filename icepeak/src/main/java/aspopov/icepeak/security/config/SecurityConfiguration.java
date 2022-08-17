package aspopov.icepeak.security.config;

import aspopov.icepeak.security.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity(debug = false)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/api/auth/logout/**", "POST", false),
            new AntPathRequestMatcher("/api/user-area/**"),
            new AntPathRequestMatcher("/api/work-area/**")

//            new AntPathRequestMatcher("/api/books/**", "POST", false),
//            new AntPathRequestMatcher("/api/books/**", "DELETE", false),
//            new AntPathRequestMatcher("/auth/logout/**", "POST", false),
//            new AntPathRequestMatcher("/api/comments/**", "POST", false),
//            new AntPathRequestMatcher("/api/comments/**", "DELETE", false)
    );


    private final UserService userService;

    private final AuthenticationProvider provider;

    public SecurityConfiguration(UserService userService, AuthenticationProvider provider) {
        this.userService = userService;
        this.provider = provider;
    }

    @Override
    public void configure( HttpSecurity http ) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authenticationProvider(provider)
                    .addFilterBefore(authenticationFilter(), AnonymousAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers("/api/public/**")
                    .permitAll()
                .and()
                    .authorizeRequests()
                    .antMatchers("/api/auth/logout/**")
                    .authenticated()
                .and()
                    .authorizeRequests()
                    .antMatchers("/api/work-area/**")
                    .hasAnyRole("MANAGER", "ADMIN")
                .and()
                    .authorizeRequests()
                    .antMatchers("/api/user-area/**")
                    .authenticated()
                .and()
                    .authorizeRequests()
                    .antMatchers( HttpMethod.POST,"/api/auth/login/**" )
                    .permitAll()
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable()
              ;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(provider);
    }

    @Bean
    AuthenticationFilter authenticationFilter() throws Exception {
        var filter = new AuthenticationFilter(PROTECTED_URLS);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }


}
