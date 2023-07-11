package cli.impl;

import cli.CLI;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс реализует интерфейс {@link CLI} и позволяет создать логику обработки аргументов коммандной строки.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public class DefaultCli implements CLI {
    private Logger log = LogManager.getLogger();

    @Override
    public String parseArgumentsForConfigPath(String[] args) {
        log.debug("Выполняется разбор аргументов коммандной строки");
        String filename = null;

        Options options = new Options();
        log.debug("Создан объект: \"{}\"", options.getClass().getName());
        CommandLineParser commandLineParser = new DefaultParser();
        log.debug("Создан объект: \"{}\"", commandLineParser.getClass().getName());
        options.addOption("c", true, "Флаг указывает на путь к конфигурационному файлу");
        log.debug("Опция \"-c\" добавлена в список объекта \"{}\"", options.getClass().getName());

        try {
            CommandLine cmd = commandLineParser.parse(options, args);
            log.debug("Создан объект: \"{}\"", cmd.getClass().getName());
            if (cmd.hasOption("c")) {
                filename = cmd.getOptionValue("c");
                log.info("Путь к файлу по аргументу \"-c\": {}", filename);
            }
        } catch (ParseException e) {
            log.fatal(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return filename;
    }
}
