package utils;

import org.testng.Assert;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ViTaLES on 17.06.2016.
 */
public class PropertyLoader {

    private static final String PROPERTY_FILE = "/application.properties";

    public static String loadProperty(String name) {
        Properties props = new Properties();
        try {
            props.load(PropertyLoader.class.getResourceAsStream(PROPERTY_FILE));
        } catch (IOException e) {
            Assert.fail("Incorrect property name - " + name);
        }
        String value = "";
        if (name != null) {
            value = props.getProperty(name);
        }
        return value;
    }

    public static void setProperty(String name, String value) {
        Properties props = new Properties();

        props.setProperty(name, value);

        if (name == null || value == null) {
            Assert.fail("Set up Incorrect property name - " + name
                    + "\n or property value - " + value);
        }
    }

}