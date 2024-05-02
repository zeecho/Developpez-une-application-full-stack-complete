package com.openclassrooms.mddapi.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.security.services.UserDetailsImpl;

import io.jsonwebtoken.*;

/**
 * Utility class for JWT operations such as generating, validating tokens, and extracting information.
 */
@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${oc.app.jwtSecret}")
	private String jwtSecret;

	@Value("${oc.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	/**
	 * Generates a JWT token from the provided authentication object.
	 * 
	 * @param authentication The authentication object.
	 * @return The generated JWT token.
	 */
	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	/**
	 * Extracts the username from the provided JWT token.
	 * 
	 * @param token The JWT token.
	 * @return The username extracted from the token.
	 */
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * Extracts the email from the provided JWT token.
	 * 
	 * @param token The JWT token.
	 * @return The email extracted from the token.
	 */
	public String getEmailFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("email", String.class);
	}

	/**
	 * Validates the provided JWT token.
	 * 
	 * @param authToken The JWT token to validate.
	 * @return True if the token is valid, false otherwise.
	 */
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
