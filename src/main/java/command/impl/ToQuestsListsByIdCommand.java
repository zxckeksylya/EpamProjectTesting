package command.impl;

import command.Command;
import command.CommandException;
import command.PagePath;
import command.ParameterName;
import dbbinding.impl.QuestBinding;
import entity.Quest;
import repository.impl.SqlRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//команда перехода к листу вопросов определенного теста

public class ToQuestsListsByIdCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        int test_id = Integer.parseInt(request.getParameter(ParameterName.TEST_ID));
        try {
            request.setAttribute(ParameterName.QUESTS_LIST,
                    new SqlRepository<Quest>(new QuestBinding()).getAllEntitiesWithTestId(test_id));
            request.getRequestDispatcher(PagePath.QUESTS_LIST).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCommandName() {
        return "to_quests_list_by_id";
    }
}
