package command.impl;

import command.Command;
import command.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* интерфейс команды с проверкой
* */

public interface CommandWithValidation extends Command {

    void onSuccess(HttpServletRequest request, HttpServletResponse response) throws CommandException;

    void onValidationError(HttpServletRequest request, HttpServletResponse response, String feedback) throws CommandException;
}
