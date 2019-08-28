import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AutoDAO {
    private MysqlConnection mysqlConnection;

    public AutoDAO(MysqlConnection mysqlConnection) throws IOException, SQLException {
        this.mysqlConnection = mysqlConnection;
        createTableIfNotExists();
    }

    private void createTableIfNotExists() throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CarshopQueries.CREATE_TABLE_QUERY)) {
                statement.execute();
            }
        }
    }



    public List<Auto> selectAll () throws SQLException {
        List<Auto> auta = new ArrayList<>();
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CarshopQueries.SELECT_ALL)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Auto auto = getAutoFromResultset(resultSet);
                    auta.add(auto);
                }
            }
        }
        return auta;
    }

    public boolean deleteAutoById (int id) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CarshopQueries.DELETE_BY_ID)) {
                statement.setInt(1, id);
                int updated = statement.executeUpdate();
                if (updated > 0) {
                    System.out.println("Usunięto rekord o id = " + id);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean deleteAutoByNrRej (String nrRej) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CarshopQueries.DELETE_BY_NR_REJ)) {
                statement.setString(1, nrRej);
                int updated = statement.executeUpdate();
                if (updated > 0) {
                    System.out.println("Usunięto auto o nr rejestracyjnym = " + nrRej);
                    return true;
                }
            }
        }
        return false;
    }

    public List<Auto> listAutosByRej (String nrRej) throws SQLException {
        List<Auto> auta = new ArrayList<>();
        try (Connection connection = mysqlConnection.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(CarshopQueries.SELECT_BY_NR_REJ)) {
                statement.setString(1, "%" + nrRej + "%");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Auto auto = getAutoFromResultset(resultSet);
                    auta.add(auto);
                }
            }
        }
        return auta;
    }

    public List<Auto> listAutosByName (String name) throws SQLException {
        List<Auto> auta = new ArrayList<>();
        try (Connection connection = mysqlConnection.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(CarshopQueries.SELECT_BY_OWNER_NAME)) {
                statement.setString(1, "%" + name + "%");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Auto auto = getAutoFromResultset(resultSet);
                    auta.add(auto);
                }
            }
        }
        return auta;
    }

    private Auto getAutoFromResultset(ResultSet resultSet) throws SQLException {
        Auto auto = new Auto();
        auto.setId(resultSet.getInt(1));
        auto.setNr_rejestracyjny(resultSet.getString(2));
        auto.setPrzebieg(resultSet.getInt(3));
        auto.setMarka_model(resultSet.getString(4));
        auto.setRocznik(resultSet.getInt(5));
        auto.setTypAuta(TypAuta.valueOf(resultSet.getString(6)));
        auto.setNazwisko_wlasciciela(resultSet.getString(7));
        return auto;
    }

    public boolean insertCar(Auto auto) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CarshopQueries.INSERT_CAR, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, auto.getNr_rejestracyjny());
                statement.setInt(2, auto.getPrzebieg());
                statement.setString(3, auto.getMarka_model());
                statement.setInt(4, auto.getRocznik());
                statement.setString(5, auto.getTypAuta().toString());
                statement.setString(6, auto.getNazwisko_wlasciciela());

                statement.executeUpdate();
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()){
                    Long idCreated = generatedKeys.getLong(1);
                    System.out.println("Utworzono rekord o nr: " + idCreated);
                    return true;
                }
            }
        }
        return false;
    }


}//class
