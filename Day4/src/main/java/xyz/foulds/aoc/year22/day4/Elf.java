package xyz.foulds.aoc.year22.day4;

public class Elf
{
    private final int startingSection;
    private final int endingSection;

    public Elf(final String range)
    {
        final String[] sections = range.split("-");
        this.startingSection = Integer.parseInt(sections[0]);
        this.endingSection = Integer.parseInt(sections[1]);
    }

    public boolean isContainedWithin(final Elf other)
    {
        return startingSection >= other.startingSection && endingSection <= other.endingSection;
    }

    public boolean overlapsWith(final Elf other)
    {
        return !(other.endingSection < startingSection || other.startingSection > endingSection);
    }
}
