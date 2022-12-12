package xyz.foulds.aoc.year22.day12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Graph
{
    private final int[][] elevationMap;
    private final Map<Integer, Node> nodes;
    private int targetId;
    private int startId;

    public Graph(final String[] elevations)
    {
        nodes = new HashMap<>();
        elevationMap = new int[elevations[0].length()][elevations.length];
        for (int y = 0; y < elevations.length; y++)
        {
            for (int x = 0; x < elevations[y].length(); x++)
            {
                elevationMap[x][y] = calculateHeight(elevations[y].charAt(x));
                if (elevations[y].charAt(x) == 'E') targetId = calculateId(x, y);
                if (elevations[y].charAt(x) == 'S') startId = calculateId(x, y);

                final Node node = new Node(elevationMap[x][y]);
                nodes.put(calculateId(x, y), node);
            }
        }
        connectTheNodes();
    }
    private void connectTheNodes()
    {
        for (int x = 0; x < elevationMap.length; x++)
        {
            for (int y = 0; y < elevationMap[x].length; y++)
            {
                final Node node = nodes.get(calculateId(x, y));
                if (x != 0)
                {
                    node.addDestinationNode(nodes.get(calculateId(x-1, y)));
                }
                if (x < elevationMap.length - 1 )
                {
                    node.addDestinationNode(nodes.get(calculateId(x+1, y)));
                }
                if (y != 0)
                {
                    node.addDestinationNode(nodes.get(calculateId(x, y-1)));
                }
                if (y < elevationMap[x].length - 1 )
                {
                    node.addDestinationNode(nodes.get(calculateId(x, y+1)));
                }
            }
        }
    }

    public int findShortestPathLengthFromStartToEnd()
    {
        final Node startingNode = nodes.get(startId);
        final Node targetNode = nodes.get(targetId);
        return doDijkstra(startingNode,
                          false,
                          node -> node.equals(targetNode));
    }

    public int findShortestPathLengthFromHighestToLowestPoint()
    {
        return doDijkstra(nodes.get(targetId), true, node -> node.getHeight() == 0);
    }

    private int doDijkstra(final Node startNode, final boolean reverse, final Function<Node, Boolean> earlyExitCondition)
    {
        // Map Nodes to the shortest distances to those nodes.
        final Map<Node, Integer> distances = new HashMap<>(nodes.size());
        distances.put(startNode, 0);

        final Set<Node> nodesToVisit = new HashSet<>(nodes.size());
        final Set<Node> settledNodes = new HashSet<>(nodes.size());
        nodesToVisit.add(startNode);

        while (!nodesToVisit.isEmpty())
        {
            final Node currentNode = getClosestNode(nodesToVisit, distances);
            nodesToVisit.remove(currentNode);
            settledNodes.add(currentNode);

            if (earlyExitCondition.apply(currentNode))
            {
                return distances.get(currentNode);
            }

            for (final Node next : currentNode.getNodes(reverse))
            {
                if (!settledNodes.contains(next))
                {
                    distances.merge(next, distances.get(currentNode) + 1, Math::min);
                    nodesToVisit.add(next);
                }
            }
        }
        throw new RuntimeException("No path exists!");
    }

    private static Node getClosestNode(final Set<Node> nodes, final Map<Node, Integer> distances)
    {
        int distance = Integer.MAX_VALUE;
        Node closestNode = null;
        for (final Node node: nodes)
        {
            final int nodeDistance = distances.get(node);
            if (nodeDistance < distance)
            {
                closestNode = node;
                distance = nodeDistance;
            }
        }
        return closestNode;
    }
    private static int calculateId(final int x, final int y)
    {
        return (x + y) * (x + y + 1) / 2 + x;
    }
    private static int calculateHeight(final char elevation)
    {
        if ('E' == elevation)
        {
            return calculateHeight('z');
        }
        else if ('S' == elevation)
        {
            return calculateHeight('a');
        }
        else
        {
            return elevation - 97;
        }
    }
}
