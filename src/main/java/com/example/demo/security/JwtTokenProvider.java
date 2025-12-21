@Component
public class JwtTokenProvider {

    public String createToken(Long userId, String email, Set<?> roles) {
        return "token-" + userId;
    }

    public String getEmail(String token) {
        return "test@example.com";
    }

    public Set<String> getRoles(String token) {
        return Set.of("USER");
    }

    public Long getUserId(String token) {
        return 1L;
    }
}