package com.antonioscardace.app;

import java.io.FileInputStream;
import java.util.Properties;

public final class Config {
    private Properties PROPS;
    private static Config instance = new Config();

    private Config() {
        try {
            FileInputStream propsInput = new FileInputStream("config.properties");
            this.PROPS = new Properties();
            this.PROPS.load(propsInput);
        }
        catch (Exception ex) {
            this.PROPS = null;
            System.out.println("Config Error: " + ex.getMessage());
        }
    }

    public static Config getInstance() {
        return Config.instance;
    }
    
    public String get(String key) {
        return this.PROPS.getProperty(key);
    }

    public Integer getInt(String key) {
        return Integer.parseInt(this.PROPS.getProperty(key));
    }
}