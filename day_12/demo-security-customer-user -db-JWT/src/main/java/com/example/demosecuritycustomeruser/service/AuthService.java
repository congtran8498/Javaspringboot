package com.example.demosecuritycustomeruser.service;

import com.example.demosecuritycustomeruser.entity.Role;
import com.example.demosecuritycustomeruser.entity.TokenConfirm;
import com.example.demosecuritycustomeruser.entity.User;
import com.example.demosecuritycustomeruser.exception.BadRequestException;
import com.example.demosecuritycustomeruser.exception.NotFoundException;
import com.example.demosecuritycustomeruser.repository.RoleRepository;
import com.example.demosecuritycustomeruser.repository.TokenConfirmRepository;
import com.example.demosecuritycustomeruser.repository.UserRepository;
import com.example.demosecuritycustomeruser.request.CreateUserRequest;
import com.example.demosecuritycustomeruser.request.LoginRequest;
import com.example.demosecuritycustomeruser.security.CustomUserDetailsService;
import com.example.demosecuritycustomeruser.security.JwtUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtUtils jwtUtils;

    public String register(CreateUserRequest request) {
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
        return "http://localhost:8080/api/v1/auth/confirm?token=" + tokenConfirm.getToken();
    }

    public String confirm(String token){
        TokenConfirm tokenConfirm = tokenConfirmRepository.findByToken(token)
                .orElseThrow(() ->  new BadRequestException("khong ton tai token"));
        if(tokenConfirm.getConfirmedAt() != null) throw new BadRequestException("da xac nhan");
        if(tokenConfirm.getExpiredAt().isBefore(LocalDateTime.now())) throw new BadRequestException("het thoi han");

        tokenConfirm.setConfirmedAt(LocalDateTime.now());
        tokenConfirm.getUser().setIsEnabled(true);

        tokenConfirmRepository.save(tokenConfirm);
        userRepository.save(tokenConfirm.getUser());

        return "xac nhan thanh cong";
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

            // Tạo ra token và trả về cho người dùng
            UserDetails userDetails = customUserDetailsService
                    .loadUserByUsername(authentication.getName());

            // Create JWT token and return
            return jwtUtils.generateToken(userDetails);

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
