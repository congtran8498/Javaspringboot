package com.example.fastfood.service;

import com.example.fastfood.entity.Role;
import com.example.fastfood.entity.Token;
import com.example.fastfood.entity.User;
import com.example.fastfood.exception.BadRequestException;
import com.example.fastfood.exception.NotFoundException;
import com.example.fastfood.repository.RoleRepository;
import com.example.fastfood.repository.TokenRepository;
import com.example.fastfood.repository.UserRepository;
import com.example.fastfood.request.CreateUserRequest;
import com.example.fastfood.request.ForgotPasswordRequest;
import com.example.fastfood.request.LoginUserRequest;
import com.example.fastfood.request.RecoverPasswordRequest;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmailService emailService;

    public String register(CreateUserRequest request) throws MessagingException {
        if(userRepository.existsByEmail(request.getEmail())) throw new BadRequestException("Đã tồn tại email này, vui lòng thử lại!");

        // get role user
        Role roleUser = roleRepository.findByName("USER")
                .orElseThrow(() -> new NotFoundException("Không tìm thấy role"));

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        user.setIsEnabled(false);
        user.setRoleList(List.of(roleUser));
        user.setStatus(User.Status.ACTIVE);
        userRepository.save(user);

        Token tokenConfirm = new Token();
        UUID uuid = UUID.randomUUID();
        tokenConfirm.setToken(uuid.toString());
        tokenConfirm.setUser(user);
        tokenConfirm.setType("Register");
        tokenConfirm.setCreatedAt(LocalDateTime.now());
        tokenConfirm.setExpiredAt(LocalDateTime.now().plusMinutes(20));
        tokenRepository.save(tokenConfirm);

        String url = "http://localhost:8080/register/confirm?token=" + tokenConfirm.getToken();
        emailService.sendVerificationEmail(user,url);
        return "Kiểm tra email của bạn để kích hoạt tài khoản";
    }

    public String forgotPassWordToken(ForgotPasswordRequest request) throws MessagingException {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Email không tồn tại"));

        Token tokenConfirm = new Token();
        UUID uuid = UUID.randomUUID();
        tokenConfirm.setToken(uuid.toString());
        tokenConfirm.setUser(user);
        tokenConfirm.setType("Forgot");
        tokenConfirm.setCreatedAt(LocalDateTime.now());
        tokenConfirm.setExpiredAt(LocalDateTime.now().plusMinutes(20));
        tokenRepository.save(tokenConfirm);
        String url =  "http://localhost:8080/forgot-password/confirm?token=" + tokenConfirm.getToken();
        emailService.sendVerificationEmail(user, url);
        return "Kiểm tra email của bạn";
    }


    // isValid: true/false   message: thong bao
    public Map<String, Object> confirm(String token){
        Map<String, Object> data = new HashMap<>();

        Optional<Token> tokenConfirmOptional = tokenRepository.findByToken(token);
        if(tokenConfirmOptional.isEmpty()){
            data.put("isValid", false);
            data.put("message", "Token không tồn tại");
            return data;
        }
        Token tokenConfirm = tokenConfirmOptional.get();

        if(tokenConfirm.getConfirmedAt() != null){
            data.put("isValid", false);
            data.put("message", "Token đã xác nhận");
            return data;
        }

        if(tokenConfirm.getExpiredAt().isBefore(LocalDateTime.now())){
            data.put("isValid", false);
            data.put("message", "Token đã hết hạn");
            return data;
        }

        tokenConfirm.setConfirmedAt(LocalDateTime.now());
        tokenConfirm.getUser().setIsEnabled(true);

        tokenRepository.save(tokenConfirm);
        userRepository.save(tokenConfirm.getUser());

        data.put("isValid", true);
        data.put("message", "Xác thực thành công");
        return data;
    }

    public String login(LoginUserRequest request) {
        // Tạo đối tượng xác thực
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );

        try {
            // Tiến hành xác thực
            Authentication authentication = authenticationManager.authenticate(token);

            // Lưu đối tượng đã xác thực vào trong SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Lưu vào trong session
            httpSession.setAttribute("MY_SESSION", authentication.getName());

            return "Đăng nhập thành công";
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    public String updatePassword(RecoverPasswordRequest request) {
        Token tokenConfirm = tokenRepository.findByToken(request.getToken())
                .orElseThrow(()-> new BadRequestException("Không tìm thấy token"));
        User user = tokenConfirm.getUser();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return "Cập nhật mật khẩu thành công";
    }
}
