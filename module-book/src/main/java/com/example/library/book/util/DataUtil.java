package com.example.library.book.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataUtil {
	private static Log log = LogFactory.getLog(DataUtil.class);
	public static boolean isTestMode() {
		return "true".equalsIgnoreCase(System.getProperty("FUNCTIONAL_TEST_MODE"));
	}
	public static Properties getRuntimeProperties(String applicationName) {		
		
		String pathName = "";
		pathName = getRuntimePropertiesFilePathName(applicationName);
		FileInputStream propertyStream = null;
		try {
			if (pathName != null) {
				pathName = pathName +File.separator+applicationName+".properties";
				propertyStream = new FileInputStream(pathName);
			}
		}
		catch (FileNotFoundException e) {
			log.warn("Unable to find a runtime properties file at " + new File(pathName).getAbsolutePath());
		}
		
		try {
			if (propertyStream == null)
				throw new IOException("Could not find a runtime properties file named " + pathName
				        + " in the OpenMRS application data directory, or the current directory");
			
			Properties props = new Properties();
			DataUtil.loadProperties(props, propertyStream);
			propertyStream.close();
			log.info("Using runtime properties file: " + pathName);
			return props;
		}
		catch (Exception ex) {
			log.info("Got an error while attempting to load the runtime properties", ex);
			log
			        .warn("Unable to find a runtime properties file. Initial setup is needed. View the webapp to run the setup wizard.");
			return null;
		}
	}
	
	public static String getRuntimePropertiesFilePathName(String applicationName) {
		String pathName = "";
		
		// next look in the OpenMRS application data directory
		pathName = DataUtil.getApplicationDataDirectory() + pathName;
		log.debug("Attempting to look for property file from: " + pathName);
		if (new File(pathName).exists()) {
			return pathName;
		} else {
			log.warn("Unable to find properties file: " + pathName);
		}
		
		return null;
	}
	
	public static String getRuntimePropertiesFileNameInTestMode() {
		String filename = null;
		if (isTestMode()) {
			log.info("In functional testing mode. Ignoring the existing runtime properties file");
			filename = getOpenMRSVersionInTestMode() + "-test-runtime.properties";
		}
		return filename;
	}
	
	public static String getOpenMRSVersionInTestMode() {
		System.out.println("This coming");
		return System.getProperty("LIBRARY_VERSION", "library");
	}
	public static String getApplicationDataDirectory() {
        String filepath = null;

        // Optional: system property override
        if (System.getProperty("LIBRARY_APPLICATION_DATA_DIRECTORY") != null) {
            filepath = System.getProperty("LIBRARY_APPLICATION_DATA_DIRECTORY");
        } else {
            // Default locations
            String userHome = System.getProperty("user.home");
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                filepath = userHome + File.separator + "LibraryAppData";
            } else {
                filepath = userHome + File.separator + ".library";
            }
        }

        // Ensure folder exists
        File folder = new File(filepath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        return filepath;
    }
	/**
	 * This method is a replacement for Properties.load(InputStream) so that we can load in utf-8
	 * characters. Currently the load method expects the inputStream to point to a latin1 encoded
	 * file. <br/>
	 * NOTE: In Java 6, you will be able to pass the load() and store() methods a UTF-8
	 * Reader/Writer object as an argument, making this method unnecessary.
	 * 
	 * @deprecated use {@link #loadProperties(Properties, File)}
	 * @param props the properties object to write into
	 * @param input the input stream to read from
	 */
	public static void loadProperties(Properties props, InputStream input) {
		try {
			InputStreamReader reader = new InputStreamReader(input, "UTF-8");
			props.load(reader);
			reader.close();
		}
		catch (UnsupportedEncodingException uee) {
			log.error("Unsupported encoding used in properties file " + uee);
		}
		catch (IOException ioe) {
			log.error("Unable to read properties from properties file " + ioe);
		}
	}
	
	public static boolean checkString(String inputString,int size) {
		
		if(inputString == null || inputString.trim().isEmpty()) {
			return false;
			
		}else if(inputString.length()>size){
			return false;
		}
		return true;
	}
	
}
