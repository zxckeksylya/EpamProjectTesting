package command.impl;

import command.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTestingCommand implements CommandWithValidation{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
    }

    @Override
    public String getCommandName() {
        return "add_testing";
    }


    @Override
    public void onSuccess(HttpServletRequest request, HttpServletResponse response) throws CommandException {

    }

    @Override
    public void onValidationError(HttpServletRequest request, HttpServletResponse response, String feedback) throws CommandException {

    }
}
