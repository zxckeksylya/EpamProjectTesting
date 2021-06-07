package command.impl;

import command.CommandException;
import command.EntityBuilder;
import command.PagePath;
import dbbinding.impl.AnswerBinding;
import dbbinding.impl.QuestBinding;
import entity.Answer;
import entity.Quest;
import repository.RepositoryException;
import repository.impl.SqlRepository;
import validation.impl.AnswerValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/*
 * команда добавления ответа
 * */

public class AddAnswerCommand implements CommandWithValidation {
    /*
    * обработка формы
    * */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Optional<Answer> optionalAnswer = EntityBuilder.INSTANCE.buildAnswer(request);
        AnswerValidator validator = new AnswerValidator();

        if (!optionalAnswer.isPresent()) {
            try {
                response.sendError(400);
            } catch (IOException e) {
                throw new CommandException(e);
            }
            return;
        }

        Answer answer = optionalAnswer.get();

        if (!validator.isValid(answer)) {
            onValidationError(request, response, "ошибка добавления ответа в базу данных");
            return;
        }
        try {
            new SqlRepository<Answer>(new AnswerBinding()).addEntity(answer);
        } catch (RepositoryException e) {
            onValidationError(request, response, "Ошибка добавления ответа в базу данных");
            return;
        }
        onSuccess(request, response);
    }


    @Override
    public String getCommandName() {
        return "add_answer";
    }

    /*
    * возврат к листу вопросов при правильном заполнением формы
    * */

    @Override
    public void onSuccess(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String params = "?command=to_quests_list_by_id";
            response.sendRedirect(PagePath.BASE_SERVLET + PagePath.SERVLET + params);
        } catch (IOException e) {
            throw new CommandException(e);
        }
    }

    /*
    * возврат к форме добавления ответа при ошибки заполнения формы
    * */

    @Override
    public void onValidationError(HttpServletRequest request, HttpServletResponse response, String feedback) throws CommandException {
        try {
            request.setAttribute("feedback", feedback);
            request.getRequestDispatcher(PagePath.SERVLET + "?command=to_add_answer").forward(request, response);
        } catch (IOException | ServletException e) {
            throw new CommandException(e);
        }
    }
}

