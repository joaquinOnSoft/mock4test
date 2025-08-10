package com.openmock.util;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilTest {
    @Test
    public void testDeleteFile() {
        String fileName = "filename.txt";
        FileWriter myWriter;
        try {
            myWriter = new FileWriter(fileName);
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
        } catch (IOException e) {
            fail(e.getMessage());
        }

        assertTrue(FileUtil.isFile(fileName));
        FileUtil.deleteFile(fileName);
        assertFalse(FileUtil.isFile(fileName));
    }

    @Test
    public void testGetFileFromResources() {
        File f = FileUtil.getFileFromResources("user-mapping.properties");
        assertNotNull(f);
        assertTrue(f.exists());
    }


    @Test
    public void testIsFile() {
        String cwd = null;
        try {
            cwd = (new File(".")).getCanonicalPath();
        } catch (IOException e) {
            fail(e.getMessage());
        }

        assertFalse(FileUtil.isFile(cwd));
    }

    @Test
    public void getLocalizedCSVFromResources() {
        File csvUK = FileUtil.getLocalizedCSVFromResources(Locale.UK, "person/family-name");
        assertNotNull(csvUK);
        assertTrue(csvUK.getAbsolutePath().endsWith("family-name-en.csv"));

        File csvEsES = FileUtil.getLocalizedCSVFromResources(Locale.forLanguageTag("es-ES"), "person/family-name");
        assertNotNull(csvEsES);
        assertTrue(csvEsES.getAbsolutePath().endsWith("family-name-es-ES.csv"));

        //File doesn't existsFile does not exist
        File csvPrc = FileUtil.getLocalizedCSVFromResources(Locale.PRC, "person/family-name");
        assertNull(csvPrc);
    }

    @Test
    public void getLocalizedJSONFromResources() {
        File jsonEN = FileUtil.getLocalizedJSONFromResources(Locale.UK, "airline/Aegean-Airlines");
        assertNotNull(jsonEN);
        assertTrue(jsonEN.getAbsolutePath().endsWith("Aegean-Airlines-en.json"));
    }

}