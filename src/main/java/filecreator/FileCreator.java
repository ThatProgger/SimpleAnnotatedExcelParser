package filecreator;

import java.io.File;

/**
 * Интерфейс позволяет создать реализации для создания объекта {@link File} по указанному пути.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public interface FileCreator {

    /**
     * Запускает проыес создания {@link File}
     * @param filename содержит путь к конфигурационному файлу расположенного на локальном диске
     * @return возращает объект {@link File}
     */
    public File createFile (String filename);

}
