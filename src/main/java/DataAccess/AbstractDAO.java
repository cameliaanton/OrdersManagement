package DataAccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;

/**
 * Abstract Data Access Object class for performing CRUD operations on a database table.
 *
 * @param <T> the type of object managed by the DAO
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;
    /**
     * Constructs a new instance of the AbstractDAO class.
     * Retrieves the actual type argument from the subclass and sets it as the type parameter.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    /**
     * Creates a DELETE query for the entity.
     *
     * @return The DELETE query string.
     */
    private String createDeleteQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append("`").append(type.getSimpleName()).append("`");
        sb.append(" WHERE id = ?");
        return sb.toString();
    }
    /**
     * Creates an INSERT query for the entity.
     *
     * @return The INSERT query string.
     */
    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append("`").append(type.getSimpleName()).append("`");
        sb.append(" (");
        Field[] fields= type.getDeclaredFields();
        for(int i=0;i<fields.length;i++)
        {
            sb.append(fields[i].getName());
            if(i+1<fields.length)
                sb.append(",");
        }
        sb.append(") VALUES (");
        for (int i = 0; i < fields.length; i++) {
            sb.append("?");
            if (i + 1 < fields.length) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }
    /**
     * Creates an UPDATE query for the entity.
     *
     * @return The UPDATE query string.
     */
    private String createUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append("`").append(type.getSimpleName()).append("`");
        sb.append(" SET ");
        Field[] fields= type.getDeclaredFields();
        for(int i=0;i<fields.length;i++)
        {
            if(!fields[i].getName().equals("id"))
            {
                sb.append(fields[i].getName());
                sb.append(" = ?");
                if(i<fields.length-1)
                    sb.append(", ");
            }
        }
        sb.append(" WHERE ");
       // sb.append((type.getSimpleName().toLowerCase()));
        sb.append(" id = ?");
        return sb.toString();
    }
    /**
     * Creates a SELECT query for the specified field.
     *
     * @param field The field to select.
     * @return The SELECT query string.
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        switch (field) {
            case "id":
                sb.append("SELECT ");
                sb.append(" * ");
                sb.append(" FROM ");
                sb.append("`").append(type.getSimpleName()).append("`");
                sb.append(" WHERE " + field + " =?");
                break;
            case "findAll":
                sb.append("SELECT ");
                sb.append(" * ");
                sb.append(" FROM ");
                sb.append("`").append(type.getSimpleName()).append("`");
                break;
            case "nume":
                sb.append("SELECT ");
                sb.append(" * ");
                sb.append(" FROM ");
                sb.append("`").append(type.getSimpleName()).append("`");
                sb.append(" WHERE " + field + " = ?");
                break;
        }
        return sb.toString();
    }
    /**
     * Retrieves all entities from the database.
     *
     * @return A list of all entities.
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> resultList = new ArrayList<>();
        String query = createSelectQuery("findAll");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            resultList = createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return resultList;
    }
    /**
     * Retrieves an entity by its ID from the database.
     *
     * @param id The ID of the entity.
     * @return The found entity, or null if not found.
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            List<T> obsects= createObjects(resultSet);
            if(!obsects.isEmpty())
                return  obsects.get(0);
          /*  if (resultSet.next()) {
                return createObjects(resultSet).get(0);
            } */
            //return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     * Creates objects from the result set.
     *
     * @param resultSet The result set to create objects from.
     * @return A list of created objects.
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * Inserts an entity into the database.
     *
     * @param t The entity to insert.
     * @return The ID of the inserted entity.
     */
    public  int insert(T t) {
        Connection connection = null;
        PreparedStatement insertStatement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            insertStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            Field[] fields= t.getClass().getDeclaredFields();
            for(int i=0;i<fields.length;i++)
            {
                Field field= fields[i];
                field.setAccessible(true);
                Object value= field.get(t);
                insertStatement.setObject(i+1,value);
            }
            insertStatement.executeUpdate();
            resultSet= insertStatement.getGeneratedKeys();
            if(resultSet.next())
            {
                int insertId= resultSet.getInt(1);
                Field idField= t.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(t,insertId);
            }
           // insertStatement.setInt(1, id);
           // resultSet = insertStatement.executeQuery();
            return 1;
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:Insert " + e.getMessage());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(connection);
        }

        return 0;
    }
    /**
     * Updates an entity in the database.
     *
     * @param t The entity to update.
     * @return The updated entity, or null if the update failed.
     */
    public T update(T t) {
        Connection connection = null;
        PreparedStatement updateStatement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery();
        int insertedId;
        try {
            connection = ConnectionFactory.getConnection();
            updateStatement = connection.prepareStatement(query);
            Field[] fields= t.getClass().getDeclaredFields();
            int parameterIndex = 1;

            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(t);
                if (!field.getName().equals("id")) {
                    if (field.getType() == String.class) {
                        updateStatement.setString(parameterIndex, (String) value);
                    } else if (field.getType() == int.class) {
                        updateStatement.setInt(parameterIndex, (int) value);
                    }
                    parameterIndex++;
                }
            }
            Field idField = t.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            int idValue = (int) idField.get(t);
            updateStatement.setInt(parameterIndex, idValue);
            updateStatement.executeUpdate();
            return t;
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:Update " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     * Deletes an object from the database table based on its ID.
     *
     * @param id the ID of the object to delete
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement deleteStatement = null;
        String query = createDeleteQuery();
        try {
            connection = ConnectionFactory.getConnection();
            deleteStatement = connection.prepareStatement(query);
            deleteStatement.setInt(1,id);
            deleteStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:Delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(connection);
        }
    }
    /**
     * Finds an object in the database table by its name.
     *
     * @param nume the name of the object
     * @param fieldName the name of the field to search for the object
     * @return the found object
     */
    public Object findByName(String nume,String fieldName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(fieldName);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, nume);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findByNume " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}