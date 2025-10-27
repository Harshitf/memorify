package com.moclase.Memorify.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSynchronizer {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public void synchronizeWithIdp(Jwt token) {
        log.info("synchronize user With Idp");
        getUserEmail(token).ifPresent(userEmail -> {
            log.info("synchronize user having Email {}", userEmail);
            Optional<User> optionalUser = userRepository.findByEmail(userEmail);
            User user=userMapper.fromTokenAttributes(token.getClaims());
            if(optionalUser.isPresent()){
                user.setId(optionalUser.get().getId());
            }
            userRepository.save(user);

        });
    }

    private Optional<String > getUserEmail(Jwt token) {
        Map<String,Object> attributes = token.getClaims();
        if (attributes.containsKey("email")) {
            return Optional.ofNullable((String) attributes.get("email").toString());
        }
        return Optional.empty();
    }
}
