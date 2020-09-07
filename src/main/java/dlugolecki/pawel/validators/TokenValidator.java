package dlugolecki.pawel.validators;

import dlugolecki.pawel.dto.TokenDto;
import dlugolecki.pawel.exceptions.MyException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TokenValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(TokenDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        try {
            if (o == null) {
                throw new NullPointerException("OBJECT IS NULL");
            }
            TokenDto tokenDto = (TokenDto) o;
            String tokenValue = tokenDto.getTokenValue();
            if (tokenValue == null || tokenValue.equals("") || tokenValue.length() < 1) {
                errors.rejectValue("tokenKey", "TOKEN IS EMPTY");
            }
        } catch (Exception e) {
            throw new MyException(
                    "Token_value is empty",
                    TokenValidator.class.getCanonicalName(),
                    "Validate");
        }
    }
}