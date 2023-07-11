package config.params;

import lombok.Data;

/**
 * Финальный класс содержит параметры для парсинга ключа.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */

@Data
public final class KeyParam {
    private String letter;
    private String regexp;
}
