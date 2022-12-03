import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Rucksack
{

    private final String input;
    private Set<Integer> common;
    private final Set<Integer> allPriorities;

    public Rucksack(final String input)
    {
        this.input = input.trim();

        allPriorities = this.input.chars()
                                  .map(Rucksack::priority)
                                  .boxed()
                                  .collect(Collectors.toSet());
    }

    private void initialiseCommon()
    {
        final int length = input.length();
        this.common = input.substring(0, length/2).chars()
                           .map(Rucksack::priority)
                           .boxed()
                           .collect(Collectors.toSet());

        final Collection<Integer> second = input.substring(length/2, length).chars()
                                                .map(Rucksack::priority)
                                                .boxed()
                                                .collect(Collectors.toSet());
        common.retainAll(second);
    }

    public int getTotalDuplicatePriority()
    {
        if (this.common == null)
        {
            initialiseCommon();
        }
        return common.stream().mapToInt(Integer::intValue).sum();
    }

    public static int priority(final int character)
    {
        if (character < 91)
        {
            return character - 38;
        }
        return character - 96;
    }

    final Set<Integer> getAllPriorities()
    {
        return new HashSet<>(allPriorities);
    }

    public int findCommon(final Rucksack other, final Rucksack another)
    {
        final Set<Integer> badgeCandidates = getAllPriorities();
        badgeCandidates.retainAll(other.getAllPriorities());
        badgeCandidates.retainAll(another.getAllPriorities());

        if (badgeCandidates.size() == 1)
        {
            return badgeCandidates.iterator().next();
        }

        throw new RuntimeException();
    }
}
