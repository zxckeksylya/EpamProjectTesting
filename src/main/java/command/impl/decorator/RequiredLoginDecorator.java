package command.impl.decorator;

import command.Command;
import command.CommandException;
import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/*
* декоратор обработки команд для авторизированого пользователя
* */

public class RequiredLoginDecorator extends CommandDecorator{

    public RequiredLoginDecorator(Command command) { super(command); }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
        //проверка авторизирован ли пользователь
        String userLogin = (String) request.getSession().getAttribute("login");
        Optional<User> userOptional = UserService.INSTANCE.getByLogin(userLogin);

        if(!userOptional.isPresent()){
            try {
                response.sendError(403);
            }catch (IOException e){
                throw new CommandException(e);
            }
            return;
        }
        super.execute(request,response);
    }
}
