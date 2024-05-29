package com.nishchith.productservice.services;

import com.nishchith.productservice.dtos.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenService {

    private final RestTemplate restTemplate;

    public TokenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean validateToken(final String token) {

        UserDto userDto;
        try {
            userDto = restTemplate.getForObject("http://localhost:9000/user/validate/" + token,
                    UserDto.class);
        } catch (Exception e) {
            return false;
        }

        return userDto != null;
    }
}
