package com.github.mvujas.nightmareauctionsbackend.managers.jwt;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * Simplifies building of Json Web Token
 * 
 * @author Milos Vujasinovic
 *
 */
public class JwtTokenBuilder {

	private Algorithm algorithm;
	private Builder jwtBuilder;
	
	JwtTokenBuilder(Algorithm algorithm) {
		if(algorithm == null) {
			throw new NullPointerException("Signing algorithm cannot be null");
		}
		this.algorithm = algorithm;
		
		jwtBuilder = JWT.create();
	}
	
	private Date now() {
		return new Date();
	}
	
	public JwtTokenBuilder claim(String name, String value) {
		jwtBuilder.withClaim(name, value);
		return this;
	}
	
	public JwtTokenBuilder claim(String name, Integer value) {
		jwtBuilder.withClaim(name, value);
		return this;
	}
	
	public JwtTokenBuilder issuedAt(Date date) {
		jwtBuilder.withIssuedAt(new Date());
		return this;
	}
	
	public JwtTokenBuilder issuedNow() {
		return issuedAt(now());
	}
	
	public JwtTokenBuilder expiresAt(Date date) {
		jwtBuilder.withExpiresAt(date);
		return this;
	}
	
	public JwtTokenBuilder period(Date date, long duration, TimeUnit timeUnit) {
		long expirationInMilliseconds = date.getTime() + timeUnit.toMillis(duration);
		Date expirationDate = new Date(expirationInMilliseconds);

		jwtBuilder
			.withIssuedAt(date)
			.withExpiresAt(expirationDate);
		
		return this;
	}
	
	public JwtTokenBuilder periodFromNow(long duration, TimeUnit timeUnit) {
		return period(now(), duration, timeUnit);
	}
	
	public JwtTokenBuilder issuer(String issuer) {
		jwtBuilder.withIssuer(issuer);
		return this;
	}
	
	public JwtTokenBuilder subject(String subject) {
		jwtBuilder.withSubject(subject);
		return this;
	}
	
	public String build() {
		return jwtBuilder.sign(algorithm);
	}

}
