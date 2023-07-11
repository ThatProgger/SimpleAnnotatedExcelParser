package configcreator;

import config.Config;

import java.io.File;

/**
 * Интерфейс позволяет создавать реализацию для создания конфигурационного класса
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public interface ConfigCreator {

    /**
     * Запускает процесс создания конфигурационного класса
     * @param file содержит путь к конфигурационному файлу расположенный на локальном диске
     * @return возращает временный конфигурационный класс.
     */
    public Config createConfig (File file);
}
