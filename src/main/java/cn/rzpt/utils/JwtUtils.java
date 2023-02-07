package cn.rzpt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wax
 * @Description 生成token的工具类
 * @since 1.0.0
 */
@Component
@Data
@ConfigurationProperties(prefix = "rzpt.jwts")
public class JwtUtils {
    private long expire;
    private String secret;
    private String header;

    /**
     * 生成token
     * @param username
     * @return
     */
    public String createToken(String username) {
        Date date = new Date(); //当前时间
        Date expireDate = new Date(date.getTime() + 1000 * expire); //过期时间
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(username)
                .setIssuedAt(date)  //设置签发时间
                .setExpiration(expireDate)  //设置过期时间
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 解决token
     * @param jwt
     * @return
     */
    public Claims getClaimByToken(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }
    public boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
