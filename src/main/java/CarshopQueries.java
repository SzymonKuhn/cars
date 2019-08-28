public interface CarshopQueries {

    String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS `auta` (\n" +
            "`id`  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
            "`nr_rejestracyjny` VARCHAR (255) NOT NULL,\n" +
            "`przebieg` INT NOT NULL,\n" +
            "`marka_model` VARCHAR(255) NOT NULL,\n" +
            "`rocznik` INT NOT NULL,\n" +
            "`typ` VARCHAR (255) NOT NULL,\n" +
            "`nazwisko_wlasciciela` VARCHAR (255) NOT NULL);";

    String CREATE_TABLE_REPAIR_QUERY = "CREATE TABLE IF NOT EXISTS `repairs` (\n" +
            "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
            "added_date DATETIME,\n" +
            "accomplished TINYINT NOT NULL DEFAULT false,\n" +
            "repair_date DATETIME,\n" +
            "contents VARCHAR(100),\n" +
            "id_car_fk INT NOT NULL,\n" +
            "FOREIGN KEY (id_car_fk) REFERENCES auta(id));";

    String INSERT_CAR = "INSERT INTO auta (nr_rejestracyjny, przebieg, marka_model, rocznik, typ, nazwisko_wlasciciela) \n" +
            "VALUES (?, ?, ?, ?, ?, ?);";

    String SELECT_ALL = "SELECT * FROM auta;";
    String DELETE_BY_ID = "DELETE FROM auta WHERE id=?";
    String DELETE_BY_NR_REJ = "DELETE FROM auta WHERE nr_rejestracyjny = ?";
    String SELECT_BY_NR_REJ = "SELECT * FROM auta WHERE nr_rejestracyjny LIKE ?;";
    String SELECT_BY_OWNER_NAME = "SELECT * FROM auta WHERE nazwisko_wlasciciela LIKE ?";

    String INSERT_REPAIR = "INSERT INTO repairs (added_date, contents, id_car_fk ) \n" +
            "VALUES (?, ?, ?);";
}
