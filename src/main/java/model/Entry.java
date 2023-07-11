package model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Этот финальный класс содержит базовую и необходиму сырую информацию о текущей строке.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */

@Data
public final class Entry {
    private String key;
    private String name;
    private String group;
    private Date date;


    @Override
    public String toString() {
        return String.format("%s :: %s :: %s :: %s", key, new SimpleDateFormat("yyyy-MM-dd").format(date), name, group);
    }
}
