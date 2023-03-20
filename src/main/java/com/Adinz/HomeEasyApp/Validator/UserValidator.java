package com.Adinz.HomeEasyApp.Validator;

import com.Adinz.HomeEasyApp.Model.User;
import com.Adinz.HomeEasyApp.PalyLoad.RegisterRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.executable.ExecutableValidator;
import jakarta.validation.metadata.BeanDescriptor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

@Component
public class UserValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterRequest user = (RegisterRequest) target;
        if(user.getPassword().length()<6){
            errors.rejectValue("password","Length", "Password must be at least 6 characters");
        }

    }
}
