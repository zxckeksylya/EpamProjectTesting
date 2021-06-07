package command.impl;

import command.Command;
import command.CommandException;
import command.PagePath;
import command.ParameterName;
import dbbinding.impl.AnswerBinding;
import entity.Answer;
import repository.impl.SqlRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//переход к листу вопросов

public class ToAnswersListCommand  implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try{
            request.setAttribute(ParameterName.ANSWERS_LIST,
                    new SqlRepository<Answer>(new AnswerBinding()).getAllEntities());
            request.getRequestDispatcher(PagePath.ANSWERS_LIST).forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCommandName() {
        return "to_answers_list";
    }
}
