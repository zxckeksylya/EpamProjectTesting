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

//команда перехода к ответам определенного вопроса

public class ToAnswersListsByIdCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int quest_id = Integer.parseInt(request.getParameter(ParameterName.QUEST_ID));
        try {
            request.setAttribute(ParameterName.ANSWERS_LIST,
                    new SqlRepository<Answer>(new AnswerBinding()).getAllEntitiesWithTestId(quest_id));
            request.getRequestDispatcher(PagePath.ANSWERS_LIST).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCommandName() {
        return "to_answers_list_by_id";
    }
}
