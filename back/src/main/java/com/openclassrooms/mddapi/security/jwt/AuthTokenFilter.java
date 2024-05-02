package com.openclassrooms.mddapi.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.openclassrooms.mddapi.security.services.UserDetailsServiceImpl;

/**
 * Filter for validating JWT tokens and setting the authentication in the SecurityContext.
 */
public class AuthTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    /**
     * Intercept the incoming request, validate the JWT token, and set the authentication.
     * @param request The incoming HTTP request.
     * @param response The HTTP response.
     * @param filterChain The filter chain.
     * @throws ServletException If an error occurs during the filter chain processing.
     * @throws IOException If an I/O error occurs during the filter chain processing.
     */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = parseJwt(request);
		if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
			String username = jwtUtils.getUserNameFromJwtToken(jwt);

			try {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (UsernameNotFoundException e) {
				logger.error("username or email not found: {}", e);
			}
		}
		filterChain.doFilter(request, response);
	}

    /**
     * Parses the JWT token from the HTTP request header.
     * @param request The HTTP request.
     * @return The JWT token.
     */
	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7);
		}

		return null;
	}
}
