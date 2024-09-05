package com.pms.user.service.config;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.HashSet;

public class JwtProvider {

    SecretKey key = Keys.hmacShakeyFor(JwtConstant.SECRET_KEY.getBytes());

public static String generateToken (Authentication auth){
    Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

    String roles=populateAuhtorities(authorities);

    String jwt= Jwts.builder()
            .setIssuedAt(new Date())
    .setExpiration(new Date(new Date().getTime()+86400000))
    .claim(name:"email",auth.getName())
    .claim(name:"authorities",roles)
    .signWith(key)
            .compact();

    return jwt;

}
    public static String getEmailFromJwtToken(String jwt){
    jwt=jwt.substring(7);

        Claims claims Jwts.parserBuilder().setSigningKey(key).build().parseClaims.Jwt(jwt).getBody();
        String email = String.valueOf(claims.get("email"));
        return email;
    }

    public static String populateAuhtorities(Collection<? extends GrantedAuthority> collection){
    Set<String> auths=new HashSet<>();

    for(GrantedAuthority authority:collection){
        auths.add(authority.getAuthority());
    }
    return String.join(delimiter: "," ,auths);

    }
}
