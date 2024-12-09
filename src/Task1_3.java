import java.util.LinkedList; // импортируем для реализации хэш-таблицы
import java.util.Scanner;

/* Задание 1
Реализовать функции для работы со словарем на базе хеш-таблицы:
•	hashtabHash()
•	hashtabInit()
•	hashtabAdd ()
•	hashtabLookup()
•	hashtabDelete() */

class Task1_3 {
    public static class HashTable {
        // Поля данных
        private LinkedList<Entry>[] table; // создаём массив односвязных списков (элемент массива - ячейка хэш-таблицы)
        private int size; // размер хэш-таблицы (количество ячеек)

        // Внутренний класс для представления записи в хеш-таблице "ключ-значение"
        private static class Entry {
            // Поля данных
            String key; // строка, представляющая ключ
            String value; // строка, представляющая значение, связанное с ключом

            // Конструктор, инициализирующий поля
            Entry(String key, String value) {
                this.key = key;
                this.value = value;
            }
        }

        // Конструктор хеш-таблицы
        public HashTable(int size) {
            this.size = size; // устанавливаем размер хэш-таблицы
            table = new LinkedList[size]; // создаём массив с указанным количеством ячеек
            for (int i = 0; i < size; i++) { // инициализируем каждую ячейку массива
                table[i] = new LinkedList<>();
            }
        }

        /* Реализуем хэш-функцию, преобразующую входные данные в определенный хэш-код,
        привязывающий их к определенному индексу хеш-таблицы */
        private int hash(String key) {
            /* Применяем функцию hashCode, возвращающая хэш-код,
            получаем модуль этого значения (может быть отрицательным),
            воспроизводим сжатие хэш-кода в диапазон допустимых индексов массива */
            return Math.abs(key.hashCode()) % size;
        }

        // 1. Добавление элемента в хеш-таблицу
        public void hashtabAdd(String key, String value) {
            int index = hash(key); // вычисляем индекс для данного ключа с помощью метода hash
            for (Entry entry : table[index]) { // проходимся по массиву
                if (entry.key.equals(key)) { // если находим запись с соответствующим ключом
                    entry.value = value; // обновляем значения, если ключ уже существует
                    return;
                }
            }
            // Если ключ не найден
            table[index].add(new Entry(key, value)); // добавляем новую запись
        }

        // 2. Поиск элемента в хеш-таблице
        public String hashtabLookup(String key) {
            int index = hash(key); // вычисляем индекс для данного ключа с помощью метода hash
            for (Entry entry : table[index]) { // проходимся по массиву
                if (entry.key.equals(key)) { // если находим запись с соответствующим ключом
                    return entry.value; // возвращаем значение
                }
            }
            // Если ключ не найден
            return null; // возвращаем null
        }

        // 3. Удаление элемента из хеш-таблицы
        public void hashtabDelete(String key) {
            int index = hash(key);  // вычисляем индекс для данного ключа с помощью метода hash
            // Метод removeIf() в Java удаляет все элементы коллекции, которые удовлетворяют заданному предикату (фильтру)
            table[index].removeIf(entry -> entry.key.equals(key)); // удаление записи по ключу
        }

        // 4. Метод инициализации (в данном случае он уже реализован в конструкторе)
        public void hashtabInit(int size) {
            this.size = size;
            table = new LinkedList[size];
            for (int i = 0; i < size; i++) {
                table[i] = new LinkedList<>();
            }
        }

        /* Задание 2 Протестировать созданные функции –
        создать словарь из 10 элементов с разными ключами
        (полю value можно задавать произвольное значение).  */

        // Пример использования
        public static void main(String[] args) {
            HashTable hashTable = new HashTable(10);
            Scanner sc = new Scanner(System.in);

            // Ввод 10 элементов
            System.out.println("Введите 10 пар ключ-значение (ключ - строка, значение - строка):");
            for (int i = 0; i < 10; i++) {
                System.out.print("Введите ключ (строка): ");
                String key = sc.nextLine();
                System.out.print("Введите значение (строка): ");
                String value = sc.nextLine();
                hashTable.hashtabAdd(key, value);
            }

            /* Задание 3 Продемонстрировать поиск элемента по ключу и удаление элемента.  */

            // Поиск элемента по ключу
            System.out.print("\nВведите ключ для поиска значения: ");
            String searchKey = sc.nextLine();
            String result = hashTable.hashtabLookup(searchKey);
            if (result != null) {
                System.out.println("Значение для '" + searchKey + "': " + result);
            } else {
                System.out.println("Ключ '" + searchKey + "' не найден.");
            }

            // Удаление элемента по ключу
            System.out.print("\nВведите ключ для удаления элемента: ");
            String deleteKey = sc.nextLine();
            hashTable.hashtabDelete(deleteKey);
            System.out.println("Элемент с ключом '" + deleteKey + "' удален.");
        }
    }
}
