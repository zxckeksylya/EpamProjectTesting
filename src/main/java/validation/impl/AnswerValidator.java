package validation.impl;

import entity.Answer;
import validation.RegExProvider;
import validation.Validator;

public class AnswerValidator extends Validator<Answer> {

    private final static int MIN_TEXT_OF_ANSWER_LENGTH=3;
    private final static int MAX_TEXT_OF_ANSWER_LENGTH=100;



    @Override
    public boolean isValid(Answer entity) {
        String textOfAnswer = entity.getTextOfAnswer();

        if(!validateString(textOfAnswer,MIN_TEXT_OF_ANSWER_LENGTH,MAX_TEXT_OF_ANSWER_LENGTH, RegExProvider.TEXT)){
            setValidationFeedback("неверный формат текста ответа");
            return false;
        }

        return true;
    }
}
