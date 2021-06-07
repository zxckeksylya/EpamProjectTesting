package controller;

import command.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

//переопределение webServlet

@WebServlet(PagePath.SERVLET)
public class SystemOfTestingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException {
        String commandName = request.getParameter(ParameterName.COMMAND);

        try {
            Optional<Command> optionalCommand = CommandProvider.INSTANCE.findCommand(commandName);
            Command command = optionalCommand.orElseThrow(CommandException::new);
            command.execute(request, response);
        } catch (CommandException | IOException e) {
            throw new ServletException(e);
        }
    }

}
