//package com.shopping.shopping_mall.jwt;
//
//import com.shopping.shopping_mall.domain.Users;
//import com.shopping.shopping_mall.repository.UserRepository;
//import com.shopping.shopping_mall.service.JwtService;
//import com.shopping.shopping_mall.util.PasswordUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
//import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@RequiredArgsConstructor
//@Slf4j
//public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {
//    private static final String NO_CHECK_URL = "/login"; // "/login"으로 들어오는 요청은 Filter 작동 X
//
//    private final JwtService jwtService;
//    private final UserRepository userRepository;
//
//    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if (request.getRequestURI().equals(NO_CHECK_URL)){
//            filterChain.doFilter(request, response);
//            return;
//        }
//        String refreshToken = jwtService.extractRefreshToken(request)
//                .filter(jwtService::isTokenValid)
//                .orElse(null);
//        if (refreshToken != null){
//            checkRefreshTokenAndReIssueAccessToken(response, refreshToken);
//            return;
//        }
//        if (refreshToken == null){
//            checkAccessTokenAndAuthentication(request, response, filterChain);
//        }
//    }
//
//    private void checkAccessTokenAndAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//    throws ServletException, IOException{
//        log.info("checkAccessTokenAndAuthentication() call");
//        jwtService.extractAccessToken(request)
//                .filter(jwtService::isTokenValid)
//                .ifPresent(accessToken -> jwtService.extractEmail(accessToken)
//                        .ifPresent(email -> userRepository.findByEmail(email)
//                                .ifPresent(this::saveAuthentication)));
//
//    }
//
//    private void saveAuthentication(Users users) {
//        String password = users.getPassword();
//        if (password == null) password = PasswordUtil.generateRandomPassword();
//        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
//                .username(users.getEmail())
//                .password(password)
//                .build();
//
//        Authentication authentication =
//                new UsernamePasswordAuthenticationToken(userDetails, null,
//                        authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
//
//    public void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken){
//        userRepository.findByRefreshToken(refreshToken)
//                .ifPresent(user -> {
//                    String reIssuedRefreshToken = reIssuedRefreshToken(user);
//                    jwtService.sendAccessAndRefreshToken(response, jwtService.createAccessToken(user.getEmail()), refreshToken);
//                });
//    }
//
//    private String reIssuedRefreshToken(Users user) {
//        String reIssuedRefreshToken = jwtService.createRefreshToken();
//        user.updateRefreshToken(reIssuedRefreshToken);
//        userRepository.saveAndFlush(user);
//        return reIssuedRefreshToken;
//    }
//}
