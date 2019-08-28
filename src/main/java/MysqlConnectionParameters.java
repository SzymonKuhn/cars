import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
@Getter
public class MysqlConnectionParameters {
    private static final String PROPERTIES_FILE_NAME = "/jdbc.properties";
    private Properties properties;

    private String dbHost;
    private String dbPort;
    private String dbUsername;
    private String dbPassword;
    private String dbName;

    public MysqlConnectionParameters() throws IOException {
        loadConfigurationFrom(PROPERTIES_FILE_NAME);

        dbHost = loadParameter("jdbc.database.host");
        dbPort = loadParameter("jdbc.database.port");
        dbName = loadParameter("jdbc.database.name");
        dbUsername = loadParameter("jdbc.username");
        dbPassword = loadParameter("jdbc.password");
    }

    private Properties loadConfigurationFrom (String resorce) throws IOException {
        properties = new Properties();
        InputStream propertiesAsStream = this.getClass().getResourceAsStream(resorce);
        if (propertiesAsStream == null) {
            System.err.println("Cannot load resource file");
            throw new FileNotFoundException("Resources files cannot load");
        }
        properties.load(propertiesAsStream);
        return properties;
    }

    private String loadParameter (String propertyName) {
        return properties.getProperty(propertyName);
    }
}
