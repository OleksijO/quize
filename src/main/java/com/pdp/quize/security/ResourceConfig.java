package com.pdp.quize.security;

import com.pdp.quize.constant.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resource.id}")
    private String resourceId;

    // The DefaultTokenServices bean provided at the AuthorizationConfig
    @Autowired
    private ResourceServerTokenServices tokenServices;

    // The TokenStore bean provided at the AuthorizationConfig
    @Autowired
    private TokenStore tokenStore;

    // To allow the rResourceServerConfigurerAdapter to understand the token,
    // it must share the same characteristics with AuthorizationServerConfigurerAdapter.
    // So, we must wire it up the beans in the ResourceServerSecurityConfigurer.
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(resourceId)
                .tokenServices(tokenServices)
                .tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()

                .antMatchers(HttpMethod.GET,"/api/public/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/student/**").hasRole(Role.STUDENT.getAuthority())
                .antMatchers(HttpMethod.POST,"/api/tutor/**").hasRole(Role.TEACHER.getAuthority())

                // restricting all access to /api/** to authenticated users
                .antMatchers("/api/**").authenticated();
    }

}
