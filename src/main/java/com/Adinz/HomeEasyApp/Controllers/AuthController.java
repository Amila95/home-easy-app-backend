package com.Adinz.HomeEasyApp.Controllers;

import com.Adinz.HomeEasyApp.PalyLoad.AuthenticationRequest;
import com.Adinz.HomeEasyApp.PalyLoad.AuthenticationResponse;
import com.Adinz.HomeEasyApp.PalyLoad.RegisterRequest;
import com.Adinz.HomeEasyApp.Services.AuthenticationService;
import com.Adinz.HomeEasyApp.Services.MapValidationErrorService;
import com.Adinz.HomeEasyApp.Validator.UserValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    @Autowired
    private final MapValidationErrorService mapValidationErrorService;
    @Autowired
    private UserValidator userValidator;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request,  BindingResult result){
        userValidator.validate(request,result);
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null) return errorMap;
        return ResponseEntity.ok(authenticationService.register(request));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest request,  BindingResult result){
       // userValidator.validate(request,result);
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        return ResponseEntity.ok(authenticationService.login(request));

        //authenticationService.login(request);
       // return ResponseEntity.ok(authenticationService.login(request));
    }
}
