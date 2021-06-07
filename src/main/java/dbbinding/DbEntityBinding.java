package dbbinding;

import entity.Entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DbEntityBinding<T extends Entity> {
    //получить название таблицы
    String getTableName();

    //получить все названия полей таблицы
    List<String> getColumns();

    //получить сущность из таблицы
    T build(ResultSet rs) throws SQLException, DbBindingException;

    //заполнить сущность таблицы
    void bind(PreparedStatement statement, T entity) throws SQLException, DbBindingException;


    //получить название спец-поля
    String getIdentityColumn();
    //заполнить сущность таблицы по спец полю
    void bindIdentityData(PreparedStatement statement, T entity) throws SQLException, DbBindingException;
}
