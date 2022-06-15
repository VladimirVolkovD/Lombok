package utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private Properties properties;

    public PropertyReader() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            String currentUser = getProperty("user");
            Properties userProps = new Properties();
            userProps.load(getClass().getClassLoader().getResourceAsStream(currentUser + ".properties"));
            properties.putAll(userProps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getLoginUrl() {
        return properties.getProperty("login.url");
    }

    public String getCartPageTitle() {
        return properties.getProperty("cartPageTitle");
    }

    public String getCheckoutPageTitle() {
        return properties.getProperty("checkoutPageTitle");
    }

    public String getProductsPageTitle() {
        return properties.getProperty("productsPageTitle");
    }
}