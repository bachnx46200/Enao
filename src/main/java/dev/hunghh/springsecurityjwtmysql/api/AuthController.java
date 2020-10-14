package dev.hunghh.springsecurityjwtmysql.api;

import dev.hunghh.springsecurityjwtmysql.entity.Token;
import dev.hunghh.springsecurityjwtmysql.entity.User;
import dev.hunghh.springsecurityjwtmysql.security.JwtUtil;
import dev.hunghh.springsecurityjwtmysql.security.UserPrincipal;
import dev.hunghh.springsecurityjwtmysql.service.TokenService;
import dev.hunghh.springsecurityjwtmysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {


    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Object register(@RequestBody User user) {
        UserPrincipal use= userService.findByUsername(user.getUsername());
        if(user.getUsername().equals(use.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tài khoản dax ton tai");
        }else {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return userService.createUser(user);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login( @Valid  @RequestBody User user) {
        UserPrincipal userPrincipal = userService.findByUsername(user.getUsername());
        if (null == user || !new BCryptPasswordEncoder().matches(user.getPassword(), userPrincipal.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tài khoản hoặc mật khẩu không chính xác");
        }
        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));
        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setId_user(userPrincipal.getUserId());
        token.setId_infor(userPrincipal.getId_infor());
        token.setId_role(userPrincipal.getId_role());
        System.out.println(userPrincipal.getUserId());

        tokenService.createToken(token);

        return ResponseEntity.ok(token);

    }

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('USER_CREATE')")
    public ResponseEntity hello() {
        return ResponseEntity.ok("hello");
    }
}
