package dbbinding.impl;

import dbbinding.DbAttributeName;
import dbbinding.DbBindingException;
import dbbinding.DbEntityBinding;
import entity.Quest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestBinding implements DbEntityBinding<Quest> {
    @Override
    public String getTableName() {
        return "quests";
    }

    @Override
    public List<String> getColumns() {
        return List.of(DbAttributeName.TEXT_OF_QUEST, DbAttributeName.TEST_ID);
    }

    @Override
    public Quest build(ResultSet rs) throws SQLException, DbBindingException {
        Quest quest = new Quest();

        quest.setId(rs.getInt(DbAttributeName.ID));
        quest.setTextOfQuest(rs.getString(DbAttributeName.TEXT_OF_QUEST));
        quest.setTestID(rs.getInt(DbAttributeName.TEST_ID));

        return quest;
    }

    @Override
    public void bind(PreparedStatement statement, Quest entity) throws SQLException, DbBindingException {
        int index = 0;

        statement.setString(++index, entity.getTextOfQuest());
        statement.setInt(++index, entity.getTestID());

        if (index != getColumns().size())
            throw new DbBindingException("Неверное количество столбцов в таблице " + getTableName());
    }

    @Override
    public String getIdentityColumn() {
        return DbAttributeName.TEST_ID;
    }

    @Override
    public void bindIdentityData(PreparedStatement statement, Quest entity) throws SQLException, DbBindingException {
        statement.setInt(1, entity.getTestID());
    }
}
