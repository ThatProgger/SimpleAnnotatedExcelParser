package excel.impl;

import cli.CLI;
import config.Config;
import excel.entrycomposite.EntryProcessor;
import annotationprocessor.AnnotationParser;
import configchecker.ConfigChecker;
import configcreator.ConfigCreator;
import convertcollection.ConvertCollection;
import excel.ExcelParser;
import filecreator.FileCreator;
import model.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Этот класс расширяет абстрактрый класс {@link ExcelParser} с параметром {@link Entry} и реализует его абстрактные методы.
 * Является частью шаблона программирвоания "Шаблонный метод".
 *
 * @author Mikahil Dedyukhin
 * @since 1.0
 */
public class EmployeeExcelParser extends ExcelParser<Entry> {
    private Logger log = LogManager.getLogger();
    private Marker marker = MarkerManager.getMarker("Entry");

    public EmployeeExcelParser(String[] args, CLI cli, ConfigCreator configCreator, ConfigChecker configChecker, FileCreator fileCreator, AnnotationParser annotationParser, EntryProcessor entryProcessor, List<ConvertCollection> collectionList) {
        super(args, cli, configCreator, configChecker, fileCreator, annotationParser, entryProcessor, collectionList);
        log.debug("Аргументы объекта \"{}\" установлены", ExcelParser.class.getName());
    }

    @Override
    public Sheet refineSheet(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);
        log.debug("Выбран эксель лист с именем {}", sheet.getSheetName());
        return sheet;
    }

    @Override
    public Map<String, Integer> refineIndex(Sheet sheet) {
        Config config = Config.getInstance();
        Map<String, Integer> idx = new HashMap<>();

        if (config.getStart() < 0)
            config.setStart(0);
        if (config.getEnd() < 0)
            config.setEnd(0);

        if(config.getStart() > config.getEnd()){
            if(config.getEnd() == 0)
                config.setEnd(sheet.getPhysicalNumberOfRows());
            else {
                config.setStart(config.getEnd() - 1);

                if(config.getStart() < 0)
                    config.setStart(0);
            }
        }

        idx.put("start", config.getStart());
        idx.put("end", config.getEnd());
        return idx;
    }


    @Override
    public Entry create(Row row, EntryProcessor entryProcessor) {
        Entry entry = new Entry();
        entryProcessor.invoke(row, entry);
        log.debug(marker, entry.toString());
        return entry;
    }

    @Override
    public Set<Entry> filter(Set<Entry> collection) {
        String mask = Config.getInstance().getDateParam().getMask();
        List<String> ignores = Config.getInstance().getGroupParam().getIgnore();

        Set<Entry> temp = collection.stream().filter(entry -> {
            return Pattern.compile(mask).matcher(new SimpleDateFormat("yyyy-MM-dd").format(entry.getDate())).find();
        }).collect(Collectors.toSet());

        temp = temp.stream().filter(entry -> {
            return ignores.contains(entry.getGroup());
        }).collect(Collectors.toSet());

        return temp;
    }

    @Override
    public void annotationProcess(Workbook workbook, AnnotationParser annotationParser, ConvertCollection convertCollection) {
        Class clazz = convertCollection.convert().get(0).getClass();
        annotationParser.createNewSheet(workbook, clazz, convertCollection);
    }

}
