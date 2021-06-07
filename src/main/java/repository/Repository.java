package repository;

import java.util.List;

public interface Repository<T> {
    //получить id сущности таблицы
    int getEntityId(T Entity)throws RepositoryException;
    //получить сущность по id
    T getEntityById(int id) throws RepositoryException;
    //получить все сущность из таблицы
    List<T> getAllEntities() throws RepositoryException;
    //добавить сущность
    void addEntity(T entity)throws RepositoryException;
    //изменить сущность
    void updateEntity(T entity)throws RepositoryException;
    //удалить сущность
    void removeEntity(T entity)throws RepositoryException;
    //получить все сущности по спец-id
    List<T>getAllEntitiesWithTestId(int id) throws RepositoryException;
}
