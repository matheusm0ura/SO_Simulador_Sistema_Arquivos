import java.util.ArrayList;
import java.util.List;

public class Directory {
    private String name;
    private String path;
    private List<CustomFile> files;
    private List<Directory> directories;

    public Directory(String name, String path) {
        this.name = name;
        this.path = path;
        this.files = new ArrayList<>();
        this.directories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public List<CustomFile> getFiles() {
        return files;
    }

    public List<Directory> getDirectories() {
        return directories;
    }

    public void addFile(CustomFile file) {
        files.add(file);
    }

    public void addDirectory(Directory directory) {
        directories.add(directory);
    }

    public void removeFile(CustomFile file) {
        files.remove(file);
    }

    public void removeDirectory(Directory directory) {
        directories.remove(directory);
    }
}
