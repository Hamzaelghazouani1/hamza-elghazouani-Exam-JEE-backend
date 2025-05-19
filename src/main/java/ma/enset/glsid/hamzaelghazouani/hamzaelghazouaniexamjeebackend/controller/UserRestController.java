package ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.LoginDto;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.dtos.UserDto;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.LoginResponse;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.entities.User;
import ma.enset.glsid.hamzaelghazouani.hamzaelghazouaniexamjeebackend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@Tag(name = "User Management", description = "APIs for user authentication and registration")
@CrossOrigin(origins = "*")
public class UserRestController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) throws IOException {
        UserDto registerUserDto = new UserDto(name, email, password);
        User registeredUser = userService.register(registerUserDto);
        if (registeredUser == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(registeredUser);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginDto loginUserDto) {
        var loginResponse = userService.authenticate(loginUserDto);
        return ResponseEntity.ok(loginResponse);
    }
}
