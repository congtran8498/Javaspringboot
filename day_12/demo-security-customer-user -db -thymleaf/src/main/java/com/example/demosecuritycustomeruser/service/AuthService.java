package com.example.demosecuritycustomeruser.service;

import com.example.demosecuritycustomeruser.entity.Role;
import com.example.demosecuritycustomeruser.entity.TokenConfirm;
import com.example.demosecuritycustomeruser.entity.User;
import com.example.demosecuritycustomeruser.exception.BadRequestException;
import com.example.demosecuritycustomeruser.exception.NotFoundException;
import com.example.demosecuritycustomeruser.repository.RoleRepository;
import com.example.demosecuritycustomeruser.repository.TokenConfirmRepository;
import com.example.demosecuritycustomeruser.repository.UserRepository;
import com.example.demosecuritycustomeruser.request.ChangePassword;
import com.example.demosecuritycustomeruser.request.CreateUserRequest;
import com.example.demosecuritycustomeruser.request.ForgotPasswordRequest;
import com.example.demosecuritycustomeruser.request.LoginRequest;
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
    private TokenConfirmRepository tokenConfirmRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmailService emailService;

    public String register(CreateUserRequest request) throws MessagingException {
        if(userRepository.existsByEmail(request.getEmail())) throw new BadRequestException("da ton tai email nay");

        // get role user
        Role roleUser = roleRepository.findByName("USER")
                .orElseThrow(() -> new NotFoundException("Không tìm thấy role"));

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setIsEnabled(false);
        user.setRoleList(List.of(roleUser));
        userRepository.save(user);

        TokenConfirm tokenConfirm = new TokenConfirm();
        UUID uuid = UUID.randomUUID();
        tokenConfirm.setToken(uuid.toString());
        tokenConfirm.setUser(user);
        tokenConfirm.setCreatedAt(LocalDateTime.now());
        tokenConfirm.setExpiredAt(LocalDateTime.now().plusMinutes(20));
        tokenConfirmRepository.save(tokenConfirm);

        String url = "http://localhost:8080/confirm?token=" + tokenConfirm.getToken();
        emailService.sendVerificationEmail(user,url);
        return "kiem tra email cua ban de kich hoat tai khoan";
    }

    public String forgotPassWordToken(ForgotPasswordRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("email khong ton tai"));

        TokenConfirm tokenConfirm = new TokenConfirm();
        UUID uuid = UUID.randomUUID();
        tokenConfirm.setToken(uuid.toString());
        tokenConfirm.setUser(user);
        tokenConfirm.setCreatedAt(LocalDateTime.now());
        tokenConfirm.setExpiredAt(LocalDateTime.now().plusMinutes(20));
        tokenConfirmRepository.save(tokenConfirm);
        return "http://localhost:8080/forgot-password/confirm?token=" + tokenConfirm.getToken();
    }

//    public String changePassword(ChangePassword request){
//
//    }

    // isValid: true/false   message: thong bao
    public Map<String, Object> confirm(String token){
        Map<String, Object> data = new HashMap<>();

        Optional<TokenConfirm> tokenConfirmOptional = tokenConfirmRepository.findByToken(token);
        if(tokenConfirmOptional.isEmpty()){
            data.put("isValid", false);
            data.put("message", "Token khong ton tai");
            return data;
        }
        TokenConfirm tokenConfirm = tokenConfirmOptional.get();

        if(tokenConfirm.getConfirmedAt() != null){
            data.put("isValid", false);
            data.put("message", "Token da xac nhan");
            return data;
        }

        if(tokenConfirm.getExpiredAt().isBefore(LocalDateTime.now())){
            data.put("isValid", false);
            data.put("message", "Token da het han");
            return data;
        }

        tokenConfirm.setConfirmedAt(LocalDateTime.now());
        tokenConfirm.getUser().setIsEnabled(true);

        tokenConfirmRepository.save(tokenConfirm);
        userRepository.save(tokenConfirm.getUser());

        data.put("isValid", true);
        data.put("message", "Xac thuc thanh cong");
        return data;
    }

    public String login(LoginRequest request) {
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

            return "dang nhap thanh cong";
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
