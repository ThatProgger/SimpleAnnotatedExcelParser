package model;

import lombok.Builder;
import lombok.Data;
import annotationprocessor.annotation.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 * Этот класс ялвяется счетчиком, сколько раз сотрудник отметился на пропускном пунке в течении определенного периода.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */

@SheetName(name = "Счетчик")
@SheetParams
@Data
@Builder
public class EmployeeVisits {
    @FieldName(name = "Ключ")
    @HeaderStyle
    @FieldStyle
    private String key;

    @FieldName(name = "ФИО")
    @HeaderStyle
    @FieldStyle(horizontal = HorizontalAlignment.LEFT)
    private String name;

    @FieldName(name = "Группа")
    @HeaderStyle
    @FieldStyle(horizontal = HorizontalAlignment.LEFT)
    private String group;

    @FieldName(name = "Счетчик")
    @HeaderStyle
    @FieldStyle
    @ColumnWith(width = 2000)
    private int visits;
}
