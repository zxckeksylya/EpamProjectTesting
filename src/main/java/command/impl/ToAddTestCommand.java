package command.impl;

import command.Command;
import command.CommandException;
import command.PagePath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//команда перехода к форме добавления теста

public class ToAddTestCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            request.getRequestDispatcher(PagePath.ADD_TEST).forward(request, response);
        } catch (ServletException | IOException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public String getCommandName() {
        return "to_add_test";
    }
}
