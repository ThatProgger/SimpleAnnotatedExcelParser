package annotationprocessor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация позвоялет задать дополнительные параметры листа.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SheetParams {

    /**
     * Задает авторазмер ячейки
     * @return true по усполчанию
     */
    public boolean autosize () default true;



    /**
     * Задает заморозку первой строки Эксель листа
     * @return true по умолчанию.
     */
    public boolean freeze () default true;
}
