package com.example.sdelamer.appParaMeli.Configuration.Security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET_KEY = "Uf7GVktCJs5j8sNF/FZ3aQpta0mcLn1eJ4fOK5g3w1sENIXYMbnYZl03yv6jvWkwfdfJuCOLOs0zOx38qALddaZECL6h1af0o83VRa+LL5cb9/CxkfOsbhBv5JuAsOJ919/DZuodYUVht+CSQlm2w4kBZAQC8nxNy8egKSfhyy+7XNjCfaqsqzzMcWk3xsYijgoOpZVChqj9+wDjofRDCiqq5rvdDYJzieCGnnoy9G5pfvPvuvG7kBS1pqWR/QmshzqdFMZ75xzDxBiNgnuv48tzpoRYy3IvNrfZboekJOQlRbasyzlUfPc1CJemLwhJLz+u8mravBBAWQ++gPMunQWem/Qn3CjwfQt0JeJZO5s=";

	public String getUsername(String jwt) {

		return extractClaim(jwt, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);

	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSigningKey() {

		byte[] decode = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(decode);

	}

	public String generateToken(UserDetails user) {
		return generateToken(new HashMap<>(), user);
	}

	public String generateToken(Map<String, Object> extraClaims, UserDetails user) {
		return Jwts.builder().setClaims(extraClaims).setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();

	}

	public boolean isTokenValid(String token, UserDetails appUser) {
		String username = getUsername(token);
		return username.equals(appUser.getUsername()) && !isTokenExpired(token);

	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());

	}

	private Date extractExpiration(String token) {

		return extractClaim(token, Claims::getExpiration);
	}

}
