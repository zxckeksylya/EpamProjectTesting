package validation.impl;

import entity.Quest;
import validation.RegExProvider;
import validation.Validator;

public class QuestValidator extends Validator<Quest> {
    private final static int MIN_TEXT_OF_QUEST_LENGTH=5;
    private final static int MAX_TEXT_OF_QUEST_LENGTH=2000;

    @Override
    public boolean isValid(Quest entity) {
        String textOfQuest = entity.getTextOfQuest();

        if(!validateString(textOfQuest,MIN_TEXT_OF_QUEST_LENGTH,MAX_TEXT_OF_QUEST_LENGTH, RegExProvider.TEXT)){
            setValidationFeedback("неверный формат текста вопроса");
            return false;
        }

        return true;
    }
}
