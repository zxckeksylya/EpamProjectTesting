package dbbinding.impl;

import dbbinding.DbAttributeName;
import dbbinding.DbBindingException;
import dbbinding.DbEntityBinding;
import entity.Answer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AnswerBinding implements DbEntityBinding<Answer> {

    @Override
    public String getTableName() { return "answers"; }

    @Override
    public List<String> getColumns() {
        return List.of(DbAttributeName.TEXT_OF_ANSWER, DbAttributeName.ANSWER_IS_TRUE,
                DbAttributeName.QUEST_ID);
    }

    @Override
    public Answer build(ResultSet rs) throws SQLException, DbBindingException {
        Answer answer = new Answer();
        answer.setId(rs.getInt(DbAttributeName.ID));
        answer.setTextOfAnswer(rs.getString(DbAttributeName.TEXT_OF_ANSWER));
        answer.setAnswerIsTrue(rs.getString(DbAttributeName.ANSWER_IS_TRUE));
        answer.setQuestID(rs.getInt(DbAttributeName.QUEST_ID));
        return answer;
    }

    @Override
    public void bind(PreparedStatement statement, Answer entity) throws SQLException, DbBindingException {
        int index = 0;
        statement.setString(++index, entity.getTextOfAnswer());
        statement.setString(++index, entity.getAnswerIsTrue());
        statement.setInt(++index, entity.getQuestID());

        if (index != getColumns().size()) {
            throw new DbBindingException("неверное количество столбцов в таблице " + getTableName());
        }
    }

    @Override
    public String getIdentityColumn() {
        return DbAttributeName.QUEST_ID;
    }

    @Override
    public void bindIdentityData(PreparedStatement statement, Answer entity) throws SQLException, DbBindingException {
        statement.setInt(1, entity.getQuestID());
    }
}
