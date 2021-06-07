package command.impl;

import command.Command;
import command.CommandException;
import command.PagePath;
import command.ParameterName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//команда перехода к добавления ответа

public class ToAddAnswerCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            request.setAttribute(ParameterName.QUEST_ID,request.getParameter(ParameterName.QUEST_ID));
            request.getRequestDispatcher(PagePath.ADD_ANSWER).forward(request, response);
        } catch (ServletException | IOException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public String getCommandName() {
        return "to_add_answer";
    }
}
