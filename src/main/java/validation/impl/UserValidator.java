package validation.impl;

import entity.User;
import validation.RegExProvider;
import validation.Validator;

public class UserValidator extends Validator<User> {
    private final static int MIN_LOGIN_LENGTH = 5;
    private final static int MAX_LOGIN_LENGTH = 50;
    private final static int MIN_PASSWORD_LENGTH = 4;
    private final static int MAX_PASSWORD_LENGTH = 50;

    @Override
    public boolean isValid(User entity) {
        String login = entity.getLogin();
        String password = entity.getPassword();

        if(!validateString(login,MIN_LOGIN_LENGTH,MAX_LOGIN_LENGTH, RegExProvider.LATIN_LETTERS_WITH_NUMBERS_AND_SYMBOLS)){
            setValidationFeedback("Неверный формат логина");
            return false;
        }

        if(!validateString(password,MIN_PASSWORD_LENGTH,MAX_PASSWORD_LENGTH,RegExProvider.LATIN_LETTERS_WITH_NUMBERS_AND_SYMBOLS)){
            setValidationFeedback("Неверный формат пороля");
            return false;
        }
        return true;
    }
}
