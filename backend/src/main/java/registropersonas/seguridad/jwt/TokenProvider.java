package registropersonas.seguridad.jwt;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import registropersonas.modelo.Usuario;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static registropersonas.utils.Constants.*;

public class TokenProvider {
    public static String generateToken(Usuario usuario) {
        // Genera el token con roles, issuer, fecha, expiración (8h)

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList("ROLE_" + usuario.getRol());

        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .claim(AUTHORITIES_KEY, grantedAuthorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setIssuer(ISSUER_TOKEN)
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(final String token,
                                                                        final UserDetails userDetails) {
        final JwtParser jwtParser = Jwts.parser().setSigningKey(SIGNING_KEY);
        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        final Claims claims = claimsJws.getBody();
        final Collection<SimpleGrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    public static String getUserName(final String token) {
        final JwtParser jwtParser = Jwts.parser().setSigningKey(SIGNING_KEY);

        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

        return claimsJws.getBody().getSubject();
    }
}
