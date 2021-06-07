package command.impl;

import command.Command;
import command.CommandException;
import command.PagePath;
import command.ParameterName;
import dbbinding.impl.TestBinding;
import entity.Test;
import repository.RepositoryException;
import repository.impl.SqlRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//команда перехода к листу тестов

public class ToTestsListCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
        try {
            request.setAttribute(ParameterName.TESTS_LIST, new SqlRepository<Test>(new TestBinding()).getAllEntities());
            request.getRequestDispatcher(PagePath.TESTS_LIST).forward(request, response);
        } catch (ServletException | IOException | RepositoryException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public String getCommandName() {
        return "to_tests_list";
    }
}
