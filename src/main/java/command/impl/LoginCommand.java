package command.impl;

import command.CommandException;
import command.EntityBuilder;
import command.PagePath;
import entity.User;
import service.UserService;
import validation.impl.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* авторизация пользователя
* */

public class LoginCommand implements CommandWithValidation{
    /*
    * проверка формы
    * */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = EntityBuilder.INSTANCE.buildUser(request).orElseThrow(CommandException::new);
        UserValidator validator = new UserValidator();

        if(!validator.isValid(user)){
            onValidationError(request,response,validator.getValidationFeedback());
            return;
        }

        if(!UserService.INSTANCE.checkPassword(user)){
            onValidationError(request,response,"неверный логин или пароль");
            return;
        }

        request.getSession().setAttribute("login",user.getLogin());
        onSuccess(request,response);
    }

    @Override
    public String getCommandName() {
        return "login";
    }

    /*
    * переход к листу тестов при верной авторизации
    * */

    @Override
    public void onSuccess(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try{
            String params = "?command=to_tests_list";
            response.sendRedirect(PagePath.BASE_SERVLET+PagePath.SERVLET+params);
        } catch (IOException e) {
            throw new CommandException(e);
        }
    }

    /*
    * возврат к форме авторизации при не верной авторизации
    * */

    @Override
    public void onValidationError(HttpServletRequest request, HttpServletResponse response, String feedback) throws CommandException {
        try {
            request.setAttribute("feedback",feedback);
            request.getRequestDispatcher(PagePath.SERVLET+"?command=to_login").forward(request,response);
        }catch (IOException | ServletException e){
            throw new CommandException(e);
        }
    }
}
