package com.crypto.dashboard.contoller;

import com.crypto.dashboard.config.JwtProvider;
import com.crypto.dashboard.model.User;
import com.crypto.dashboard.repository.UserRepository;
import com.crypto.dashboard.response.AuthResponse;
import com.crypto.dashboard.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) {


        User isExists = userRepository.findByEmail(user.getEmail());
        if (isExists != null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setRole(user.getRole());
        newUser.setTwoFactorAuth(user.getTwoFactorAuth());
        User savedUser = userRepository.save(newUser);
        Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = JwtProvider.generateToken(auth);
       AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("User registered successfully");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) {
        String userName = user.getEmail();
        String password = user.getPassword();

        Authentication auth = authenticate(userName, password);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = JwtProvider.generateToken(auth);
        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("User login successful");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    private Authentication authenticate(String userName, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username ");
        }
        if(!password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userName, password);
    }


}
