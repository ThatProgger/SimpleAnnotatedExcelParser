package config.params;

import lombok.Data;

import java.util.List;

/**
 * Финальный класс содержит параметры для парсинга группы.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Data
public final class GroupParam {
    private String letter;
    private List<String> ignore;
}
