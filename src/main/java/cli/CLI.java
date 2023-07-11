package cli;

/**
 * Интерфейс позволяет создавать реализации для парсинга аргументов коммандной строки.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public interface CLI {

    /**
     * Запускает процесс парсинга аргументов коммандной строки.
     * @param args содержит коммандной строки
     * @return возращает путь к конфигурационному файлу.
     */
    public String parseArgumentsForConfigPath(String [] args);
}
