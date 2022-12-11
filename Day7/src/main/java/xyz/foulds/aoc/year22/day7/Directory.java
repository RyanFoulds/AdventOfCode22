package xyz.foulds.aoc.year22.day7;

import java.util.HashMap;
import java.util.Map;

public class Directory
{
    private final String name;
    private final Directory parent;
    private final Map<String, Directory> directoryMap = new HashMap<>();
    private final Map<String, Long> fileMap = new HashMap<>();

    public Directory(final String name, final Directory parent)
    {
        this.parent = parent;
        this.name = name;
    }

    public long getSize()
    {
        final long fileSize = fileMap.values().stream()
                                     .mapToLong(Long::longValue)
                                     .sum();
        final long directorySize = directoryMap.values().stream()
                                               .mapToLong(Directory::getSize)
                                               .sum();
        return fileSize + directorySize;
    }

    public long getSize(final Map<String, Long> directorySizeMap)
    {
        final long fileSize = fileMap.values().stream()
                                     .mapToLong(Long::longValue)
                                     .sum();
        final long dirSize = directoryMap.values().stream()
                                         .mapToLong(dir -> dir.getSize(directorySizeMap))
                                         .sum();

        final long totalSize = fileSize + dirSize;
        directorySizeMap.putIfAbsent(getFullPath(), totalSize);
        return totalSize;
    }

    public Directory getParent()
    {
        return parent;
    }

    public Directory getDirectory(final String name)
    {
        if ("..".equals(name))
        {
            return parent;
        }
        return directoryMap.get(name);
    }

    public void createChildDirectory(final String name)
    {
        directoryMap.computeIfAbsent(name, n -> new Directory(n, this));
    }

    public void createFile(final String name, final long size)
    {
        fileMap.putIfAbsent(name, size);
    }

    private String getFullPath()
    {
        return parent == null ? name : parent.getFullPath() + name + "/";
    }
}
