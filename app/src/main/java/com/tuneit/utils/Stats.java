package com.tuneit.utils;

public class Stats {
	int partidasTotales, partidasGanadas;
	int[] partidasGeneroTotales, partidasGeneroGanadas;
	/*	0 = Jazz
	 *  1 = Soundtrack
	 *  2 = Otros
	 *  3 = Rap
	 *  4 = Pop
	 *  5 = Rock
	 * */
	
	public Stats(){
		this.partidasTotales = 0;
		this.partidasGanadas = 0;
		this.partidasGeneroTotales = new int[6];
		this.partidasGeneroGanadas = new int[6];
		
		for(int i = 0; i < this.partidasGeneroTotales.length; i++){
			partidasGeneroTotales[i] = 0;
			partidasGeneroGanadas[i] = 0;
		}
	}

	public Stats(int partidasTotales, int partidasGanadas, int[] partidasGeneroTotales, int[] partidasGeneroGanadas) {
		this.partidasTotales = partidasTotales;
		this.partidasGanadas = partidasGanadas;
		this.partidasGeneroTotales = partidasGeneroTotales;
		this.partidasGeneroGanadas = partidasGeneroGanadas;
	}
	
	public int getPartidasTotales() {
		return partidasTotales;
	}
	public void setPartidasTotales(int partidasTotales) {
		this.partidasTotales = partidasTotales;
	}
	public int getPartidasGanadas() {
		return partidasGanadas;
	}
	public void setPartidasGanadas(int partidasGanadas) {
		this.partidasGanadas = partidasGanadas;
	}
	public int[] getPartidasGeneroTotales() {
		return partidasGeneroTotales;
	}
	public void setPartidasGeneroTotales(int[] partidasGeneroTotales) {
		this.partidasGeneroTotales = partidasGeneroTotales;
	}
	public int[] getPartidasGeneroGanadas() {
		return partidasGeneroGanadas;
	}
	public void setPartidasGeneroGanadas(int[] partidasGeneroGanadas) {
		this.partidasGeneroGanadas = partidasGeneroGanadas;
	}
}
