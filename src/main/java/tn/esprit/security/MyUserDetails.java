package tn.esprit.security;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import tn.esprit.entities.Role;
import tn.esprit.entities.User;

public class MyUserDetails implements UserDetails{

	private User user;
public MyUserDetails(User user){
	this.user=user;
}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
Set<Role> roles=user.getRoles();
List<SimpleGrantedAuthority> autorities = new ArrayList<>();
for(Role role:roles){
	autorities.add(new SimpleGrantedAuthority(role.getName()));
}
return autorities;
}

@Override
public String getPassword() {
	// TODO Auto-generated method stub
	return user.getPassword();
}

@Override
public String getUsername() {
	// TODO Auto-generated method stub
	return user.getUserName();
}

@Override
public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isEnabled() {
	// TODO Auto-generated method stub
	return user.isEnabled();
}

}
