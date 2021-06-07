package command;

import command.impl.*;
import command.impl.decorator.RequiredLoginDecorator;

import java.util.List;
import java.util.Optional;

/*
* провайдер команд
* */

public enum CommandProvider {
    INSTANCE;

    private List<Command> commands;

    CommandProvider() {
        commands= List.of(new LoginCommand(),new ToLoginCommand(), new LogoutCommand(),
                new RequiredLoginDecorator(new AddAnswerCommand()),
                new RequiredLoginDecorator(new AddQuestCommand()),
                new RequiredLoginDecorator(new AddTestCommand()),
                new RequiredLoginDecorator(new AddTestCommand()),
                new RequiredLoginDecorator(new ToAddAnswerCommand()),
                new RequiredLoginDecorator(new ToAddQuestCommand()),
                new RequiredLoginDecorator(new ToAddSubjectCommand()),
                new RequiredLoginDecorator(new ToAddTestCommand()),
                new RequiredLoginDecorator(new ToTestsListCommand()),
                new RequiredLoginDecorator(new ToQuestsListCommand()),
                new RequiredLoginDecorator(new ToQuestsListsByIdCommand()),
                new RequiredLoginDecorator(new ToAnswersListCommand()),
                new RequiredLoginDecorator(new ToAnswersListsByIdCommand()),
                new RequiredLoginDecorator(new ToAddTestingCommand()));
    }
    /*
    * фильтр поиска команды
    * */
    public Optional<Command> findCommand(String commandName){
        return commands.stream().filter(x -> x.getCommandName().equals(commandName)).findFirst();
    }
}
