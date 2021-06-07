package command.impl;

import command.CommandException;
import command.EntityBuilder;
import command.PagePath;
import dbbinding.impl.QuestBinding;
import dbbinding.impl.TestBinding;
import entity.Quest;
import entity.Test;
import repository.RepositoryException;
import repository.impl.SqlRepository;
import validation.impl.QuestValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/*
 * команда добавления вопроса
 * */

public class AddQuestCommand implements CommandWithValidation{
    /*
     * обработка формы
     * */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Optional<Quest> optionalQuest = EntityBuilder.INSTANCE.buildQuest(request);
        QuestValidator validator = new QuestValidator();

        if (!optionalQuest.isPresent()) {
            try {
                response.sendError(400);
            } catch (IOException e) {
                throw new CommandException(e);
            }
            return;
        }

        Quest quest = optionalQuest.get();

        if (!validator.isValid(quest)) {
            System.out.println("validatorError");
            onValidationError(request, response, "ошибка добавления вопроса в базу данных");
            return;
        }

        try{
            new SqlRepository<Quest>(new QuestBinding()).addEntity(quest);
        } catch (RepositoryException e) {
            onValidationError(request, response, "Ошибка добавления вопроса в базу данных");
            return;
        }

        onSuccess(request, response);
    }

    @Override
    public String getCommandName() {
        return "add_quest";
    }

    /*
     * возврат к листу тестов при правильном заполнением формы
     * */

    @Override
    public void onSuccess(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String params = "?command=to_tests_list";
            response.sendRedirect(PagePath.BASE_SERVLET+PagePath.SERVLET+params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * возврат к форме добавления вопроса при ошибки заполнения формы
     * */

    @Override
    public void onValidationError(HttpServletRequest request, HttpServletResponse response, String feedback) throws CommandException {
        try {
            request.setAttribute("feedback", feedback);
            request.getRequestDispatcher(PagePath.SERVLET + "?command=to_add_quest").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new CommandException(e);
        }
    }
}
