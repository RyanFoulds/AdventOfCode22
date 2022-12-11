package xyz.foulds.aoc.year22.day7;

public class FileSystem
{
    private Directory currentDirectory;

    public void setCurrentDirectory(final Directory newDirectory)
    {
        this.currentDirectory = newDirectory;
    }

    public Directory getCurrentDirectory()
    {
        return currentDirectory;
    }

    public void cdRoot()
    {
        while (currentDirectory.getParent() != null)
        {
            currentDirectory = currentDirectory.getParent();
        }
    }
}
