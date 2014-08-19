package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CoffeeWhiteSugar extends CoffeeWhite {

	public CoffeeWhiteSugar(ComponentsFactory factory) {
		super(factory);
	}

	@Service
	public boolean verifyWhiteSugarDrink() {
		if(!verifyWhiteDrink()){
			return false;
		}
		factory.getSugarDispenser().contains(5);
		return true;

	}

	@Service
	public void releaseWhiteSugarDrink() {
		releaseWhiteDrink();
		this.factory.getSugarDispenser().release(5);
	}
}