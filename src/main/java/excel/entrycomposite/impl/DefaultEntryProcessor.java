package excel.entrycomposite.impl;

import excel.entrycomposite.EntryProcessor;
import excel.entrycomposite.EntryComposite;
import model.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс реализует интерфейс {@link EntryProcessor} и позвоялет позвоялет обработать коллекцию с
 * объектами с реализованным интерфейсом {@link EntryComposite}
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public class DefaultEntryProcessor implements EntryProcessor<Row, Entry> {
    private Logger log = LogManager.getLogger();
    private List<EntryComposite> entryCompositeList = new LinkedList<>();



    @Override
    public void add(EntryComposite entryComposite) {
        entryCompositeList.add(entryComposite);
        log.info("Объект \"{}\" добавлен в коллекию. Размер коллекции составляет: {}", entryComposite.getClass().getName(), entryCompositeList.size());
    }

    @Override
    public void remove(EntryComposite entryComposite) {
        entryCompositeList.remove(entryComposite);
        log.info("Объект \"{}\" удалена из коллекции. Размер коллекции составляет: {}", entryComposite.getClass().getName(), entryCompositeList.size());
    }

    @Override
    public void clean() {
        entryCompositeList.clear();
        log.info("Коллекция полностью очищена");
    }

    @Override
    public void invoke(Row cells, Entry entry) {
        for (EntryComposite entryComposite : entryCompositeList){
            entryComposite.invoke(cells, entry);
        }
    }
}
