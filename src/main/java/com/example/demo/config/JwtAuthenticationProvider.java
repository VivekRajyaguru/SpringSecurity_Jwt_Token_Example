package com.example.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.model.AuthenticationToken;
import com.example.demo.model.User;
import com.example.demo.model.UserDetail;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	 @Autowired
	 private JwtValidator validator;

	
	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0, UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
		
	}

	@Override
	protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken userToken)
			throws AuthenticationException {
		
		AuthenticationToken authToken = (AuthenticationToken) userToken; 
		
		String token = authToken.getToken();

		User user = validator.validate(token);
		
		if (user == null) {
            throw new RuntimeException("JWT Token is incorrect");
        }
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());
        
		return new UserDetail(user.getUserName(), user.getId(),token,grantedAuthorities);
	}
	
	@Override
    public boolean supports(Class<?> aClass) {
        return (AuthenticationToken.class.isAssignableFrom(aClass));
    }

}
