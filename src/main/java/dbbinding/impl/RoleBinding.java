package dbbinding.impl;

import dbbinding.DbAttributeName;
import dbbinding.DbBindingException;
import dbbinding.DbEntityBinding;
import entity.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoleBinding implements DbEntityBinding<Role> {

    @Override
    public String getTableName() {
        return "roles";
    }

    @Override
    public List<String> getColumns() {
        return List.of(DbAttributeName.NAME_ROLE);
    }

    @Override
    public Role build(ResultSet rs) throws SQLException, DbBindingException {
        Role role = new Role();

        role.setId(rs.getInt(DbAttributeName.ID));
        role.setName(rs.getString(DbAttributeName.NAME_ROLE));

        return role;
    }

    @Override
    public void bind(PreparedStatement statement, Role entity) throws SQLException, DbBindingException {
        int index = 0;
        statement.setInt(++index, entity.getId());
        if (index != getColumns().size())
            throw new DbBindingException("Неверное количество столбцов в таблице " + getTableName());
    }

    @Override
    public String getIdentityColumn() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void bindIdentityData(PreparedStatement statement, Role entity) throws SQLException, DbBindingException {
        throw new UnsupportedOperationException();
    }
}
