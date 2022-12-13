package xyz.foulds.aoc.year22.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Node implements Comparable<Node>
{
    private final List<Node> childNodes;
    private Integer value;

    public Node(final String string)
    {
        childNodes = new ArrayList<>();

        if (!string.contains("[") && !string.contains(","))
        {
            this.value = Integer.parseInt(string);
            return;
        }
        
        final String unwrapped = string.substring(1, string.length() - 1);

        int openBraceCount = 0;
        final List<Integer> topLevelCommas = new ArrayList<>();
        for (int i = 0; i < unwrapped.length(); i++)
        {
            if (unwrapped.charAt(i) == '[') openBraceCount += 1;
            if (unwrapped.charAt(i) == ']') openBraceCount -= 1;
            if (unwrapped.charAt(i) == ',' && openBraceCount == 0) topLevelCommas.add(i);
        }

        for (int i = 0; i < topLevelCommas.size(); i++)
        {
            final int start = i == 0 ? 0 : topLevelCommas.get(i-1) + 1;
            final int end = topLevelCommas.get(i);
            final String childNode = unwrapped.substring(start, end);
            childNodes.add(new Node(childNode));
        }
        if (!topLevelCommas.isEmpty())
        {
            childNodes.add(new Node(unwrapped.substring(topLevelCommas.get(topLevelCommas.size() - 1) + 1)));
        }
        else if (!unwrapped.isEmpty())
        {

            childNodes.add(new Node(unwrapped));
        }
    }

    @Override
    public int compareTo(final Node other)
    {
        // Both terminal nodes.
        if (childNodes.isEmpty() && other.childNodes.isEmpty())
        {
            if (this.value == null && other.value == null) return 0;
            if (this.value == null) return -1;
            if (other.value == null) return 1;
            return this.value - other.value;
        }

        // Both list nodes.
        if (!childNodes.isEmpty() && !other.childNodes.isEmpty())
        {
            for (int i = 0; i < childNodes.size(); i++)
            {
                final int comparison = i >= other.childNodes.size() ? 1 : childNodes.get(i).compareTo(other.childNodes.get(i));
                if (comparison != 0) return comparison;
            }
            return childNodes.size() - other.childNodes.size();
        }
        
        // The other has children, this does not.
        if (childNodes.isEmpty())
        {
            if (value == null) return -1;
            final Node tempNode = new Node("[" + value + "]");
            return tempNode.compareTo(other);
        }

        // This one has children, the other does not.
        if (other.value == null) return 1;
        final Node otherTempNode = new Node("[" + other.value + "]");
        return this.compareTo(otherTempNode);
    }

    @Override
    public String toString()
    {
        if (childNodes.isEmpty()) return value == null ? "[]" : String.valueOf(value);
        else
        {
            return "[" + childNodes.stream()
                             .map(Node::toString)
                             .collect(Collectors.joining(",")) + "]";
        }
    }
}
