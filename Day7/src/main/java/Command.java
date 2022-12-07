import java.util.Arrays;

public interface Command
{
    void execute(final FileSystem fileSystem);

    static Command parseCommand(final String[] command)
    {
        final String input = command[0];
        if (input.startsWith("cd"))
        {
            final String target = input.split(" ")[1];
            return new ChangeDirectory(target);
        }
        else if (input.startsWith("ls"))
        {
            final String[] outputs = Arrays.copyOfRange(command, 1, command.length);
            return new ListContent(outputs);
        }
        throw new IllegalArgumentException();
    }
}
