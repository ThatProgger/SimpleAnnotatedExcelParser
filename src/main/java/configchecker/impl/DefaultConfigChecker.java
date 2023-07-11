package configchecker.impl;

import config.Config;
import configchecker.ConfigChecker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс реализует интерфейс {@link ConfigChecker} и позволяет создать логику для проверки конфигурационного класса.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public class DefaultConfigChecker implements ConfigChecker {
    private final Logger log = LogManager.getLogger();
    @Override
    public boolean checkConfig(Config config) {
        if(null == config.getKeyParam()){
            log.fatal("Нулевая ссылка на объект: \"{}\"", config.getKeyParam().getClass().getName());
            return false;
        }

        if(null == config.getNameParam()){
            log.fatal("Нулевая ссылка на объект: \"{}\"", config.getNameParam().getClass().getName());
            return false;
        }

        if(null == config.getGroupParam()){
            log.fatal("Нулевая ссылка на объект: \"{}\"", config.getGroupParam().getClass().getName());
            return false;
        }

        if(null == config.getDateParam()){
            log.fatal("Нулевая ссылка на объект: \"{}\"", config.getDateParam().getClass().getName());
            return false;
        }

        log.debug("Временный конфигурационный файл проверен.");

        Config configuration = Config.getInstance();
        log.debug("Создан объект: \"{}\"", config.getClass().getName());

        configuration.setStart(config.getStart());
        log.debug("Установлен параметр: \"{}\"", configuration.getStart());

        configuration.setEnd(config.getEnd());
        log.debug("Установлен параметр: \"{}\"", configuration.getEnd());

        configuration.setPathToRead(config.getPathToRead());
        log.debug("Установлен параметр: \"{}\"", configuration.getPathToRead());

        configuration.setPathToWrite(config.getPathToWrite());
        log.debug("Установлен параметр: \"{}\"", configuration.getPathToWrite());

        configuration.setKeyParam(config.getKeyParam());
        log.debug("Установлен параметр: \"{}\"", configuration.getKeyParam().getClass().getName());

        configuration.setNameParam(config.getNameParam());
        log.debug("Установлен параметр: \"{}\"", configuration.getNameParam().getClass().getName());

        configuration.setGroupParam(config.getGroupParam());
        log.debug("Установлен параметр: \"{}\"", configuration.getGroupParam().getClass().getName());

        configuration.setDateParam(config.getDateParam());
        log.debug("Установлен параметр: \"{}\"", configuration.getDateParam().getClass().getName());

        log.info("Конфигурационный класс создан");
        return true;
    }
}
