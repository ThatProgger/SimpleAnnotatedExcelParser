package annotationprocessor.annotation;


import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация позволяет задать стиль для заголовка колонки
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HeaderStyle {

    /**
     * Позвояляет сделать текст жирным.
     * @return true по умолчанию
     */
    public boolean bold () default true;


    /**
     * Устанавливает позицию текста по вертикали.
     * @return CENTER по умолчанию
     */
    public VerticalAlignment vertical () default  VerticalAlignment.CENTER;

    /**
     * Утснавливает позицию текста по горизонтали
     * @return CENTER по умолчанию.
     */
    public HorizontalAlignment horizontal () default HorizontalAlignment.CENTER;


    /**
     * Устанавливает высоту текста заголовка
     * @return 200 по умолчанию
     */
    public short fontHeight () default 200;
}
