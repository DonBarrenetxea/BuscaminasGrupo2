package modelo;

public enum Dificultad {
FACIL(8,8,10),
MEDIO(16,16,40),
DIFICIL(16,30,99);

	private final int columnas;
	private final int filas;
	private final int minas;
	
Dificultad(int columnas, int filas, int minas) {
	this.columnas = columnas;
	this.filas=filas;
	this.minas=minas;
}

public int getColumnas() {
	return columnas;
}

public int getFilas() {
	return filas;
}

public int getMinas() {
	return minas;
}

}
