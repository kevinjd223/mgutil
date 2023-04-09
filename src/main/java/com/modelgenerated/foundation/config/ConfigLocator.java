/*
 * Config.java
 *
 * Created on October 6, 2002, 7:45 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.config;


import com.modelgenerated.foundation.debug.Displayable;
import com.modelgenerated.foundation.logging.Logger;
import com.modelgenerated.tools.EnvironmentPrinter;
import com.modelgenerated.util.Assert;
import com.modelgenerated.util.StringUtil;

import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * Locates and returns Config information bases on information in the bootstrap properties file.
 * The bootstrap propertied contain config name pair with config locations. amd name.class with the class location
 *
 * If the config location is a proper URL then the  
 * 
 *
 * @author  kevind
 */
public class ConfigLocator {
    public static final String FILE_SEPARATOR = "file.separator";
    public static final String ENV_BOOTSTRAPPATH = "modelgenerated.bootstrap.path";
    
    private static final String BOOTSTRAPFILE = "bootstrap.properties";
    private static final String BASEPATH = "basepath";
    private static final Map configs = Collections.synchronizedMap(new HashMap());
    
    private static String bootstrapPath = null;
    private static String basePath = null;
    
    
    static {
        try {
            Properties properties = loadBootstapProperties();
            
            basePath = properties.getProperty(BASEPATH).trim();
            if (basePath.equals(".")) {
                basePath = bootstrapPath;             
            }
            System.out.println("basePath " + getBasePath());
            
            // display everything
            
            TreeMap sortedConfigs = new TreeMap();
            Enumeration enumProperty = properties.keys();
            while (enumProperty.hasMoreElements()) {
                String key = (String)enumProperty.nextElement();
                if (!key.equals(BASEPATH)) {
                    if (key.indexOf('.') == -1) {
                        ConfigDescriptor configDescriptor = getConfigDescriptor(properties, key);
                        
                        sortedConfigs.put(configDescriptor.order, configDescriptor);
                    }
                }
            }

            Iterator i = sortedConfigs.values().iterator();
            while (i.hasNext()) {
                com.modelgenerated.foundation.config.ConfigDescriptor configDescriptor = (ConfigDescriptor)i.next();
                loadConfig(configDescriptor);
            }
        } catch (Throwable e) {
            
            System.out.println("Error Loading Modelgenerated Configuration");
            e.printStackTrace();
            System.exit(1);
        } 
        String loggerCategory = "com.modelgenerated.foundation.config.ConfigLocator";
        Logger.info(loggerCategory, "Config information is loaded.");
        Iterator i = configs.values().iterator();
        while (i.hasNext()) {
            Config config = (Config)i.next();
            if (config instanceof Displayable) {
                // Logger.info(loggerCategory, config);
            }
        }    
    }

    private static ConfigDescriptor getConfigDescriptor(Properties properties, String key) {
        ConfigDescriptor configDescriptor = new ConfigDescriptor();

        System.out.println("Config key: " + key);
        configDescriptor.name = key;
        configDescriptor.fileName = properties.getProperty(key);
        configDescriptor.resourceName = properties.getProperty(key + ".resourceName");
        System.out.println("Config resourceName: " + configDescriptor.resourceName);
        configDescriptor.className = properties.getProperty(key + ".class");
        String orderStr = properties.getProperty(key + ".order");
        if (orderStr != null) {
            configDescriptor.order = new Integer(orderStr);
        }
        if (configDescriptor.order == null) { // still 
            configDescriptor.order = new Integer(0);
        }
        return configDescriptor;      
    }

    
    // ClassLoader loader = this.getClass().getClassLoader();        
    // InputStream inputStream = loader.getResourceAsStream(PROPERTYFILE);
    // if (inputStream == null) {
    //     throw new FileNotFoundException("property file '" + PROPERTYFILE + "' not found in the classpath");
    // }

