package command.impl;

import command.Command;
import command.CommandException;
import command.PagePath;
import command.ParameterName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//команда перехода к форме добавления вопроса

public class ToAddQuestCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            request.setAttribute(ParameterName.TEST_ID,request.getParameter(ParameterName.TEST_ID));
            request.getRequestDispatcher(PagePath.ADD_QUEST).forward(request, response);
        } catch (ServletException | IOException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public String getCommandName() {
        return "to_add_quest";
    }
}
