package org.example.springsecuritytemplate.Controller;


import org.example.springsecuritytemplate.Dto.User.UserCreateDTO;
import org.example.springsecuritytemplate.Model.ApiResponse;
import org.example.springsecuritytemplate.Service.AuthenticationService;
import org.example.springsecuritytemplate.Service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springsecuritytemplate.Dto.Auth.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @GetMapping("/checkId/{id}")
    public ApiResponse<IdCheck> checkId(@PathVariable String id, Authentication authentication) {
        return new ApiResponse<>(new IdCheck(this.authenticationService.isIdOfSelf(UUID.fromString(id), authentication)), HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ApiResponse<?> login(@Valid @RequestBody AuthRequestDTO loginDTO, HttpServletResponse response) {
        authenticationService.login(loginDTO.getUsername(), loginDTO.getPassword(), response);
        return new ApiResponse<>("Login succes", HttpStatus.OK);
    }

    // Voor testing doeleinden
    @PostMapping(value = "/register")
    public ApiResponse<AuthResponseDTO> register(@RequestBody UserCreateDTO userCreateDTO) {
        Optional<String> tokenResponse = authenticationService.register(
                userCreateDTO.getUsername(),
                userCreateDTO.getPassword()

        );

        if (tokenResponse.isEmpty()) {
            return new ApiResponse<>("User already exists", HttpStatus.BAD_REQUEST);
        }

        String token = tokenResponse.get();
        return new ApiResponse<>(new AuthResponseDTO(token));
    }

    @GetMapping(value = "/authenticated")
    public ApiResponse<AuthCheckResponseDTO> checkAuthenticated(HttpServletRequest request, HttpServletResponse response) {
        AuthCheckResponseDTO authCheckResponseDTO = this.authenticationService.checkAuthenticated(request);

        if (!authCheckResponseDTO.isAuthenticated()) {
            // Clears the cookies in case the browser still has them stored whilst they're invalid
            Cookie cookie = this.authenticationService.getEmptyCookie("token");
            response.addCookie(cookie);
        }

        return new ApiResponse<>(authCheckResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        response.addCookie(this.authenticationService.logout());
    }

    @GetMapping("/isAdmin")
    public ApiResponse<AdminCheckResponseDTO> isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
        return new ApiResponse<>(new AdminCheckResponseDTO(isAdmin), HttpStatus.OK);
    }


}
