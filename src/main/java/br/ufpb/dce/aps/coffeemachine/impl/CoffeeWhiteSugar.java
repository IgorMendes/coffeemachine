package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class CoffeeWhiteSugar extends CoffeeBlack {

	public CoffeeWhiteSugar(ComponentsFactory factory) {
		super(factory);
	}

	@Service
	public boolean verifyWhiteSugarDrink() {
		if (!verifyBlackDrink()) {
			return false;
		}
		factory.getCreamerDispenser().contains(100);
		factory.getSugarDispenser().contains(100);
		return true;

	}

	@Service
	public void releaseWhiteSugarDrink() {
		releaseBlackDrink();
		this.factory.getCreamerDispenser().release(100);
		factory.getSugarDispenser().release(100);
	}
}