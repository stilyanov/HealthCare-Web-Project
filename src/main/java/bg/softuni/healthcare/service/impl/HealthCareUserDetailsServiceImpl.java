package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.entity.User.HealthcareUserDetails;
import bg.softuni.healthcare.model.entity.enums.UserRoleEnum;
import bg.softuni.healthcare.repository.UserRepository;
import bg.softuni.healthcare.service.HealthCareUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HealthCareUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public HealthCareUserDetailsServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository
                .findByEmail(email)
                .map(userEntity -> modelMapper.map(userEntity, HealthcareUserDetails.class))
                .orElseThrow(() -> new UsernameNotFoundException("UserEntity not found with email: " + email));
    }

    public GrantedAuthority map(UserRoleEnum role) {
        return null;
    }
}
