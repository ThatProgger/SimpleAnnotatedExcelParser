package annotationprocessor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация задает ширину ячейки.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnWith {
    /**
     * Задает ширину ячейки
     * @return 300 по умолчанию
     */
    public int width () default 300;
}
