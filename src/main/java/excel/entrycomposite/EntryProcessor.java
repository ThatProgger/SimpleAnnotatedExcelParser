package excel.entrycomposite;

/**
 * Этот интерфейс расширяет интерфейс {@link EntryComposite} и позвоялет реализовать простой процессор для выполнения
 * шаблона программирования "Компоновщик"
 * @author Mikhail Dedyukhin
 * @since 1.0
 * @param <T> объект из которого будут извлекаться необходимые данные.
 * @param <K> объект в который будут записываться данные.
 */
public interface EntryProcessor <T, K> extends EntryComposite<T, K>{

    /**
     * Добавляет новый элемент в коллекцию
     * @param entryComposite класс реализованным интрефейсом {@link EntryComposite}
     */
    public void add (EntryComposite entryComposite);

    /**
     * Удаляет элемент из коллекции.
     * @param entryComposite класс реализованным интрефейсом {@link EntryComposite}
     */
    public void remove (EntryComposite entryComposite);

    /**
     * Очищает коллекцию.
     */
    public void clean ();
}
