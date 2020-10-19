public class LinkTiles
{
    NodeTile root;
    NodeTile current;
    
    public LinkTiles(){};

    public LinkTiles(Tile p)
    {
        root = new NodeTile(p);
        current = new NodeTile(p);
    }

    public void add(Tile p)
    {
        if(null == this.root)
        {
            root = new NodeTile(p);
            current = root;
        }
        else
        {
            NodeTile n = new NodeTile(p);
            current.addNext(n);
            current = n;
        }
    }

    public int lengthList()
    {
        int i = 0;
        NodeTile n = root;
        while(n != null)
        {
            i++;
            n = n.next;
        }
        return i;
    }

    public Tile getTile(int index)
    {
        int i = 0;
        NodeTile n = root;
        while(i<index && n.next != null)
        {
            n = n.next;
            i++;
        }
        return n.getter();  // Tile corresponding to the index
    }

    public void tester()
    {
        NodeTile n = root;
        while(n != null)
        {
            System.out.println(n.value);
            n = n.next;
        }
    }
}

class NodeTile
{
    Tile value;
    NodeTile next;

    public NodeTile(Tile value)
    {
        this.value = value;
    }

    public NodeTile(Tile value, NodeTile next)
    {
        this.value = value;
        this.next = next;
    }

    public void addNext(NodeTile n)
    {
        this.next = n;
    }

    public Tile getter()
    {
        return this.value;
    }
}
