package dev.listeners;

import dev.entitys.blocos.Bloco;
import dev.frames.criarMapas.Celula;

public interface ICriarMapaClicked {
	public Bloco click(Celula celula);
}
