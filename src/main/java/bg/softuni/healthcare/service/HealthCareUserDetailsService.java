package bg.softuni.healthcare.service;

import bg.softuni.healthcare.model.entity.enums.UserRoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface HealthCareUserDetailsService {

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

}
