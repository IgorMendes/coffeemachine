package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CoffeeBlackSugar extends CoffeeBlack {

	public CoffeeBlackSugar(ComponentsFactory factory) {
		super(factory);
	}

	@Service
	public boolean verifyBlackSugarDrink() {
		if (!verifyBlackDrink()) {
			return false;
		}
		if (!this.factory.getSugarDispenser().contains(100)) {
			this.factory.getDisplay().warn(Messages.OUT_OF_SUGAR);
			return false;
		}
		return true;
	}

	@Service
	public void releaseBlackSugarDrink() {
		releaseBlackDrink();
		factory.getSugarDispenser().release(100);
	}
}