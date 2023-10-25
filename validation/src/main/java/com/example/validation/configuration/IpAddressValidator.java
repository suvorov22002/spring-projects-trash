package com.example.validation.configuration;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpAddressValidator implements ConstraintValidator<IpAddress, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        Pattern pattern = Pattern.compile("^([1-9]{1,3})//.([1-9]{1,3})//.([1-9]{1,3})//.([1-9]{1,3})$");
        Matcher matcher = pattern.matcher(value);

        try{
            if(!matcher.matches()) {
                return false;
            }
            else{
                for(int i=0; i<4; i++) {
                    int octet = Integer.valueOf(matcher.group(i));
                    if(octet > 255) {
                        return false;
                    }
                }

                return true;
            }
        }
        catch (Exception e) {
            return false;
        }

    }
}
