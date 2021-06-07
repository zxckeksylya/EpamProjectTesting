package dbbinding.impl;

import dbbinding.DbAttributeName;
import dbbinding.DbBindingException;
import dbbinding.DbEntityBinding;
import entity.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestBinding implements DbEntityBinding<Test> {
    @Override
    public String getTableName() {
        return "tests";
    }

    @Override
    public List<String> getColumns() {
        return List.of(DbAttributeName.NAME_OF_TEST);

    }

    @Override
    public Test build(ResultSet rs) throws SQLException, DbBindingException {
        Test test = new Test();
        test.setId(rs.getInt(DbAttributeName.ID));
        test.setNameOfTest(rs.getString(DbAttributeName.NAME_OF_TEST));
        return test;
    }

    @Override
    public void bind(PreparedStatement statement, Test entity) throws SQLException, DbBindingException {
        int index = 0;
        statement.setString(++index, entity.getNameOfTest());
        if (index != getColumns().size())
            throw new DbBindingException("Неверное количество столбцов в таблице " + getTableName());
    }

    @Override
    public String getIdentityColumn() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void bindIdentityData(PreparedStatement statement, Test entity) throws SQLException, DbBindingException {
        throw new UnsupportedOperationException();
    }
}
