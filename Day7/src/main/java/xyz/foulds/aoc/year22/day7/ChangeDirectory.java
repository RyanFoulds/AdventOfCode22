package xyz.foulds.aoc.year22.day7;

public class ChangeDirectory implements Command
{
    private final String target;

    public ChangeDirectory(final String target)
    {
        this.target = target;
    }

    @Override
    public void execute(final FileSystem fileSystem)
    {
        if (fileSystem.getCurrentDirectory() == null && target.equals("/"))
        {
            fileSystem.setCurrentDirectory(new Directory(target, null));
        }
        else
        {
            fileSystem.setCurrentDirectory(fileSystem.getCurrentDirectory().getDirectory(target));
        }
    }
}
