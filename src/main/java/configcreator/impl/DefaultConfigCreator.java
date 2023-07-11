package configcreator.impl;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import config.Config;
import configcreator.ConfigCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Класс реализует интерфейс {@link ConfigCreator} и позвоялет создавать главный конфигурационный класс из временного.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public class DefaultConfigCreator implements ConfigCreator {
    private Logger log = LogManager.getLogger();

    @Override
    public Config createConfig(File file) {
       JsonReader jsonReader = null;
       FileReader fileReader = null;
       Gson gson = null;
       Config config = null;

        try {
            fileReader = new FileReader(file);
            log.info("Создан объект: \"{}\"", fileReader.getClass().getName());
            jsonReader = new JsonReader(fileReader);
            log.info("Создан объект: \"{}\"", jsonReader.getClass().getName());
            gson = new Gson();
            log.info("Создан объект: \"{}\"", gson.getClass().getName());
            config = gson.fromJson(jsonReader, Config.class);
            log.info("Создан временный объект: \"{}\"", config.getClass().getName());
        } catch (FileNotFoundException e) {
            log.fatal("Файл не найден");
            throw new RuntimeException(e.getMessage());
        } finally {
            gson = null;
            jsonReader = null;
            fileReader = null;
            System.gc();
        }


        return config;
    }
}
