# Java Stream Log Analyzer

## 📌 Project Overview
This project is a simple log analyzer built using **Java 8 Streams API**. It reads a text file (`app.log`), extracts log levels (INFO, WARN, ERROR) using Regular Expressions (Regex), and counts the occurrences of each level using `Collectors.groupingBy`.

## 🚀 Features & Implementation Steps
- **Step 1:** Read all lines from a log file into a `Stream<String>`.
- **Step 2:** Map each line to its log level using a **Regex** pattern (`\\[(INFO|WARN|ERROR)\\]`).
- **Step 3:** Use **Collectors.groupingBy** and `Collectors.counting()` to aggregate the results.
- **Step 4:** Print a well-formatted summary table of the log level counts to the console.

## 🛠️ Technologies Used
- **Language:** Java (JDK 8 or above)
- **APIs:** `java.nio.file.Files`, `java.util.stream`, `java.util.regex`

## ⚙️ How to Run
1. Clone the repository.
2. Make sure the `app.log` file is in the root directory of the project.
3. Run the `Main.java` class.
4. View the summary table in your console.

## 📂 Project Structure
- `src/Main.java`: Contains the stream logic for reading, mapping, and counting.
- `app.log`: Sample log file for testing.