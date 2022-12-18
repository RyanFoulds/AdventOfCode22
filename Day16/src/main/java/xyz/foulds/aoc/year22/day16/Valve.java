package xyz.foulds.aoc.year22.day16;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Valve
{
    private final List<String> leadsToValves;
    private final String name;
    private final int flowRate;

    private List<Valve> valves;
    public Valve(final String string)
    {
        final String[] split = string.split(";");
        this.name = split[0].substring(6, 8);
        this.flowRate = Integer.parseInt(split[0].substring(23));
        this.leadsToValves = Arrays.stream(split[1].substring(23)
                                                   .split(","))
                                   .map(String::trim)
                                   .collect(Collectors.toList());
    }

    public String getName()
    {
        return name;
    }

    public int getTotalFlow(final int minutesRemaining)
    {
        return minutesRemaining * flowRate;
    }

    public void setValves(final Map<String, Valve> map)
    {
        valves = leadsToValves.stream()
                              .map(map::get)
                              .collect(Collectors.toList());

        // preprocess the graph, to cut out intermediate steps? give each non-zero valve an edge weight and somehow decrement the moves counter by that weight each time.
    }

    public List<Valve> getNextValves()
    {
        return Collections.unmodifiableList(valves);
    }

    public int getFlowRate()
    {
        return flowRate;
    }
}
