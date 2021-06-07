package dbbinding.impl;

import dbbinding.DbAttributeName;
import dbbinding.DbBindingException;
import dbbinding.DbEntityBinding;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserBinding implements DbEntityBinding<User> {
    @Override
    public String getTableName() {
        return "users";
    }

    @Override
    public List<String> getColumns() {
        return List.of(DbAttributeName.LOGIN, DbAttributeName.PASSWORD, DbAttributeName.ROLE_ID);
    }

    @Override
    public User build(ResultSet rs) throws SQLException, DbBindingException {
        User user = new User();
        user.setId(rs.getInt(DbAttributeName.ID));
        user.setLogin(rs.getString(DbAttributeName.LOGIN));
        user.setPassword(rs.getString(DbAttributeName.PASSWORD));
        user.setRoleID(rs.getInt(DbAttributeName.ROLE_ID));
        return user;
    }

    @Override
    public void bind(PreparedStatement statement, User entity) throws SQLException, DbBindingException {
        int index = 0;

        statement.setString(++index,entity.getLogin());
        statement.setString(++index,entity.getPassword());
        statement.setInt(++index,entity.getRoleID());

        if (index != getColumns().size())
            throw new DbBindingException("Неверное количество столбцов в таблице " + getTableName());
    }

    @Override
    public String getIdentityColumn() {
        return DbAttributeName.LOGIN;
    }

    @Override
    public void bindIdentityData(PreparedStatement statement, User entity) throws SQLException, DbBindingException {
        statement.setString(1,entity.getLogin());
    }
}
