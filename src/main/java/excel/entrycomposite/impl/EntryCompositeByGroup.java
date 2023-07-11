package excel.entrycomposite.impl;

import config.Config;
import config.params.GroupParam;
import excel.entrycomposite.EntryComposite;
import model.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;

/**
 * Класс реализует интерфейс {@link EntryComposite} и позвоялет парсить группу пользователя.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public class EntryCompositeByGroup implements EntryComposite<Row, Entry> {
    private Logger log = LogManager.getLogger();

    @Override
    public void invoke(Row cells, Entry entry) {
        GroupParam groupParam = Config.getInstance().getGroupParam();
        /*
        Получаем сконвертированное значение стобца в которой находится ключ
         */
        int numericCell = CellReference.convertColStringToIndex(groupParam.getLetter());

        /*
        Получаем группу пользователя
         */
       String group = cells.getCell(numericCell).getStringCellValue();
       if (!(null == group || group.isEmpty() || group.isBlank())){
           entry.setGroup(group);
       } else {
           log.fatal("Неудалось получить группу");
           throw new RuntimeException("Не удалось получить группу");
       }

    }
}
