package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ViTaLES on 30.05.2016.
 */
/*
 *  That class provides static methods for getting values from Config and UI mapping files
 *  UIMapping.properties
 */
public class ConfigData {
    public static String uiMappingFile="src/UIMapping.properties";
    private static final Logger log = Logger.getLogger(ConfigData.class);

    /*
     *  Return value from .properties file
     */
    public static String getValueFromFile(String key, String fileName) throws IOException {
        Properties p = new Properties();
        // Create stream for reading from file
        FileInputStream cfg = new FileInputStream(fileName);
        // Load Properties from input stream
        p.load(cfg);
        cfg.close();

        // Return value for the property
        return(p.getProperty(key));
    }


    /*
     *  Return By class with finding method and target for WebElement from UI mapping file
     */
    public static By ui(String key) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        // Get WebElement's locator from UI mapping file and divide it to finding method and target
        String[] partsOfLocator = getValueFromFile(key, uiMappingFile).split("\"");
        String findMethod = partsOfLocator[0].substring(0,partsOfLocator[0].length()-1);
        String target = partsOfLocator[1];

        log.info("after parsing:");
        log.info(partsOfLocator[0]);
        log.info(partsOfLocator[1]);
        log.info(findMethod);
        log.info(target);

        // Return By class with appropriate method and target
/*        refactoring:
        1) switch?
        2) update + add exeption? maybe need to create?
        3) p. 199
        */
        if (findMethod.equals("id")){
            return By.id(target);
        } else {
            if (findMethod.equals("xpath")){
                return By.xpath(target);
            } else {
                if (findMethod.equals("name")){
                    return By.name(target);
                } else {

                    if (findMethod.equals("tagName")){
                        return By.tagName(target);
                    } else {
                        if (findMethod.equals("className")){
                            return By.className(target);
                        } else {
                            if (findMethod.equals("cssSelector")){
                                return By.cssSelector(target);
                            } else {
                                return By.partialLinkText(target);
                            }
                        }
                    }
                }
            }
        }
    }



}
