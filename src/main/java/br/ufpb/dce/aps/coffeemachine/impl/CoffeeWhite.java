package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class CoffeeWhite extends CoffeeBlack {

	public CoffeeWhite(ComponentsFactory factory) {
		super(factory);
	}

	@Service
	public boolean verifyWhiteDrink() {
		if (!verifyBlackDrink()) {
			return false;
		}
		factory.getCreamerDispenser().contains(100);
		return true;

	}

	@Service
	public void releaseWhiteDrink() {
		releaseBlackDrink();
		this.factory.getCreamerDispenser().release(100);
	}
}