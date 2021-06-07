package command;

import entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/*
* билдер сущностей из базы данных
* */

public enum EntityBuilder {
    INSTANCE;
    public Optional<User> buildUser(HttpServletRequest request){
        User user = new User();
        user.setLogin(request.getParameter(ParameterName.LOGIN));
        user.setPassword(request.getParameter(ParameterName.PASSWORD));
        //user.setRoleID(Integer.parseInt(request.getParameter(ParameterName.ROLE_ID)));

        return Optional.of(user);
    }

    public Optional<Answer> buildAnswer(HttpServletRequest request){
        Answer answer = new Answer();
        answer.setTextOfAnswer(request.getParameter(ParameterName.TEXT_OF_ANSWER));
        answer.setAnswerIsTrue(request.getParameter(ParameterName.ANSWER_IS_TRUE));
        answer.setQuestID(Integer.parseInt(request.getParameter(ParameterName.QUEST_ID)));

        return Optional.of(answer);
    }
    public Optional<Quest> buildQuest(HttpServletRequest request){
        Quest quest = new Quest();
        quest.setTextOfQuest(request.getParameter(ParameterName.TEXT_OF_QUEST));
        quest.setTestID(Integer.parseInt(request.getParameter(ParameterName.TEST_ID)));

        return Optional.of(quest);
    }
    public Optional<Subject> buildSubject(HttpServletRequest request){
        Subject subject = new Subject();
        subject.setNameOfSubject(request.getParameter(ParameterName.NAME_OF_SUBJECT));
        subject.setTestID(Integer.parseInt(request.getParameter(ParameterName.TEST_ID)));

        return Optional.of(subject);
    }
    public Optional<Test> buildTest(HttpServletRequest request){
        Test test = new Test();
        test.setNameOfTest(request.getParameter(ParameterName.NAME_OF_TEST));
        return Optional.of(test);
    }
    public Optional<Testing> buildTesting(HttpServletRequest request){
        Testing testing = new Testing();
        testing.setTestID(Integer.parseInt(request.getParameter(ParameterName.TEST_ID)));
        testing.setUserID(Integer.parseInt(request.getParameter(ParameterName.USER_ID)));
        testing.setNumberOfTrueQuests(Integer.parseInt(request.getParameter(ParameterName.NUMBER_OF_TRUE_QUESTS)));

        return Optional.of(testing);
    }
    public Optional<Role> buildRole(HttpServletRequest request){
        Role role = new Role();
        role.setName(request.getParameter(ParameterName.NAME_ROLE));
        return Optional.of(role);
    }

}
