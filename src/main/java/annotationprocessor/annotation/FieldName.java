package annotationprocessor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация позвоялет задать имя поля
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldName {
    /**
     * Устанавливает новое имя поля.
     * @return новое имя поля или пустую строку.
     */
    public String name () default "";
}
