package annotationprocessor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация позвоялет задать имя листа.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SheetName {

    /**
     * Этот параметр указывает имя листа.
     * @return имя листа или пустую строку.
     */
    public String name () default "";
}
