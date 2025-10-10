package com.example.library.core.util;

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

import com.example.library.core.util.AppDataUtil;

public class AppDataUtil {
	private static Log log = LogFactory.getLog(AppDataUtil.class);
	
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
			AppDataUtil.loadProperties(props, propertyStream);
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
//		if (applicationName == null)
//			applicationName = "librarysystem";
//		
//		String defaultFileName = applicationName + "-runtime.properties";
//		String fileNameInTestMode = getRuntimePropertiesFileNameInTestMode();
		
		// first look in the current directory (that java was started from)
//		String pathName = fileNameInTestMode != null ? fileNameInTestMode : defaultFileName;
//		log.debug("Attempting to look for properties file in current directory: " + pathName);
//		if (new File(pathName).exists()) {
//			return pathName;
//		} else {
//			log.warn("Unable to find a runtime properties file at " + new File(pathName).getAbsolutePath());
//		}
//		
//		// next look from environment variable
//		String envVarName = applicationName.toUpperCase() + "_RUNTIME_PROPERTIES_FILE";
//		String envFileName = System.getenv(envVarName);
//		if (envFileName != null) {
//			log.debug("Atempting to look for runtime properties from: " + pathName);
//			if (new File(envFileName).exists()) {
//				return envFileName;
//			} else {
//				log.warn("Unable to find properties file with path: " + pathName + ". (derived from environment variable "
//				        + envVarName + ")");
//			}
//		} else {
//			log.info("Couldn't find an environment variable named " + envVarName);
//			if (log.isDebugEnabled())
//				log.debug("Available environment variables are named: " + System.getenv().keySet());
//		}
		
		// next look in the OpenMRS application data directory
		pathName = AppDataUtil.getApplicationDataDirectory() + pathName;
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
	
	//check input string with null or greater than 
	public static boolean checkInputValue(String inputString,int size) {
		
		if(inputString == null || inputString.trim().isEmpty()) {
			return false;
			
		}else if(inputString.length()>size){
			return false;
		}
		return true;
	}
	
	public static boolean checkInputValueLess(String inputString,int size) {
			
			if(inputString == null || inputString.trim().isEmpty()) {
				return false;
				
			}else if(inputString.length()<size){
				return false;
			}
			return true;
		}
	
	
	
	
	
}
