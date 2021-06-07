package validation.impl;

import entity.Test;
import validation.RegExProvider;
import validation.Validator;

public class TestValidator extends Validator<Test> {

    private final static int MIN_NAME_OF_TEST_LENGTH=4;
    private final static int MAX_NAME_OF_TEST_LENGTH=100;

    @Override
    public boolean isValid(Test entity) {
        String nameOfTest=entity.getNameOfTest();

        if(!validateString(nameOfTest,MIN_NAME_OF_TEST_LENGTH,MAX_NAME_OF_TEST_LENGTH, RegExProvider.TEXT)){
            setValidationFeedback("неверный формат имени теста");
            return false;
        }

        return true;
    }
}
