package service;

import dbbinding.impl.UserBinding;
import entity.User;
import repository.RepositoryException;
import repository.impl.SqlRepository;

import javax.servlet.ServletException;
import java.util.Objects;
import java.util.Optional;

//сервис авторизации пользователя

public enum UserService {
    INSTANCE;

    public boolean checkPassword(User user){

        SqlRepository<User> repository = new SqlRepository<User>(new UserBinding());

        try {
            int userId = repository.getEntityId(user);
            String password = repository.getEntityById(userId).getPassword();
            System.out.println(password);
            System.out.println(user.getPassword());
            return Objects.equals(password, user.getPassword());
        } catch (RepositoryException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<User> getByLogin(String login)
    {
        User user = new User();
        user.setLogin(login);

        try{
            SqlRepository<User> repository = new SqlRepository<User>(new UserBinding());
            int userId = repository.getEntityId(user);
            return Optional.of(repository.getEntityById(userId));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
