package edu.njnu.qaserver.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
	private static final long EXPIRE_DATE = 1000 * 60 * 60 * 24;

	private static final String PRIVATE_KEY = "U2FsdGVkX184C7cKA2uAGsDn9vrpRTpbvqyUvoZ1OJ4=";

	public static String sign(String userId, String userName) {
		try {
			Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
			Algorithm algorithm = Algorithm.HMAC256(PRIVATE_KEY);

			Map<String, Object> header = new HashMap<String, Object>();
			header.put("typ", "JWT");
			header.put("alg", "HS256");
			return JWT.create()
					.withHeader(header)
					.withClaim("userId", userId)
					.withClaim("userName", userName)
					.sign(algorithm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean verify(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(PRIVATE_KEY);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify(token);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
