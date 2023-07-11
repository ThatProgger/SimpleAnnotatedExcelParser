package filecreator.impl;

import filecreator.FileCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Класс реализауте интерфейс {@link FileCreator} и содержит логику для обработки входного аргумента.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public class DefaultFileCreator implements FileCreator {
    private Logger log = LogManager.getLogger();

    @Override
    public File createFile(String filename) {
        File file = null;

        if (null == filename || filename.trim().isEmpty() || filename.trim().isBlank()) {
            log.fatal("Путь к файлу указан некорректно.");
            throw new RuntimeException("Путь к файлу указан некорректно");
        }

        try {
            file = new File(filename);
        } catch (NullPointerException e) {
            log.fatal("Путь к файлу является нулевым");
            throw new RuntimeException(e.getMessage());
        }


        try {
            if (!file.exists()) {
                log.fatal("По указанному пути файл не обнаружен");
                throw new RuntimeException("По указанному пути файл не обнаружен");
            }
        } catch (SecurityException e) {
            log.fatal("Файл защищен от чтения");
            throw new RuntimeException(e.getMessage());
        }

        try {
            if (!file.isFile()) {
                log.fatal("Указанный путь не содержит файл");
                throw new RuntimeException("Указанные путь не содержит файл");
            }
        } catch (SecurityException e) {
            log.fatal("Файл защищен от чтения");
            throw new RuntimeException(e.getMessage());
        }


        try {
            if(!file.canRead()){
                log.fatal("Файл не может быть прочитан");
                throw new RuntimeException("Файл не может быть прочитан!");
            }
        } catch (SecurityException e) {
            log.fatal("Файл защищен от чтения");
            throw new RuntimeException(e.getMessage());
        }

        return file;
    }
}
