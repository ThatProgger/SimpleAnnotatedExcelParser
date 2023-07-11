package config.params;

import lombok.Data;

/**
 * Финальный класс содержит параметры для парсинга даты.
 * @author Mihail Dedyukhin
 * @since 1.0
 */
@Data
public class DateParam {
    private String letter;
    private String regexp;
    private String mask;
}
