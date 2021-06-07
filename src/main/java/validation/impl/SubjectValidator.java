package validation.impl;

import entity.Subject;
import validation.RegExProvider;
import validation.Validator;

public class SubjectValidator extends Validator<Subject> {
    private final static int MIN_NAME_OF_SUBJECT_LENGTH=3;
    private final static int MAX_NAME_OF_SUBJECT_LENGTH=100;

    @Override
    public boolean isValid(Subject entity) {
        String nameOfSubject=entity.getNameOfSubject();
        if(!validateString(nameOfSubject,MIN_NAME_OF_SUBJECT_LENGTH,MAX_NAME_OF_SUBJECT_LENGTH, RegExProvider.TEXT)){
            setValidationFeedback("неверный формат имени предмета");
        }
        return true;
    }
}
