import annotationprocessor.annotation.FieldName;
import annotationprocessor.annotation.HeaderStyle;
import annotationprocessor.annotation.SheetName;
import model.EmployeeVisits;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class AnnotationParserTest {

    @Test
    public void getAnnotationTest (){
        Class clazz = EmployeeVisits.class;

        Assertions.assertTrue(clazz.isAnnotationPresent(SheetName.class));
        Field [] fields = clazz.getDeclaredFields();

        for (Field field : fields){
            Assertions.assertTrue(field.isAnnotationPresent(FieldName.class));
            Assertions.assertTrue(field.isAnnotationPresent(HeaderStyle.class));
        }
    }
}
