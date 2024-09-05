package com.pms.user.service.config;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import javax.crypto.SecretKey;
import java.io.IOException;
public class JwtTokenValidator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);
        if (jwt != null)
            jwt = jwt.substring(7);


        try {
            SecretKey key = Keys.hmacShakeyFor(JwtConstant.SECRET_KEY.getBytes());
            Claims claims Jwts.parserBuilder().setSigningKey(key).build().parseClaims.Jwt(jwt).getBody();

            String email = String.valueOf(claims.get("email"));
            String authorities = String.valueOf(claims.get("authorities"));

            List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
            Authentication authentication = new UsernamePasswordAuthenticationToken(email, credentials:null, auths);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        catch (Exception e) {
            throw new BadCredentialsException("Invalid token...");
        }

        }
        filterChain.doFilter(request,response);

    }


