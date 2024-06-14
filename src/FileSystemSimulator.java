import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileSystemSimulator {
    private static final String FILE_SYSTEM_FILE = "filesystem.txt";
    private static Journal journal = new Journal();

    public static void createFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_SYSTEM_FILE));
        if (fileExists(filePath)) {
            throw new FileAlreadyExistsException("File already exists: " + filePath);
        }
        lines.add("FILE " + filePath);
        Files.write(Paths.get(FILE_SYSTEM_FILE), lines);
        journal.log("Created file " + filePath);
    }

    public static void copyFile(String sourcePath, String destPath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_SYSTEM_FILE));
        boolean found = false;
        for (String line : lines) {
            if (line.equals("FILE " + sourcePath)) {
                lines.add("FILE " + destPath);
                found = true;
                break;
            }
        }
        if (!found) throw new FileNotFoundException("Source file not found");
        Files.write(Paths.get(FILE_SYSTEM_FILE), lines);
        journal.log("Copied file from " + sourcePath + " to " + destPath);
    }

    public static void deleteFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_SYSTEM_FILE));
        boolean found = lines.removeIf(line -> line.equals("FILE " + filePath));
        if (!found) throw new FileNotFoundException("File not found");
        Files.write(Paths.get(FILE_SYSTEM_FILE), lines);
        journal.log("Deleted file " + filePath);
    }

    public static void renameFile(String oldPath, String newPath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_SYSTEM_FILE));
        boolean found = false;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals("FILE " + oldPath)) {
                lines.set(i, "FILE " + newPath);
                found = true;
                break;
            }
        }
        if (!found) throw new FileNotFoundException("File not found");
        Files.write(Paths.get(FILE_SYSTEM_FILE), lines);
        journal.log("Renamed file from " + oldPath + " to " + newPath);
    }

    public static void createDirectory(String dirPath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_SYSTEM_FILE));
        if (directoryExists(dirPath)) {
            throw new FileAlreadyExistsException("Directory already exists: " + dirPath);
        }
        lines.add("DIR " + dirPath);
        Files.write(Paths.get(FILE_SYSTEM_FILE), lines);
        journal.log("Created directory " + dirPath);
    }

    public static void deleteDirectory(String dirPath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_SYSTEM_FILE));
        boolean found = lines.removeIf(line -> line.startsWith("DIR " + dirPath) || line.startsWith("FILE " + dirPath + "/"));
        if (!found) throw new FileNotFoundException("Directory not found");
        Files.write(Paths.get(FILE_SYSTEM_FILE), lines);
        journal.log("Deleted directory " + dirPath);
    }

    public static void renameDirectory(String oldPath, String newPath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_SYSTEM_FILE));
        boolean found = false;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith("DIR " + oldPath)) {
                lines.set(i, lines.get(i).replace(oldPath, newPath));
                found = true;
            } else if (lines.get(i).startsWith("FILE " + oldPath + "/")) {
                lines.set(i, lines.get(i).replace(oldPath + "/", newPath + "/"));
                found = true;
            }
        }
        if (!found) throw new FileNotFoundException("Directory not found");
        Files.write(Paths.get(FILE_SYSTEM_FILE), lines);
        journal.log("Renamed directory from " + oldPath + " to " + newPath);
    }

    public static void listFiles(String dirPath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_SYSTEM_FILE));
            for (String line : lines) {
                if (line.startsWith("FILE " + dirPath + "/")) {
                    System.out.println("File: " + line.substring(5));
                } else if (line.startsWith("DIR " + dirPath + "/") && !line.equals("DIR " + dirPath)) {
                    System.out.println("Directory: " + line.substring(4));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean fileExists(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_SYSTEM_FILE));
        return lines.stream().anyMatch(line -> line.equals("FILE " + filePath));
    }

    private static boolean directoryExists(String dirPath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_SYSTEM_FILE));
        return lines.stream().anyMatch(line -> line.equals("DIR " + dirPath));
    }
}


