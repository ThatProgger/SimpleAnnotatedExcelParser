import annotationprocessor.AnnotationParser;
import annotationprocessor.impl.DefaultAnnotationParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import cli.CLI;
import cli.impl.DefaultCli;
import configchecker.ConfigChecker;
import configchecker.impl.DefaultConfigChecker;
import configcreator.ConfigCreator;
import configcreator.impl.DefaultConfigCreator;
import convertcollection.ConvertCollection;
import convertcollection.impl.VisitsCollection;
import excel.ExcelParser;
import excel.entrycomposite.EntryProcessor;
import excel.entrycomposite.impl.*;
import excel.impl.EmployeeExcelParser;
import filecreator.FileCreator;
import filecreator.impl.DefaultFileCreator;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private final static Logger log = LogManager.getLogger();

    public static void main(String[] args) {

        log.info("Программа запущена");
//Реализуем класс для парсинга коммандной строки
        CLI cli = new DefaultCli();
        log.info("Создан объект: \"{}\"", cli.getClass().getName());
//Реализуем класс для создания файла по аргументам коммандной строки
        FileCreator fileCreator = new DefaultFileCreator();
        log.info("Создан объект: \"{}\"", fileCreator.getClass().getName());
//Реализуем класс для создания конфигурационного класса
        ConfigCreator configCreator = new DefaultConfigCreator();
        log.info("Создан объект: \"{}\"", configCreator.getClass().getName());
//Реализуем класс для проверки конфигурационного класса
        ConfigChecker configChecker = new DefaultConfigChecker();
        log.info("Создан объект: \"{}\"", configChecker.getClass().getName());

//Реализуем класс для записи данных в Эксель лист
        AnnotationParser annotationParser = new DefaultAnnotationParser();

//Реализуем список для конвертации коллекций
        List<ConvertCollection> collectionList = new ArrayList<>();
        log.info("Создан объект: \"{}\"", collectionList.getClass().getName());
        collectionList.add(new VisitsCollection());


//Реализуем конструкцию для выполнения шаблона проектирования "Компоновщик".
        EntryProcessor entryProcessor = new DefaultEntryProcessor();
        log.info("Создан объект: \"{}\"", entryProcessor.getClass().getName());
        entryProcessor.add(new EntryCompositeByKey());
        entryProcessor.add(new EntryCompositeByName());
        entryProcessor.add(new EntryCompositeByGroup());
        entryProcessor.add(new EntryCompositeByDate());


        ExcelParser excelParser = new EmployeeExcelParser(args, cli, configCreator, configChecker, fileCreator, annotationParser, entryProcessor, collectionList);
        log.info("Создан объект: \"{}\"", excelParser.getClass().getName());


        excelParser.run();
    }
}
