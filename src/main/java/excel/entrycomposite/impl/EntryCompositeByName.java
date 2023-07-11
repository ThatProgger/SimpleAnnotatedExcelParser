package excel.entrycomposite.impl;

import config.Config;
import config.params.NameParam;
import excel.entrycomposite.EntryComposite;
import model.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;

/**
 * Класс реализует интерфейс {@link EntryComposite} и позвоялет парсить полное имя  пользователя.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public class EntryCompositeByName implements EntryComposite<Row, Entry> {
    private Logger log = LogManager.getLogger();

    @Override
    public void invoke(Row cells, Entry entry) {
        NameParam nameParam = Config.getInstance().getNameParam();
        /*
        Получаем сконвертированное значение стобца в которой находится ключ
         */
        int numericCell = CellReference.convertColStringToIndex(nameParam.getLetter());

        /*
        Получаем полное имя пользователя
         */
        String employeeName = cells.getCell(numericCell).getStringCellValue();

        if(!(null == employeeName || employeeName.isBlank() || employeeName.isEmpty())){
            entry.setName(employeeName);
        } else {
            log.fatal("Ошибка в имени пользователя");
            throw new RuntimeException("Ошибка в имени пользователя");
        }

    }
}
