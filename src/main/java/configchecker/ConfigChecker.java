package configchecker;

import config.Config;

/**
 * Интерфейс позволяет создать реализацию для проверки конфигурационного класса.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public interface ConfigChecker {

    /**
     * Запускает процесс провеки конфигурационного класса.
     * @param config временный конфигурационный класс,
     * @return true если конфигурационный класс коректный и false если конфигурационный класс имеет ошибки конфигурации
     */
    public boolean checkConfig (Config config);
}
