import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        
        String filePath = "app.log"; // Hamari file ka naam

        // Step 1: Read all lines from the log file into a Stream<String>
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            
            // Regex to extract log levels (INFO, WARN, ERROR)
            // Yeh pattern square brackets ke andar wale words ko dhoondhega
            Pattern logPattern = Pattern.compile("\\[(INFO|WARN|ERROR)\\]");

            // Step 2, 3 & 4 combined using Streams
            Map<String, Long> logCounts = lines
                // Step 2: Map each line to its log level using regex
                .map(line -> {
                    Matcher matcher = logPattern.matcher(line);
                    if (matcher.find()) {
                        return matcher.group(1); // Agar match mila toh level return karo (INFO/WARN/ERROR)
                    }
                    return "UNKNOWN"; // Agar koi level nahi mila
                })
                // Unknown lines ko filter out kar dete hain taaki count sahi aaye
                .filter(level -> !level.equals("UNKNOWN"))
                
                // Step 3: Collect results with Collectors.groupingBy to count occurrences
                .collect(Collectors.groupingBy(
                        level -> level, 
                        Collectors.counting()
                ));

            // Step 4: Print a summary table of log level counts
            System.out.println("===================================");
            System.out.println("       LOG LEVEL SUMMARY           ");
            System.out.println("===================================");
            System.out.printf("%-10s | %-10s%n", "Log Level", "Count");
            System.out.println("-----------------------------------");
            
            logCounts.forEach((level, count) -> {
                System.out.printf("%-10s | %-10d%n", level, count);
            });
            
            System.out.println("===================================");

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            System.err.println("Make sure 'app.log' file is in the root directory of the project.");
        }
    }
}