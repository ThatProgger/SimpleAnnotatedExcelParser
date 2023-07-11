package config;

import config.params.DateParam;
import config.params.GroupParam;
import config.params.KeyParam;
import config.params.NameParam;
import lombok.Data;

/**
 * Финальный конфигурационный класс содержит в себе настройки для парсинга и записи Эксель документа.
 * Класс реализует шаблон проектировать "Singleton"
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */

@Data
public final class Config {
    private static Config config;
    private int start;
    private int end;
    private String pathToRead;
    private String pathToWrite;
    private KeyParam keyParam;
    private NameParam nameParam;
    private GroupParam groupParam;
    private DateParam dateParam;


    private Config() {
    }

    /**
     * Если конфигурационный файл имеет нулевую ссылку, то создается новый объект и возращается ссылка.
     * Если ссылка на объект уже создана, объект не создается и возращается ссылка на текущий объект.
     * @return ссылка на объект.
     */
    public static Config getInstance() {
        if (null == config)
            config = new Config();
        return config;
    }

}
