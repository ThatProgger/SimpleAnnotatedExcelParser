package annotationprocessor;

import convertcollection.ConvertCollection;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Класс позволяет создавать реализции парсера аннотаций с последюущей записи в эксель документ.
 * @author Mikhail Dedyukhin
 * @since 1.0
 */


public interface AnnotationParser {
    /**
     * Создает новый лист в Эксель документе
     * @param workbook
     * @param claz
     * @param convertCollection
     */
    public void createNewSheet (Workbook workbook, Class claz, ConvertCollection convertCollection);
}
