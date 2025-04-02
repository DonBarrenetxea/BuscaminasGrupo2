package modelo;

public class Celda {

    private boolean esMina;
    private boolean abierta;
    private int minasCerca;
    private boolean banderaMarcada;

    public Celda() {
        this.esMina = false;
        this.abierta = false;
        this.minasCerca = 0;
        this.banderaMarcada=false;
    }

    public void setBanderaMarcada(boolean banderaMarcada) {
		this.banderaMarcada = banderaMarcada;
	}
    public boolean getBanderaMarcada() {
		return banderaMarcada;
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

    public void incrementarMinasCerca() {
        minasCerca++;
    }
}