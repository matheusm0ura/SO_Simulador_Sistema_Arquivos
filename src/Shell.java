import java.io.IOException;
import java.util.Scanner;

public class Shell {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Simulador de Sistema de Arquivos!");

        while (true) {
            System.out.print("fs-shell> ");
            command = scanner.nextLine();
            String[] parts = command.split(" ");

            try {
                switch (parts[0]) {
                    case "copy":
                        FileSystemSimulator.copyFile(parts[1], parts[2]);
                        break;
                    case "delete":
                        FileSystemSimulator.deleteFile(parts[1]);
                        break;
                    case "rename":
                        FileSystemSimulator.renameFile(parts[1], parts[2]);
                        break;
                    case "mkdir":
                        FileSystemSimulator.createDirectory(parts[1]);
                        break;
                    case "rmdir":
                        FileSystemSimulator.deleteDirectory(parts[1]);
                        break;
                    case "renamedir":
                        FileSystemSimulator.renameDirectory(parts[1], parts[2]);
                        break;
                    case "list":
                        FileSystemSimulator.listFiles(parts[1]);
                        break;
                    case "touch":
                        FileSystemSimulator.createFile(parts[1]);
                        break;
                    case "exit":
                        System.out.println("Saindo do shell...");
                        return;
                    default:
                        System.out.println("Comando não reconhecido.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
