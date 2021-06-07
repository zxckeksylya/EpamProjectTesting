package validation.impl;

import entity.Role;
import validation.RegExProvider;
import validation.Validator;

public class RoleValidator extends Validator<Role> {
    private final static int MIN_NAME_OF_ROLE_LENGTH=3;
    private final static int MAX_NAME_OF_ROLE_LENGTH=20;
    @Override
    public boolean isValid(Role entity) {
        String nameOfSubject=entity.getName();
        if(!validateString(nameOfSubject,MIN_NAME_OF_ROLE_LENGTH,MAX_NAME_OF_ROLE_LENGTH, RegExProvider.TEXT)){
            setValidationFeedback("неверный формат имени предмета");
        }
        return true;

    }
}
