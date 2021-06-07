package dbbinding.impl;

import dbbinding.DbAttributeName;
import dbbinding.DbBindingException;
import dbbinding.DbEntityBinding;
import entity.Testing;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestingBinding implements DbEntityBinding<Testing> {
    @Override
    public String getTableName() {
        return "testings";
    }

    @Override
    public List<String> getColumns() {
        return List.of(DbAttributeName.TEST_ID, DbAttributeName.USER_ID,DbAttributeName.NUMBER_OF_TRUE_QUESTS);
    }

    @Override
    public Testing build(ResultSet rs) throws SQLException, DbBindingException {
        Testing testing = new Testing();
        testing.setId(rs.getInt(DbAttributeName.ID));
        testing.setTestID(rs.getInt(DbAttributeName.TEST_ID));
        testing.setUserID(rs.getInt(DbAttributeName.USER_ID));
        testing.setNumberOfTrueQuests(rs.getInt(DbAttributeName.NUMBER_OF_TRUE_QUESTS));
        return testing;
    }

    @Override
    public void bind(PreparedStatement statement, Testing entity) throws SQLException, DbBindingException {
        int index = 0;
        statement.setInt(++index, entity.getTestID());
        statement.setInt(++index, entity.getUserID());
        statement.setInt(++index, entity.getNumberOfTrueQuests());
        if (index != getColumns().size())
            throw new DbBindingException("Неверное количество столбцов в таблице " + getTableName());
    }

    @Override
    public String getIdentityColumn() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void bindIdentityData(PreparedStatement statement, Testing entity) throws SQLException, DbBindingException {
        throw new UnsupportedOperationException();
    }
}
