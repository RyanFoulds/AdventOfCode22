package xyz.foulds.aoc.year22.day16;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TunnelState
{
    private final String currentValveName;
    private final Set<String> openValveNames;

    public TunnelState(final String currentValveName,
                       final Collection<String> openValves)
    {
        this.currentValveName = currentValveName;
        this.openValveNames = new HashSet<>(openValves);
    }

    public TunnelState()
    {
        this.currentValveName = "AA";
        this.openValveNames = new HashSet<>();
    }

    public String getCurrentValveName()
    {
        return currentValveName;
    }

    public Set<String> getOpenValveNames()
    {
        return new HashSet<>(openValveNames);
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj) return true;
        if (obj== null) return false;
        if (!TunnelState.class.equals(obj.getClass())) return false;
        final TunnelState other = (TunnelState) obj;
        return currentValveName.equals(other.currentValveName)
            && this.openValveNames.equals(other.openValveNames);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(currentValveName, openValveNames);
    }
}
