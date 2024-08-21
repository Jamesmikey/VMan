package tz.ac.iact.va.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tz.ac.iact.va.repository.UserRepository;


@Service
@Data
@RequiredArgsConstructor
public class UserDetailServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails u= userRepository.findByEmailOrPhoneNumber(username,username).orElseThrow(() -> new UsernameNotFoundException("Incorrect username or password"));
        return u;
    }


}
