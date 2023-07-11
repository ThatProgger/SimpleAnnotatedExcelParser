package convertcollection.impl;

import convertcollection.ConvertCollection;
import model.EmployeeVisits;
import model.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Класс реализует интерфейс {@link ConvertCollection} и позволяет конвертировать одну коллецию в другую.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public class VisitsCollection implements ConvertCollection<EmployeeVisits, Set<Entry>>{
    private final Logger log = LogManager.getLogger();
    private Set<Entry> entries;

    @Override
    public void setCollection(Set<Entry> entries) {
        this.entries = entries;
        log.debug("Коллекция для конвертации добавлена");
    }

    @Override
    public List<EmployeeVisits> convert() {
        List<EmployeeVisits> employeeVisits = entries.stream().collect(Collectors.groupingBy(entry -> {
            return entry.getKey();
        })).entrySet().stream().map(entry -> {
            List<Entry> list = entry.getValue();
            return EmployeeVisits
                    .builder()
                    .key(entry.getKey())
                    .name(list.get(0).getName())
                    .group(list.get(0).getGroup())
                    .visits(list.size())
                    .build();
        }).collect(Collectors.toList());

        log.debug("Конвертация коллекции выполнена");
        return employeeVisits;
    }
}
