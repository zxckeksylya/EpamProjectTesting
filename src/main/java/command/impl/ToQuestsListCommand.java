package command.impl;

import command.Command;
import command.CommandException;
import command.PagePath;
import command.ParameterName;
import dbbinding.impl.AnswerBinding;
import dbbinding.impl.QuestBinding;
import entity.Answer;
import entity.Quest;
import repository.impl.SqlRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//команда перехода к листу вопросов

public class ToQuestsListCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try{
            request.setAttribute(ParameterName.QUESTS_LIST,
                    new SqlRepository<Quest>(new QuestBinding()).getAllEntities());
            request.getRequestDispatcher(PagePath.QUESTS_LIST).forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCommandName() {
        return "to_quests_list";
    }
}
