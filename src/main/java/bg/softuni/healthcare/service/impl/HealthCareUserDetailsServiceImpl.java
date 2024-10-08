package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.entity.User.HealthcareUserDetails;
import bg.softuni.healthcare.model.entity.UserEntity;
import bg.softuni.healthcare.model.entity.UserRoleEntity;
import bg.softuni.healthcare.model.entity.enums.UserRoleEnum;
import bg.softuni.healthcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthCareUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository
                .findByEmail(email)
                .map(HealthCareUserDetailsServiceImpl::map)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    private static UserDetails map(UserEntity user) {
        return new HealthcareUserDetails(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(UserRoleEntity::getRole).map(HealthCareUserDetailsServiceImpl::map).toList(),
                user.getFirstName(),
                user.getLastName());
    }

    public static GrantedAuthority map(UserRoleEnum role) {
        return new SimpleGrantedAuthority("ROLE_" + role.name());
    }
}
