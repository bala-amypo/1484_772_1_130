import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import java.util.Set;
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public AuthResponse register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        String token = jwtTokenProvider.createToken(
                user.getId(),
                user.getEmail(),
                user.getRoles()
        );
        return new AuthResponse(token);
    }

    public AuthResponse login(User user) {
        User u = userRepository.findByEmail(user.getEmail())
                .orElseThrow();
        if (!passwordEncoder.matches(user.getPassword(), u.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return new AuthResponse(
                jwtTokenProvider.createToken(
                        u.getId(),
                        u.getEmail(),
                        u.getRoles()
                )
        );
    }
}