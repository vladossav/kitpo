package types;

import java.util.Comparator;

public interface UserType extends Comparator {
    String typeName(); // Имя типа
    Object create(); // Создает объект ИЛИ
    Object clone(); // Клонирует текущий
    Object readValue(); // Создает и читает объект
    Object parseValue(String ss); // Создает и парсит содержимое из строки
    Comparator getTypeComparator();
}
