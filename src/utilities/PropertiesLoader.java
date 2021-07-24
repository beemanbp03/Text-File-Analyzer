package utilities;

import java.io.*;
import java.util.*;

/**
 * This interface contains a default method that can be used anywhere a Properties
 * object is needed to be loaded.
 * @author Eric Knapp
 *
 */
public interface PropertiesLoader{

    /**
     * This default method will load a properties file into a Properties instance
     * and return it.
     * @param propertiesFilePath a path to a file on the java classpath list
     * @return a populated Properties instance or an empty Properties instance if
     * the file path was not found.
     * @throws Exception exception thrown
     */
     default Properties loadProperties(String propertiesFilePath) throws Exception {
         Properties properties = new Properties();
         try {
             //how can I split up the line below? It's using .dot notation and
             //I want to know a good way of splitting .dot notation into 2 lines
             properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
         } catch (IOException ioException) {
             ioException.printStackTrace();
             throw ioException;
         } catch (Exception exception) {
             System.out.println("Exception caught inside the "
                    + "PropertiesLoader.loadProperties method");
             exception.printStackTrace();
             throw exception;
         }
         return properties;
     }
}
