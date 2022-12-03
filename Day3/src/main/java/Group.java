public class Group
{
    private final Rucksack first;
    private final Rucksack second;
    private final Rucksack third;

    public Group(final Rucksack first, final Rucksack second, final Rucksack third)
    {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public int getBadgePriority()
    {
        return first.findCommon(second, third);
    }

}
