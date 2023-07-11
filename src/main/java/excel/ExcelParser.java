package excel;

import annotationprocessor.AnnotationParser;
import cli.CLI;
import config.Config;
import configchecker.ConfigChecker;
import convertcollection.ConvertCollection;
import excel.entrycomposite.EntryProcessor;
import configcreator.ConfigCreator;
import filecreator.FileCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Этот абстрактный класс реализует шаблон программирования "Шаблонный метод".
 * Класс содержит несколько абстрактных методов позволяющие по-строчно разобрать эксель документ и создать обобщенный,
 * отфильтровать полученную коллекцию и записать коллекцию в новый Эксель документ.
 *
 * @param <T> - сущность хранимая в коллекции типа {@link java.util.HashSet}
 * @author Mikhail Dedyukhin
 * @since 1.0
 */

public abstract class ExcelParser<T> {
    private final Logger log = LogManager.getLogger();
    private Set<T> set = new HashSet<>();
    private String[] args;
    private CLI cli;
    private ConfigCreator configCreator;
    private ConfigChecker configChecker;
    private FileCreator fileCreator;
    private AnnotationParser annotationParser;
    private EntryProcessor entryProcessor;
    private List<ConvertCollection> convertCollectionList;

    public ExcelParser(String[] args, CLI cli, ConfigCreator configCreator, ConfigChecker configChecker, FileCreator fileCreator, AnnotationParser annotationParser, EntryProcessor entryProcessor, List<ConvertCollection> convertCollectionList) {
        this.args = args;
        this.cli = cli;
        this.configCreator = configCreator;
        this.configChecker = configChecker;
        this.fileCreator = fileCreator;
        this.annotationParser = annotationParser;
        this.entryProcessor = entryProcessor;
        this.convertCollectionList = convertCollectionList;
    }

    /**
     * Запускает процесс парсинга Эксель документа.
     */
    public void run() {
        log.info("Парсер запущен");
        if (configChecker.checkConfig(configCreator.createConfig(fileCreator.createFile(cli.parseArgumentsForConfigPath(args))))) {
            File fileForRead = new File(Config.getInstance().getPathToRead());
            log.debug("Создан объект: \"{}\"", fileForRead.getClass().getName());
            File fileForWrite = new File(Config.getInstance().getPathToWrite());
            log.debug("Создан объект: \"{}\"", fileForWrite.getClass().getName());


            /*
            Создаем объект для чтения Эксель дкоумента
             */
            try {
                Workbook workbook = WorkbookFactory.create(fileForRead);
                Sheet sheet = refineSheet(workbook);
                log.info("Количество записей в Эксель листе: {}", sheet.getPhysicalNumberOfRows());
                Map<String, Integer> index = refineIndex(sheet);
                log.info("Индексы: {}", index.toString());

                /*
                Построчно разбираем эксель документ и создаем новые объекты.
                 */

                for (int i = index.get("start").intValue(); i < index.get("end").intValue(); i++) {
                    T t = create(sheet.getRow(i), entryProcessor);
                    if (null == t)
                        continue;
                    set.add(t);
                }

                log.info("Размер коллекции после парсинга Эксель листа составляет: {}", set.size());
                set = filter(set);
                log.info("Размер коллекции после фильтрации: {}", set.size());

            } catch (IOException e) {
                log.fatal(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }


            /*
            Создаем объект для записи в Эксель документа
             */

            try (OutputStream fileOutput = new FileOutputStream(fileForWrite)) {
                Workbook workbook = new SXSSFWorkbook();
                for (ConvertCollection convertCollection : convertCollectionList) {
                    convertCollection.setCollection(set);
                    annotationProcess(workbook, annotationParser, convertCollection);
                }
                workbook.write(fileOutput);
                log.info("Книга успешно записана");
            } catch (FileNotFoundException e) {
                log.fatal(e.getMessage());
                throw new RuntimeException(e.getMessage());
            } catch (IOException e) {
                log.fatal(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }


        } else {
            log.fatal("Парсер остановлен из-за ошибки создания конфигурационного файла.");
        }
    }


    /**
     * Уточняет лист для чтения
     * @param workbook содержит один или несколько Эксель листов.
     * @return возращает эксель лист.
     */
    public abstract Sheet refineSheet (Workbook workbook);

    /**
     * Позволяет уточнить индексы для разбора эксель листа.
     * @param sheet содержит текущий лист Эксель документа.
     * @return карту индексов.
     */
    public abstract Map<String, Integer> refineIndex (Sheet sheet);

    /**
     * Читает строку и создает новый объект.
     *
     * @param row            текущая строка Эксель документа
     * @param entryProcessor позволяет упаковать объект в нужную форму.
     * @return новый объект.
     */
    public abstract T create(Row row, EntryProcessor entryProcessor);

    /**
     * Абстрактный метод позволяет отфильтровать коллекцию от нежелательных элементов.
     *
     * @param collection неотфильтрованная коллекция.
     * @return отфильтрованную коллекцию.
     */
    public abstract Set<T> filter(Set<T> collection);

    /**
     * TODO: make description
     *
     * @param workbook
     * @param annotationParser
     * @param convertCollection
     */
    public abstract void annotationProcess(Workbook workbook, AnnotationParser annotationParser, ConvertCollection convertCollection);


}
