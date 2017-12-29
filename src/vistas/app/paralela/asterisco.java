package app.paralela;

import sun.misc.Queue;

import java.util.ArrayList;

public class asterisco {
    int []map_bruto;
    int [][]map;

    public ArrayList camino_directo;
    public ArrayList camino_total;
    int[] xx = { 0, 0, 1, -1 };
    int[] yy = { 1, -1, 0, 0 };
    public asterisco(){}

    public asterisco(int[][] map)
    {
        this.map = map;
        this.camino_directo = new ArrayList();
        this.camino_total = new ArrayList();
    }

    public asterisco(int[] map)
    {
        this.map_bruto = map;
        this.camino_directo = new ArrayList();
        this.camino_total = new ArrayList();
        obtener_datos(map[0], map[1]);
    }
    public void ini(int x, int y)
    {
        prev = new int[x][y][2];
        vis = new int[x][y];
        ans = new int[x][y];
        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
                vis[i][j] = 0;
                ans[i][j] = 10000;
            }
        }

    }

    public void obtener_datos(int x, int y)
    {
        map = new int[x][y];
        for (int i = 0; i < map_bruto[0] * map_bruto[1]; i++) {
            this.map[(i) / map_bruto[1]][(i) % map_bruto[1]] = map_bruto[i + 2];
        }
    }
    void mos(int Tx, int Ty)
    {
        //      MessageBox.Show(Tx+" "+Ty);
        if (prev[Tx][Ty][0] == -1 || prev[Tx][Ty][1] == -1)
        {
            camino_directo.add(new node(Tx, Ty));
            return;
        }
        mos(prev[Tx][Ty][0], prev[Tx][Ty][1]);
        camino_directo.add(new node(Tx, Ty));
    }

    boolean esta_visitado(node a)
    {
        if (vis[a.x][a.y] == 1) return true;
        return false;
    }
    int[][][]prev;
    int [][]ans;
    int [][]vis;
    boolean resuelto = false;
    public void resolver(node a,node b,int limitex,int limitey) {
        resolver(a.x, a.y, b.x, b.y, limitex, limitey);
    }
    public void resolver(int ix, int iy, int fx, int fy, int limitex, int limitey)
    {
        this.camino_directo = new ArrayList();
        this.camino_total = new ArrayList();
        ini(limitex, limitey);
        Queue<node> q=new Queue() ;

        node inicio = new node(ix, iy);
        node fin = new node(fx, fy);
        inicio.heuristico(fin);
        fin.heuristico(fin);
        q.enqueue(inicio);
        prev[ix][iy][0] = -1;
        prev[ix][iy][1] = -1;
        ans[ix][iy] = 0;
        while (!q.isEmpty())
        {
            //            un_momento();
            node top = null;
            try {
                top = q.dequeue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (esta_visitado(top)) continue;

            camino_total.add(top);
            vis[top.x][top.y] = 1;
            if (top.x == fin.x && top.y == fin.y) { resuelto = true; break; }
            for (int i = 0; i < 4; i++)
            {
                node sig = new node(top.x + xx[i], top.y + yy[i]);

                if (!sig.existe(limitex, limitey) || esta_visitado(sig) )
                    continue;

                int man = sig.heuristico(fin);

                if (ans[sig.x][sig.y] > ans[top.x][top.y])                {

                ans[sig.x][sig.y] = ans[top.x][top.y] + 1;
                sig.c = sig.c + ans[sig.x][sig.y]+map[sig.x][sig.y];
                q.enqueue(sig);

                prev[sig.x][sig.y][0] = top.x;
                prev[sig.x][sig.y][1] = top.y;

            }
            }
        }


        camino_directo.clear();
        if (resuelto) mos(fx, fy);
    }
}
