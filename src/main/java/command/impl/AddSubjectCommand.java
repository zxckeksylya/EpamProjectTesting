package command.impl;

import command.CommandException;
import command.EntityBuilder;
import command.PagePath;
import entity.Quest;
import entity.Subject;
import validation.impl.QuestValidator;
import validation.impl.SubjectValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/*
 * команда добавления предмета
 * */

public class AddSubjectCommand implements CommandWithValidation{

    /*
     * обработка формы
     * */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Optional<Subject> optionalSubject = EntityBuilder.INSTANCE.buildSubject(request);
        SubjectValidator validator = new SubjectValidator();

        if (!optionalSubject.isPresent()) {
            try {
                response.sendError(400);
            } catch (IOException e) {
                throw new CommandException(e);
            }
            return;
        }

        Subject subject = optionalSubject.get();

        if (!validator.isValid(subject)) {
            onValidationError(request, response, "ошибка добавления предмета в базу данных");
            return;
        }

        onSuccess(request, response);
    }

    @Override
    public String getCommandName() {
        return "add_subject";
    }

    /*
     * возврат к листу предметов при правильном заполнением формы
     * */

    @Override
    public void onSuccess(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String params = "?command=to_subjects_list";
            response.sendRedirect(PagePath.SERVLET + params);

        } catch (IOException e) {
            throw new CommandException(e);
        }
    }

    /*
     * возврат к форме добавления предмета при ошибки заполнения формы
     * */
    @Override
    public void onValidationError(HttpServletRequest request, HttpServletResponse response, String feedback) throws CommandException {
        try {
            request.setAttribute("feedback", feedback);
            request.getRequestDispatcher(PagePath.SERVLET + "?command=to_add_subject").forward(request, response);
        } catch (IOException | ServletException e) {
            throw new CommandException(e);
        }
    }
}
