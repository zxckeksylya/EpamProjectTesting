package command.impl.decorator;

import command.Command;
import command.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* декоратор для команд
* */

public abstract class CommandDecorator implements Command {
    private Command command;

    public CommandDecorator(Command command) { this.command = command; }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
        command.execute(request, response);
    }

    @Override
    public String getCommandName() { return command.getCommandName(); }
}
