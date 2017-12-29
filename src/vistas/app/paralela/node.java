package app.paralela;

public class node {
    public int x;
    public int y;
    public int valor;
    public int c;

    public node(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    public int heuristico(node fin)
    {
        c = Math.abs(fin.x - x) + Math.abs(fin.y - y);
        return c;
    }
    public boolean existe(int lx, int ly)
    {
        if (this.x >= lx || this.x < 0 || this.y >= ly || this.y < 0) return false;
        return true;
    }

    public int compareTo(node a)    {
        if (a.y == y && a.x == x) return 0;
        if (c < a.c) return -1;
        if (c > a.c) return 1;
        return 0;
    }

}
