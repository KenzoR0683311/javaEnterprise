package example.com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.com.config.JwtUtil;
import example.com.dto.LoginDTO;

@RestController
@RequestMapping("api/")
class UserRestController {
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  JwtUtil jwtUtil;

  @PostMapping("/auth")
  public ResponseEntity<String> auth(@RequestBody LoginDTO loginDTO) {
    UsernamePasswordAuthenticationToken usat = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
    authenticationManager.authenticate(usat);

    String jwt = jwtUtil.generate(loginDTO.getUsername());
    return ResponseEntity.ok(jwt); 
  }
}

