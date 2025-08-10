package com.openmock.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileUtil {

    protected static final Logger log = LogManager.getLogger(FileUtil.class);


    public static InputStream getLocalizedCSVStreamFromResources(Locale locale, String alias) throws IllegalArgumentException {
        List<String> suffixes = getLocalizedSuffix(locale);

        InputStream resource = null;
        for (String suffix : suffixes) {
            resource = FileUtil.class.getClassLoader().getResourceAsStream(alias + suffix + ".csv");
            if (resource != null) {
                break;
            }
        }

        return resource;
    }

    public static File getLocalizedJSONFromResources(Locale locale, String alias) throws IllegalArgumentException {
        return getLocalizedFileFromResources(locale, alias, ".json");
    }

    public static File getLocalizedCSVFromResources(Locale locale, String alias) throws IllegalArgumentException {
        return getLocalizedFileFromResources(locale, alias, ".csv");
    }

    public static File getLocalizedFileFromResources(Locale locale, String alias, String extension) throws IllegalArgumentException {
        File csv = null;
        List<String> suffixes = getLocalizedSuffix(locale);

        URL resource;
        for (String suffix : suffixes) {
            resource = FileUtil.class.getClassLoader().getResource(alias + suffix + extension);
            if (resource != null) {
                csv = new File(resource.getFile());
                break;
            }
        }

        return csv;
    }

    private static List<String> getLocalizedSuffix(Locale locale) {
        List<String> suffixes = new LinkedList<>();
        if (locale != null) {
            suffixes.add("-" + locale.getLanguage() + "-" + locale.getCountry());
            suffixes.add("-" + locale.getLanguage());
            suffixes.add("");
        }
        return suffixes;
    }

    /**
     * Get file from classpath, resources folder
     * SEE:
     * <a href="https://www.mkyong.com/java/java-read-a-file-from-resources-folder/">Java Read a file from resources folder</a>
     *
     * @param fileName
     * @return
     */
    public static File getFileFromResources(String fileName) throws IllegalArgumentException {
        URL resource = FileUtil.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }

    public static InputStream getStreamFromResources(String fileName) throws IllegalArgumentException {
        InputStream resource = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File '" + fileName + "' is not found!");
        } else {
            return resource;
        }
    }

    public static boolean isFile(String path) {
        boolean isFile = false;

        File f = new File(path);
        if (f.exists() && !f.isDirectory()) {
            isFile = true;
        }

        return isFile;
    }

    public static boolean deleteFile(String path) {
        boolean deleted = false;

        File f = new File(path);
        if (f.exists()) {
            deleted = f.delete();
        }

        return deleted;

    }

    public static Properties loadProperties(String propFileName) {
        Properties prop = null;

        log.debug("Properties file name: " + propFileName);
        InputStream propFile = FileUtil.getStreamFromResources(propFileName);

        try {
            prop = new Properties();
            log.debug("Loading");
            prop.load(propFile);
            log.debug("Loaded");
        } catch (FileNotFoundException e) {
            System.err.println("Properties file not found");
            e.getSuppressed();
        } catch (IOException e) {
            System.err.println("Properties file: " + e.getMessage());
        }

        return prop;
    }

    public static String getRandomFileName(String extension) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String strDate = dateFormat.format(GregorianCalendar.getInstance().getTime());

        int random = new Random().nextInt(10000);  // [0...10000]
        String strRandom = String.format("%05d", random);

        String name = strDate + strRandom;
        name += extension.startsWith(".") ? extension : "." + extension;

        return name;
    }
}