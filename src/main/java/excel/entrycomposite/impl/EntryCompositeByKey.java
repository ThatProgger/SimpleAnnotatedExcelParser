package excel.entrycomposite.impl;

import config.Config;
import config.params.KeyParam;
import excel.entrycomposite.EntryComposite;
import model.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс реализует интерфейс {@link EntryComposite} и позвоялет парсить ключ пользователя.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public class EntryCompositeByKey implements EntryComposite<Row, Entry> {
    private Logger log = LogManager.getLogger();

    @Override
    public void invoke(Row cells, Entry entry) {
        KeyParam keyParam = Config.getInstance().getKeyParam();
        /*
        Получаем сконвертированное значение стобца в которой находится ключ
         */
        int numericCell = CellReference.convertColStringToIndex(keyParam.getLetter());

        /*
        Получаем паттерн по которому будет обрабатываться ключ
         */
        String regexp = keyParam.getRegexp();

        /*
        Компилируем  строку по шаблону.
         */
        Matcher matcher = Pattern.compile(regexp).matcher(cells.getCell(numericCell).getStringCellValue());

        if(matcher.find()){
            String key = matcher.group();
            entry.setKey(key);
        } else {
            log.fatal("Ошибка получения ключа по шаблону!");
            throw new RuntimeException("Ошибка получения ключа по шаблону!");
        }

    }
}
