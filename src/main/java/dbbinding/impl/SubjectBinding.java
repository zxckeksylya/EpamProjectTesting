package dbbinding.impl;

import dbbinding.DbAttributeName;
import dbbinding.DbBindingException;
import dbbinding.DbEntityBinding;
import entity.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SubjectBinding implements DbEntityBinding<Subject> {
    @Override
    public String getTableName() {
        return "subjects";
    }

    @Override
    public List<String> getColumns() {
        return List.of(DbAttributeName.NAME_OF_SUBJECT, DbAttributeName.TEST_ID);
    }

    @Override
    public Subject build(ResultSet rs) throws SQLException, DbBindingException {
        Subject subject = new Subject();
        subject.setId(rs.getInt(DbAttributeName.ID));
        subject.setNameOfSubject(rs.getString(DbAttributeName.NAME_OF_SUBJECT));
        subject.setTestID(rs.getInt(DbAttributeName.TEST_ID));
        return subject;
    }

    @Override
    public void bind(PreparedStatement statement, Subject entity) throws SQLException, DbBindingException {
        int index = 0;
        statement.setString(++index, entity.getNameOfSubject());
        statement.setInt(++index, entity.getTestID());
        if (index != getColumns().size())
            throw new DbBindingException("Неверное количество столбцов в таблице " + getTableName());
    }

    @Override
    public String getIdentityColumn() {
        return DbAttributeName.TEST_ID;
    }

    @Override
    public void bindIdentityData(PreparedStatement statement, Subject entity) throws SQLException, DbBindingException {
        statement.setInt(1, entity.getTestID());
    }
}
