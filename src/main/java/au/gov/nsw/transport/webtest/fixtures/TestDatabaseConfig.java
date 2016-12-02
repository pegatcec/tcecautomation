package au.gov.nsw.transport.webtest.fixtures;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * User: GumpuR
 */
public class TestDatabaseConfig {

    Properties configurationProperties = new Properties();

    public TestDatabaseConfig() throws IOException {
        InputStream configFile =
                this.getClass().getClassLoader()
                               .getResourceAsStream("database.properties");
        configurationProperties.load(configFile);
    }

    private String getFromSystemPropertiesOrConfigurationFile(String property) {
        if (System.getProperty(property) != null) {
            return System.getProperty(property);
        } else {
            return configurationProperties.getProperty(property);
        }
    }
    public String getHost() {
        return getFromSystemPropertiesOrConfigurationFile("database.host");
    }

    public String getPort() {
        return getFromSystemPropertiesOrConfigurationFile("database.port");
    }

    public String getSid() {
        return getFromSystemPropertiesOrConfigurationFile("database.sid");
    }

    public String getUser() {
        return getFromSystemPropertiesOrConfigurationFile("database.user");
    }

    public String getPassword() {
        return getFromSystemPropertiesOrConfigurationFile("database.password");
    }
}
