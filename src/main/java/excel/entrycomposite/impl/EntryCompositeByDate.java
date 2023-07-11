package excel.entrycomposite.impl;

import config.Config;
import config.params.DateParam;
import excel.entrycomposite.EntryComposite;
import model.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс реализует интерфейс {@link EntryComposite} и позвоялет парсить группу пользователя.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public class EntryCompositeByDate implements EntryComposite<Row, Entry> {
    private Logger log = LogManager.getLogger();

    @Override
    public void invoke(Row cells, Entry entry) {
        DateParam dateParam = Config.getInstance().getDateParam();
        /*
        Получаем сконвертированное значение стобца в которой находится ключ
         */
        int numericCell = CellReference.convertColStringToIndex(dateParam.getLetter());

        /*
        Компилируем строку по шаблону
         */
        Matcher matcher = Pattern.compile(dateParam.getRegexp()).matcher(cells.getCell(numericCell).getStringCellValue());

        if (matcher.find()) {
            String d = matcher.group();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            try {
                Date date = sdf.parse(d);
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                entry.setDate(date);
            } catch (ParseException e) {
                log.fatal("Неудалось конвертировать дату");
                throw new RuntimeException(e.getMessage());
            }
        } else {
            log.fatal("Неудалось получить дату");
            throw new RuntimeException("Неудалось получить дату");
        }


    }
}
