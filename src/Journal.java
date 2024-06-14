import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Journal {
    private static final String JOURNAL_FILE = "journal.log";

    public void log(String message) {
        try (FileWriter fw = new FileWriter(JOURNAL_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(LocalDateTime.now() + " - " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
