package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services;

import lombok.AllArgsConstructor;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.LoginDto;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.UserDto;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.LoginResponse;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.User;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public User register(UserDto userDto) throws IOException {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(List.of("ROLE_ADMIN"));
        return userRepository.save(user);
    }

    public LoginResponse authenticate(LoginDto input) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.email(), input.password()));
        String token= tokenService.generateToken(authentication);
        return new LoginResponse(token);
    }
}
