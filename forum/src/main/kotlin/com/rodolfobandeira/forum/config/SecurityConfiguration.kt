package com.rodolfobandeira.forum.config

import com.rodolfobandeira.forum.security.JWTAuthenticationFilter
import com.rodolfobandeira.forum.security.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration (
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JwtUtil
): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        // Here we can tell the application that /topics requires authentication
        http?.csrf()?.disable()?.authorizeRequests()?.
        antMatchers("/topics")?.hasAuthority("READ_WRITE")?.
        antMatchers(HttpMethod.POST,"/login")?.permitAll()?.
        anyRequest()?.authenticated()?.
        and()
        http?.addFilterBefore(
            JWTLoginFilter(authManager = authenticationManager(), jwtUtil = jwtUtil),
            UsernamePasswordAuthenticationFilter().javaClass
        )
        http?.addFilterBefore(
            JWTAuthenticationFilter(jwtUtil = jwtUtil),
            UsernamePasswordAuthenticationFilter().javaClass
        )
        http?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        // Here is where we check in the database for the credentials
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptPasswordEncoder())
    }
}