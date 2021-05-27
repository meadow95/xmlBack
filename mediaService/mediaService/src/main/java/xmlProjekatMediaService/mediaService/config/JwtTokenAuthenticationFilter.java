package xmlProjekatMediaService.mediaService.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;

    public JwtTokenAuthenticationFilter(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // 1. Get zaglavlja za potvrdu identiteta
    	// Tokeni bi trebalo da se proslede u headeru za potvrdu identiteta
        String header = request.getHeader(jwtConfig.getHeader());

        // 2. Validarija headera i potvrda prefix-a
        if(header == null || !header.startsWith(jwtConfig.getPrefix())) {
            chain.doFilter(request, response);  		// ako nije validno prelazak na sledeci filter
            return;
        }

        // Ako nema tokena korisnik nece biti autentifikovan
        // Mozda je pristupio javnog putanji i trazi token

        // Sve zasticene putanje kojima je potreban token vec su definisane u config-u
        // Ako im korisnik pokusa pristupiti, a nije mu potvrdjen identitet bacice izuzetak

        // 3. Get token
        String token = header.replace(jwtConfig.getPrefix(), "");

        try {	

            // 4. Validacija tokena
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret().getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            if(username != null) {
                List<String> authorities = (List<String>) claims.get("authorities");

                // 5. Pravljenje auth objekta
                // UsernamePasswordAuthenticationToken: Ugradjen objekat koji predstavlja trenutnog autentifikovanog korisnika
                
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(username, null,
                                authorities
                                        .stream()
                                        .map(SimpleGrantedAuthority::new)
                                        .collect(toList()));

                // 6. Autentifikacija korisnika
                // Sad je autentifikovan
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            // U slucaju pucanja, garancija da korisnik nece biti autentifikovan
            SecurityContextHolder.clearContext();
        }

        // Idi na sledeci filter
        chain.doFilter(request, response);
    }
}
