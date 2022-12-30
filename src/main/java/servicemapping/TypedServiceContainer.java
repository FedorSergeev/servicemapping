package servicemapping;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Обертка над хранилищем обработчиков, распределенных по типам данных, которые принимают на вход
 */
public class TypedServiceContainer {
    private Map<Class<?>, Object> delegate = new HashMap<>();

    /**
     * Добавить сервис в хранилище. Здесь может осуществляться контроль соответствия типов.
     *
     * @param klass тип принимаемого на вход объекта
     * @param service сервис
     * @param <T>     параметризация входных данных для сервиса
     * @param <E>     параметризация выходных данных для сервиса
     */
    public <T,E> void put(Class<T> klass, Function<T,E> service) {
        delegate.put(klass, service);
    }

    /**
     * Получить экземпляр сервиса по типу входных данных
     * @param klass тип принимаемого на вход объекта
     * @param <T>     параметризация входных данных для сервиса
     * @param <E>     параметризация выходных данных для сервиса
     * @return сервис, специализирующийся на конкретном типе входящих объектов
     */
    public <T,E> Function<T,E> get(Class<T> klass) {
        return Function.class.cast(delegate.get(klass));
    }
}
