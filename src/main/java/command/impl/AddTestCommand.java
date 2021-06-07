package command.impl;

import command.CommandException;
import command.EntityBuilder;
import command.PagePath;
import dbbinding.impl.TestBinding;
import entity.Test;
import repository.RepositoryException;
import repository.impl.SqlRepository;
import validation.impl.TestValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/*
 * команда добавления теста
 * */

public class AddTestCommand implements CommandWithValidation {
    /*
     * обработка формы
     * */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Optional<Test> optionalTest = EntityBuilder.INSTANCE.buildTest(request);
        TestValidator validator = new TestValidator();

        if (!optionalTest.isPresent()) {
            try {
                response.sendError(400);
            } catch (IOException e) {
                throw new CommandException(e);
            }
            return;
        }

        Test test = optionalTest.get();

        if (!validator.isValid(test)) {
            onValidationError(request, response, "ошибка добавления теста в базу данных");
            return;
        }
        try{
            new SqlRepository<Test>(new TestBinding()).addEntity(test);
        } catch (RepositoryException e) {
            onValidationError(request, response, "Ошибка добавления теста в базу данных");
            return;
        }

        onSuccess(request, response);
    }

    @Override
    public String getCommandName() {
        return "add_test";
    }

    /*
     * возврат к листу тестов при правильном заполнением формы
     * */
    @Override
    public void onSuccess(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String params = "?command=to_tests_list";
            response.sendRedirect(PagePath.BASE_SERVLET + PagePath.SERVLET + params);

        } catch (IOException e) {
            throw new CommandException(e);
        }
    }

    /*
     * возврат к форме добавления теста при ошибки заполнения формы
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
