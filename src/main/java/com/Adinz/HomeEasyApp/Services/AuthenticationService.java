package com.Adinz.HomeEasyApp.Services;

import com.Adinz.HomeEasyApp.Enum.Role;
import com.Adinz.HomeEasyApp.Model.User;
import com.Adinz.HomeEasyApp.PalyLoad.AuthenticationRequest;
import com.Adinz.HomeEasyApp.PalyLoad.AuthenticationResponse;
import com.Adinz.HomeEasyApp.PalyLoad.RegisterRequest;
import com.Adinz.HomeEasyApp.Repositories.UserRepository;
import com.Adinz.HomeEasyApp.exceptions.UsernameAlreadyExistsException;
import com.Adinz.HomeEasyApp.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.Adinz.HomeEasyApp.security.SecurityConstants.TOKEN_PREFIX;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        Optional<User> userAE= userRepository.findByEmail(request.getEmail());
        if(userAE.isPresent()){
            throw new UsernameAlreadyExistsException("Username " + request.getEmail()+ " already exists");

        }

        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }


    public AuthenticationResponse login(AuthenticationRequest request){
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
