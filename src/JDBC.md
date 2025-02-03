# Полное руководство по функционалу JDBC (Java Database Connectivity)

JDBC (Java Database Connectivity) — это API, предоставляемый Java для подключения, взаимодействия и управления базами данных. Он используется для выполнения операций, таких как создание, чтение, обновление и удаление данных (CRUD).

## 1. Что такое JDBC?
JDBC предоставляет стандартный интерфейс для взаимодействия Java-приложений с различными базами данных. Он является частью JDK (Java Development Kit) и находится в пакете `java.sql`.

Основные задачи JDBC:
- Подключение к базе данных.
- Выполнение SQL-запросов.
- Обработка результатов запросов.
- Управление транзакциями.

## 2. Основные компоненты JDBC
JDBC включает несколько ключевых компонентов:

### 2.1. DriverManager
`DriverManager` — это класс, который управляет списком драйверов баз данных. Он используется для установления соединения с базой данных.

Пример использования:
```java
Connection connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mydatabase", "username", "password"
);
```

### 2.2. Connection
`Connection` представляет соединение с базой данных. Оно используется для выполнения SQL-запросов и управления транзакциями.

Пример:
```java
Connection connection = DriverManager.getConnection(url, user, password);
```

### 2.3. Statement
`Statement` используется для выполнения SQL-запросов. Он имеет несколько подтипов:
- `Statement`: для выполнения простых SQL-запросов.
- `PreparedStatement`: для параметризованных запросов.
- `CallableStatement`: для вызова хранимых процедур.

Пример:
```java
Statement statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
```

### 2.4. ResultSet
`ResultSet` представляет результат выполнения SQL-запроса. Он предоставляет методы для навигации и извлечения данных из результатов.

Пример:
```java
while (resultSet.next()) {
    System.out.println("User ID: " + resultSet.getInt("id"));
    System.out.println("Username: " + resultSet.getString("username"));
}
```

### 2.5. SQLException
`SQLException` — это исключение, которое выбрасывается при ошибках работы с базой данных.

Пример обработки:
```java
try {
    Connection connection = DriverManager.getConnection(url, user, password);
} catch (SQLException e) {
    e.printStackTrace();
}
```

## 3. Жизненный цикл работы с JDBC

1. **Загрузка драйвера:**
   Загрузка драйвера для вашей базы данных.
   ```java
   Class.forName("com.mysql.cj.jdbc.Driver");
   ```

2. **Установление соединения:**
   Подключение к базе данных через `DriverManager`.
   ```java
   Connection connection = DriverManager.getConnection(url, user, password);
   ```

3. **Создание запроса:**
   Создание объекта `Statement`, `PreparedStatement` или `CallableStatement`.
   ```java
   Statement statement = connection.createStatement();
   ```

4. **Выполнение запроса:**
   Выполнение SQL-запросов (SELECT, INSERT, UPDATE, DELETE).
   ```java
   ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
   ```

5. **Обработка результатов:**
   Чтение данных из `ResultSet`.
   ```java
   while (resultSet.next()) {
       System.out.println(resultSet.getString("username"));
   }
   ```

6. **Закрытие ресурсов:**
   Закрытие `ResultSet`, `Statement` и `Connection` для предотвращения утечек памяти.
   ```java
   resultSet.close();
   statement.close();
   connection.close();
   ```

## 4. Основные классы и интерфейсы JDBC

| Класс/Интерфейс     | Описание                                                   |
|---------------------|-----------------------------------------------------------|
| `DriverManager`     | Управляет драйверами баз данных.                           |
| `Connection`        | Представляет соединение с базой данных.                   |
| `Statement`         | Используется для выполнения простых SQL-запросов.         |
| `PreparedStatement` | Предназначен для выполнения параметризованных запросов.   |
| `CallableStatement` | Используется для вызова хранимых процедур.                |
| `ResultSet`         | Содержит результаты выполнения SQL-запросов.              |
| `SQLException`      | Представляет ошибки, связанные с базой данных.            |

## 5. Типы драйверов JDBC

JDBC поддерживает четыре типа драйверов:

1. **Type-1 (JDBC-ODBC Bridge):**
   Использует мост для подключения к ODBC-драйверу. Устарел.

2. **Type-2 (Native-API):**
   Использует нативный код для взаимодействия с базой данных. Требует установки библиотек.

3. **Type-3 (Network Protocol):**
   Использует промежуточный сервер для связи с базой данных.

4. **Type-4 (Thin Driver):**
   Чистый Java-драйвер, взаимодействующий напрямую с базой данных. Рекомендуется для большинства проектов.

Пример использования Type-4 драйвера для MySQL:
```java
Connection connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mydatabase", "username", "password"
);
```

## 6. Транзакции в JDBC

Транзакции позволяют выполнять набор операций как единое целое. Если одна из операций завершится неудачно, вся транзакция откатывается.

Пример работы с транзакциями:
```java
Connection connection = DriverManager.getConnection(url, user, password);
connection.setAutoCommit(false);

try {
    Statement statement = connection.createStatement();
    statement.executeUpdate("INSERT INTO users (username) VALUES ('john')");
    statement.executeUpdate("INSERT INTO users (username) VALUES ('jane')");
    connection.commit();
} catch (SQLException e) {
    connection.rollback();
    e.printStackTrace();
} finally {
    connection.close();
}
```

## 7. Пул соединений (Connection Pooling)

В больших приложениях создание нового соединения для каждого запроса — дорогостоящая операция. Для повышения производительности используют пул соединений.

### Библиотеки для пула соединений:
- HikariCP
- Apache DBCP
- C3P0

Пример с использованием HikariCP:
```java
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:mysql://localhost:3306/mydatabase");
config.setUsername("username");
config.setPassword("password");
HikariDataSource dataSource = new HikariDataSource(config);

try (Connection connection = dataSource.getConnection()) {
    // Использование соединения
} catch (SQLException e) {
    e.printStackTrace();
}
```

## 8. Часто задаваемые вопросы

1. **Можно ли подключиться к нескольким базам данных одновременно?**
   Да, можно создать несколько объектов `Connection`, каждый из которых подключается к своей базе данных.

2. **Чем отличается `Statement` от `PreparedStatement`?**
    - `Statement` используется для выполнения простых SQL-запросов.
    - `PreparedStatement` поддерживает параметризованные запросы, улучшая безопасность и производительность.

3. **Как избежать SQL-инъекций?**
   Используйте `PreparedStatement` и избегайте динамической генерации SQL-запросов.

---

Продолжение книги будет основано на ваших следующих вопросах и запросах по JDBC.