    private static synchronized Config loadConfig(ConfigDescriptor configDescriptor) {
        try {
            Config config = (Config)Class.forName(configDescriptor.className).newInstance();

            // If resource exists then read from resource file. Otherwise read from bast path. 
            // If we wanted to allow overrides, we would read the boot path or some other URL first and then load as resource.
            URL configURL = null;
            if (!StringUtil.isEmpty(configDescriptor.resourceName)) {
                //ClassLoader loader = ConfigLocator.class.getClassLoader();        
                //configInputStream = loader.getResourceAsStream(configDescriptor.resourceName);
                System.out.println("load from resource name :" + configDescriptor.resourceName);
            	// configURL = Thread.currentThread().getContextClassLoader().getResource(configDescriptor.resourceName);
            	//configURL = System.class.getResource(configDescriptor.resourceName);
                
                // use the class loader of the config class
            	configURL = config.getClass().getResource(configDescriptor.resourceName);
                
                System.out.println("configURL3 :" + configURL);
            } else {
                String configFile = getBasePath() + "/" + configDescriptor.fileName;
                System.out.println("basePath " + getBasePath());
                System.out.println("configFile:" + configFile + ":");
                
				configURL = new URL(configFile);
            }
            InputStream configInputStream = configURL.openStream();
            config.load(configInputStream);

            configs.put(configDescriptor.name, config);
            
            return config;
		} catch (IOException e) {
            throw new ConfigNotFoundException("InstantiationException: " + configDescriptor.fileName,e);
        } catch (ClassNotFoundException e) {
            // do nothing
            // If this is called on loading on the ConfigLocator, then the class might be found if it's
            // in a jar that is not loaded yet.
        } catch (InstantiationException e) {
            throw new ConfigNotFoundException("InstantiationException: " + configDescriptor.fileName,e);
        } catch (IllegalAccessException e) {
            throw new ConfigNotFoundException("IllegalAccessException: " + configDescriptor.fileName,e);
        } 
        
        return null;
    }
    
    /** Creates a new instance of Config */
    private ConfigLocator() {
    }
    
    static private Properties loadBootstapProperties() {
        try {
            InputStream bootstrapInStream = null; 
            
            // there is a strange bug that keep this from loading more than x configs
            // try using getResource as stream and see if the problem is fixed.

            System.out.println("ALL PROPERTIES");
            System.getProperties().forEach((k, v) -> System.out.println(k + ":" + v));

            System.out.println("ALL ENVIRONMENT VARIABLES");
            System.getenv().forEach((k, v) -> System.out.println(k + ":" + v));
            
			//String fileSeparator = System.getProperty(FILE_SEPARATOR);
			String fileSeparator = "/";
            bootstrapPath = System.getProperty(ENV_BOOTSTRAPPATH);
            System.out.println("bootstrapPath:" + bootstrapPath);

            if (bootstrapPath != null) {
                String bootstrapFile = bootstrapPath + fileSeparator + BOOTSTRAPFILE;
				System.out.println("bootstrapFile " + bootstrapFile);
                URL bootstrapURL = new URL(bootstrapFile);  
                URLConnection bootstrapConnection = bootstrapURL.openConnection();
                bootstrapInStream = bootstrapConnection.getInputStream();                
                Assert.check(bootstrapInStream != null, "couldn't create instream for " + bootstrapFile);
            } else {           
                System.out.println("loading ConfigLocator class");
                //bootstrapInStream = Config.class.getClassLoader().getResourceAsStream(BOOTSTRAPFILE);
                URL bootstrapURL = Config.class.getClassLoader().getResource(BOOTSTRAPFILE);
                Assert.check(bootstrapURL != null, "bootstrapURL != null");
                bootstrapPath = bootstrapURL.getPath(); 
                // remove the bootstrap.properties file
                bootstrapPath = bootstrapPath.substring(0, bootstrapPath.lastIndexOf(BOOTSTRAPFILE)-1);
                // added URL protocol
                bootstrapPath = "file:///" + bootstrapPath;
                System.out.println("loaded bootstrappath " + bootstrapPath);
                bootstrapInStream = bootstrapURL.openStream(); 
                Assert.check(bootstrapInStream != null, "couldn't create instream for default bootstrap.properties");
            }
            
            EnvironmentPrinter.printEnvironment();

            Properties properties = new Properties();
            properties.load(bootstrapInStream);
            bootstrapInStream.close();
            return properties;
        } catch (IOException e) {
            System.out.print("couldn't find bootstrap config file2");
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
    
    /* 
     * Finds a config by name.
     * Config's are configured in the bootstrap.properties file. If the modelgenerated.bootstrap.path
     * property is set then the bootstrap.properties will be loaded from that path.
     * If not it will look on the classpath.
     *
     */
    static public synchronized <T extends Config> T findConfig(String configName) {
        Assert.check(configs.containsKey(configName), "missing config: *" + configName + "*");
        
        Config config = (Config)configs.get(configName);

        return (T)config;
    }

    static public String getBasePath() {
        if (basePath.equals(".")) {
            return bootstrapPath;             
        } else if (basePath.startsWith(".")) {
            return bootstrapPath + "/" + basePath;
        } else {
            return basePath;
        }
    }
    
    /* 
     * Initialize configuration information.
     */
    static public void init() {
        // Don't need to do anything here since we are using a static block to initialize.
        
    }

    
    
}
