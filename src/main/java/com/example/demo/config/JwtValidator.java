package com.example.demo.config;

import org.springframework.stereotype.Component;

import com.example.demo.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {

	private String secret = "123456789";
	
	public User validate(String token) {
		User user = null;
		try {
			
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			user = new User();
			user.setId(Long.parseLong((String) body.get("userId")));
			user.setUserName(body.getSubject());
			user.setRole((String) body.get("role"));
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}
	
}
