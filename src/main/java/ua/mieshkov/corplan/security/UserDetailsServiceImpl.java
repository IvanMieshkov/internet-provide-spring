package ua.mieshkov.corplan.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.mieshkov.corplan.model.User;
import ua.mieshkov.corplan.repository.UserRepository;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.parseLong(login))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return SecurityUser.getUserDetailsFromUser(user) ;
    }
}
