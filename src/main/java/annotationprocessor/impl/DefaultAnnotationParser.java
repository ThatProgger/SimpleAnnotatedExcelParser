package annotationprocessor.impl;

import annotationprocessor.AnnotationParser;
import annotationprocessor.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import convertcollection.ConvertCollection;

import java.lang.reflect.Field;

/**
 * Класс реализует интерфейс {@link AnnotationParser} и создает реализацию по записи данных в Эксель лист.
 *
 * @author Mikhail Dedyuhin
 * @since 1.0
 */
public class DefaultAnnotationParser implements AnnotationParser {
    private final Logger log = LogManager.getLogger();

    @Override
    public void createNewSheet(Workbook workbook, Class clazz, ConvertCollection convertCollection) {
        SXSSFSheet sheet = createSheet(workbook, clazz);
        header(workbook, sheet, clazz);
        embedData(workbook, sheet, convertCollection);
        autosize(sheet, clazz);
        freezeTopRow(sheet, clazz);
    }

    /*
    Создает лист.
     */
    private SXSSFSheet createSheet(Workbook workbook, Class clazz) {
        if (clazz.isAnnotationPresent(SheetName.class)) {
            SheetName sheetName = (SheetName) clazz.getDeclaredAnnotation(SheetName.class);
            if (!"".equals(sheetName.name())) {
                String name = WorkbookUtil.createSafeSheetName(sheetName.name());
                log.debug("Имя листа: {}", name);
                return (SXSSFSheet) workbook.createSheet(name);
            }
        }

        String name = WorkbookUtil.createSafeSheetName(clazz.getCanonicalName());
        log.debug("Имя листа: {}", name);
        return (SXSSFSheet) workbook.createSheet(name);
    }

    /*
    Устанавливает заголовоки  листа.
     */
    private void header(Workbook workbook, SXSSFSheet sheet, Class clazz) {
        Row row = sheet.createRow(0);
        int counter = 0;

        for (Field field : clazz.getDeclaredFields()) {
            Cell cell = row.createCell(counter++);

            /*
            Устанавливаем стиль для ячейки
             */

            if (field.isAnnotationPresent(HeaderStyle.class)) {
                HeaderStyle headerStyle = field.getAnnotation(HeaderStyle.class);
                CellStyle cellStyle = workbook.createCellStyle();
                Font font = workbook.createFont();
                cellStyle.setAlignment(headerStyle.horizontal());
                cellStyle.setVerticalAlignment(headerStyle.vertical());
                font.setBold(headerStyle.bold());
                font.setFontHeight(headerStyle.fontHeight());
                cellStyle.setFont(font);
                cell.setCellStyle(cellStyle);
            }

            /*
            Устанавливаем заголовоки для ячейки
             */

            if (field.isAnnotationPresent(FieldName.class)) {
                FieldName fieldName = field.getAnnotation(FieldName.class);
                if (!"".equals(fieldName.name())) {
                    cell.setCellValue(fieldName.name());
                    log.debug("Установлено название заголовка: {}", fieldName.name());
                } else {
                    cell.setCellValue(field.getName());
                    log.debug("Установлено название заголовка: {}", field.getName());
                }
            } else {
                cell.setCellValue(field.getName());
                log.debug("Установлено название заголовка: {}", field.getName());
            }
        }
    }

    /*
    Записывает данные коллекции в эксель лист
     */
    public void embedData(Workbook workbook, SXSSFSheet sheet, ConvertCollection convertCollection) {
        int counter = 1;

        for (Object obj : convertCollection.convert()) {
            Row row = sheet.createRow(counter++);
            Class clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                Cell cell = row.createCell(i);
                Field field = fields[i];

                if (field.isAnnotationPresent(FieldStyle.class)) {
                    FieldStyle headerStyle = field.getAnnotation(FieldStyle.class);
                    CellStyle cellStyle = workbook.createCellStyle();
                    Font font = workbook.createFont();
                    cellStyle.setAlignment(headerStyle.horizontal());
                    cellStyle.setVerticalAlignment(headerStyle.vertical());
                    font.setBold(headerStyle.bold());
                    font.setFontHeight(headerStyle.fontHeight());
                    cellStyle.setFont(font);
                    cell.setCellStyle(cellStyle);
                }


                field.setAccessible(true);
                Object _obj = null;

                try {
                    _obj = field.get(obj);
                } catch (IllegalAccessException e) {
                    log.fatal(e.getMessage());
                    throw new RuntimeException(e.getMessage());
                }

                field.setAccessible(false);
                cell.setCellValue(String.valueOf(_obj));
            }
        }
    }


    private void freezeTopRow(SXSSFSheet sheet, Class clazz) {
        if (clazz.isAnnotationPresent(SheetParams.class)) {
            SheetParams sheetParams = (SheetParams) clazz.getAnnotation(SheetParams.class);
            if (sheetParams.freeze()) {
                sheet.createFreezePane(0, 1);
            }
        }
    }


    /*
    Авторазмер ячеек
     */

    private void autosize(SXSSFSheet sheet, Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (field.isAnnotationPresent(ColumnWith.class)) {
                ColumnWith columnWith = field.getAnnotation(ColumnWith.class);
                sheet.setColumnWidth(i, columnWith.width());
            } else {
                sheet.trackAllColumnsForAutoSizing();
                sheet.autoSizeColumn(i);
            }
        }
    }
}
