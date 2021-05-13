package xmlProjectSpringbootstarter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import xmlProjectSpringbootstarter.authentication.JwtAuthenticationEntryPoint;
import xmlProjectSpringbootstarter.authentication.JwtAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Resource(name = "korisnikService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/token/*", "/").permitAll()
                .antMatchers(HttpMethod.GET, "/dodatneusluge","/kategorija","/tipsmestaja","/smestaj/*").permitAll()
                .antMatchers(HttpMethod.GET, "/dodatneusluge/*","/kategorija/*","/tipsmestaja/*","/korisnici/*","/korisnici-list/*","/komentari/*").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST, "/komentari/*").hasAnyAuthority()
                .antMatchers(HttpMethod.POST, "/dodatneusluge/*","/kategorija/*","/tipsmestaja/*","/korisnici/*","/korisnici-list/*").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PUT, "/dodatneusluge/*","/kategorija/*","/tipsmestaja/*","/korisnici/*","/korisnici-list/*","/komentari/*").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE, "/dodatneusluge/*","/kategorija/*","/tipsmestaja/*","/korisnici/*","/korisnici-list/*","/komentari/*").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/smestaj/search","/signup","/user/*/","/smestaj","/korisnik/*/", "/poruka","/soap-api/*","/soapWs/*");
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
