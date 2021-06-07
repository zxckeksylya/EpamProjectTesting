package command.impl;

import command.Command;
import command.CommandException;
import command.PagePath;
import command.ParameterName;
import dbbinding.impl.AnswerBinding;
import dbbinding.impl.QuestBinding;
import entity.Answer;
import entity.Quest;
import repository.RepositoryException;
import repository.impl.SqlRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//команда перехода к тестированию пользователя тестом

//не доделано

public class ToAddTestingCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
        int test_id = Integer.parseInt(request.getParameter(ParameterName.TEST_ID));
        try{
            request.setAttribute(ParameterName.QUESTS_LIST,
                    new SqlRepository<Quest>(new QuestBinding()).getAllEntitiesWithTestId(test_id));

            request.setAttribute(ParameterName.ANSWERS_LIST,
                    new SqlRepository<Answer>(new AnswerBinding()).getAllEntitiesWithTestId(test_id));
            request.getRequestDispatcher(PagePath.TESTING).forward(request,response);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCommandName() {
        return "to_add_testing";
    }
}
