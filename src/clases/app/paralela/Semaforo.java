/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.paralela;

/**
 *
 * @author Cesia
 */
public class Semaforo {

   protected long a_permisos;
public Semaforo()
{
a_permisos=0;
}
public Semaforo(long p_permisos)
{
a_permisos=p_permisos;
}
public synchronized void V()
{
++a_permisos;
notify();
}
public void P() throws InterruptedException
{
if(Thread.interrupted()) throw new InterruptedException();
synchronized (this)
{
try
{
while (a_permisos <=0)
wait();
--a_permisos;
}
catch (InterruptedException ie)
{
notifyAll();
throw ie;
}
}
}
}

