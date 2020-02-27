//package com.superwork.beoneplatform.utils;
//
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import javax.crypto.spec.SecretKeySpec;
//import javax.xml.bind.DatatypeConverter;
//import java.awt.*;
//import java.util.Date;
//
///**
// * @program: code->TokenUtils
// * @description: token工具类
// * @author: xjj
// * @create: 2019-11-28 11:28
// **/
//public class TokenUtils {
//
//    /**
//     * 签名秘钥
//     */
//    public static final String SECRET = "token";
//
//    /**
//     * 生成token
//     * @param id 一般传入userName
//     * @return
//     */
//    public static String createJwtToken(String id){
//        String issuer = "";
//        String subject = "";
//        long ttlMillis = 30*60*1000;
//        return createJwtToken(id, issuer, subject, ttlMillis);
//    }
//
//    /**
//     * 生成Token
//     *
//     * @param id
//     *            编号
//     * @param issuer
//     *            该JWT的签发者，是否使用是可选的
//     * @param subject
//     *            该JWT所面向的用户，是否使用是可选的；
//     * @param ttlMillis
//     *            签发时间
//     * @return token String
//     */
//    public static String createJwtToken(String id, String issuer, String subject, long ttlMillis) {
//
//        // 签名算法 ，将对token进行签名
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//
//        // 生成签发时间
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//
//        // 通过秘钥签名JWT
//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
//        SecretKeySpec secretKeySpec = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
//
//        // Let's set the JWT Claims
//        JwtBuilder builder = Jwts.builder().setId(id)
//                .setIssuedAt(now)
//                .setSubject(subject)
//                .setIssuer(issuer)
//                .signWith(signatureAlgorithm, secretKeySpec);
//
//        // if it has been specified, let's add the expiration
//        if (ttlMillis >= 0) {
//            long expMillis = nowMillis + ttlMillis;
//            Date exp = new Date(expMillis);
//            builder.setExpiration(exp);
//        }
//
//        // Builds the JWT and serializes it to a compact, URL-safe string
//        return builder.compact();
//
//    }
//
//    // Sample method to validate and read the JWT
//    public static Claims parseJWT(String jwt) {
//        // This line will throw an exception if it is not a signed JWS (as expected)
//        Claims claims = Jwts.parser()
//                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
//                .parseClaimsJws(jwt).getBody();
//        return claims;
//    }
//    public static String getUserIdByToken(String jwt){
//        Claims claims = parseJWT(jwt);
//        System.out.println("这是"+claims.getId());
//        String subject = claims.get("jti").toString();
//        return subject;
//    }
//
//    public static void main(String[] args) {
//        System.out.println(TokenUtils.createJwtToken("admin"));
//        System.out.println(TokenUtils.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhZG1pbiIsImlhdCI6MTU3NDkxMjE4NSwic3ViIjoiIiwiaXNzIjoiIiwiZXhwIjoxNTc0OTEzOTg1fQ.jcPN02YyY1YXRFN1COhGIrVes2fXSv6-1gCqItyjDSk"));
//        System.out.println(TokenUtils.getUserIdByToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhZG1pbiIsImlhdCI6MTU3NDkxMjIxNSwic3ViIjoiIiwiaXNzIjoiIiwiZXhwIjoxNTc0OTE0MDE1fQ.BwXb4eHCWVA1CsFyoWKgMLRNNQQn2h7HzH7Woraooo0"));
//    }
//}
