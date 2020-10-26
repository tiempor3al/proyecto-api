package la.kingtide.utils;

import io.smallrye.jwt.build.Jwt;
import la.kingtide.dto.UserDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.HashSet;

@ApplicationScoped
public class TokenUtils {




    private TokenUtils() {
        // no-op: utility class
    }

    public static String generateToken(UserDto userDto, Long duration) throws Exception {

        var rol = new HashSet<>(Collections.singletonList(userDto.getRole()));

        String token = Jwt.issuer("kingtide")
                .claim("name",userDto.getName())
                .groups(rol)
                .expiresAt(currentTimeInSecs() + duration)
                .sign();

        return token;
    }

    public static int currentTimeInSecs() {
        long currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS / 1000);
    }
}