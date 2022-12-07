public class ListContent implements Command
{
    private final String[] outputs;

    public ListContent(final String[] outputs)
    {
        this.outputs = outputs;
    }

    @Override
    public void execute(final FileSystem fileSystem)
    {
        final Directory directory = fileSystem.getCurrentDirectory();
        for (final String output : outputs)
        {
            if (output.startsWith("dir"))
            {
                directory.createChildDirectory(output.split(" ")[1]);
            }
            else if (Character.isDigit(output.charAt(0)))
            {
                final String[] file = output.split(" ");
                directory.createFile(file[1], Long.parseLong(file[0]));
            }
        }
    }
}
