package command.impl;

import command.Command;
import command.CommandException;
import command.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* команда возвращения к авторизации
* */

public class LogoutCommand implements Command {
    //переход к форме авторизации
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            request.getSession().invalidate();
            String param = "?command=to_login";
            response.sendRedirect(PagePath.BASE_SERVLET+PagePath.SERVLET + param);
        } catch (IOException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public String getCommandName() { return "logout"; }
}
