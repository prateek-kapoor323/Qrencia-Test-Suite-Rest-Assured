package org.qrencia.utils;

import lombok.extern.slf4j.Slf4j;
import org.qrencia.constants.CommonConstants;

import java.io.*;
import java.util.Properties;

@Slf4j
public class PropertyUtils
{
    public static String readProp(String configKey){
        String propertyValue = null;
        try {
            log.info("Request received to read property value for key - {}", configKey);
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(CommonConstants.CONFIG_PROPERTIES_FILE_PATH);
            properties.load(fileInputStream);
            propertyValue = (properties.getProperty(configKey));
        } catch (Exception e) {
            log.error("Could not read the property value due to exception - {}", e.getMessage());
        }
        return propertyValue;
    }
}