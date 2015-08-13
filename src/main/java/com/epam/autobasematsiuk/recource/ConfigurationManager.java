package com.epam.autobasematsiuk.recource;

import java.util.ResourceBundle;

/**
 * The class ConfigurationManager.
 */
public class ConfigurationManager {

    public final static String PROPERTY_FILE = "configuration";
    private ResourceBundle resourceBundle;
    public static ConfigurationManager instance = new ConfigurationManager();

    /**
     * Instantiates a new ConfigurationManager.
     */
    private ConfigurationManager() {
        resourceBundle = ResourceBundle.getBundle(PROPERTY_FILE);
    }

    /**
     * Gets the single instance of ConfigurationManager.
     *
     * @return single instance of ConfigurationManager
     */
    public static ConfigurationManager getInstance() {
        return instance;
    }

    /**
     * Gets the property.
     *
     * @param key is the key
     * @return the property
     */
    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
