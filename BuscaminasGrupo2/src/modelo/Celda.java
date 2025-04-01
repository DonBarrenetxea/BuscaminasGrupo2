package modelo;

public class Celda {

    private boolean esMina;
    private boolean abierta;
    private int minasCerca;

    public Celda() {
        this.esMina = false;
        this.abierta = false;
        this.minasCerca = 0;
    }

    public boolean esMina() {
        return esMina;
    }

    public void colocarMina() {
        this.esMina = true;
    }

    public boolean esAbierta() {
        return abierta;
    }

    public void marcarComoAbierta() {
        this.abierta = true;
    }

    public int getMinasCerca() {
        return minasCerca;
    }

    public void setMinasCerca(int minasCerca) {
        this.minasCerca = minasCerca;
    }
}