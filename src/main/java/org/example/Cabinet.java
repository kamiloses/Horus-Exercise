package org.example;

import java.util.List;
import java.util.Optional;

interface Cabinet {
    Optional<Folder>
    findFolderByName(String name);

    List<Folder> findFoldersBySize(String size);

    int count();
}