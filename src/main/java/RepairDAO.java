import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class RepairDAO {
    private MysqlConnection mysqlConnection;

    public RepairDAO(MysqlConnection mysqlConnection) throws IOException, SQLException {
        this.mysqlConnection = mysqlConnection;
        createTableRepairsIfNotExists();

    }

    private void createTableRepairsIfNotExists() throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CarshopQueries.CREATE_TABLE_REPAIR_QUERY)) {
                statement.execute();
            }
        }
    }

    public boolean insertRepair(Repair repair) throws SQLException {
        try(Connection connection = mysqlConnection.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(CarshopQueries.INSERT_REPAIR)) {
                statement.setDate(1, java.sql.Date.valueOf(repair.getAddDate().toLocalDate()));
                statement.setString(2, repair.getContents());
                statement.setInt(3, repair.getCarId());
                int recordAdded = statement.executeUpdate();
                if(recordAdded>0){
                    System.out.println(recordAdded);
                    return true;
                }
            }
        }
        return false;
    }

    public List<Repair> getRepairsById (int id) {

        return null;
    }
}
