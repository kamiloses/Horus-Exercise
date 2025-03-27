package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileCabinet implements Cabinet {
    private List<Folder> folders;

    public FileCabinet(List<Folder> folders) {
        this.folders = folders;
    }


    @Override
    public Optional<Folder> findFolderByName(String name) {
        return findFolderByNameRecursive(folders, name);
    }

    private Optional<Folder> findFolderByNameRecursive(List<Folder> folders, String name) {
        for (Folder folder : folders) {
            if (folder.getName().equals(name)) {
                return Optional.of(folder);
            }
            if (folder instanceof MultiFolder) {
                Optional<Folder> found = findFolderByNameRecursive(((MultiFolder) folder).getFolders(), name);
                if (found.isPresent()) {
                    return found;
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        List<Folder> result = new ArrayList<>();
        findFoldersBySizeRecursive(folders, size, result);
        return result;
    }

    private void findFoldersBySizeRecursive(List<Folder> folders, String size, List<Folder> result) {
        for (Folder folder : folders) {
            if (folder.getSize().equals(size)) {
                result.add(folder);
            }
            if (folder instanceof MultiFolder) {
                findFoldersBySizeRecursive(((MultiFolder) folder).getFolders(), size, result);
            }
        }
    }

    @Override
    public int count() {
        return countRecursive(folders);
    }

    private int countRecursive(List<Folder> folders) {
        int count = 0;
        for (Folder folder : folders) {
            count++;
            if (folder instanceof MultiFolder) {
                count += countRecursive(((MultiFolder) folder).getFolders());
            }
        }

        return count;
}
}