package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CoffeeWhite extends CoffeeBlack {

	public CoffeeWhite(ComponentsFactory factory) {
		super(factory);
	}

	@Service
	public boolean verifyWhiteDrink() {
		if (!verifyBlackDrink()) {
			return false;
		}
		if (!factory.getCreamerDispenser().contains(100)) {
			this.factory.getDisplay().warn(Messages.OUT_OF_CREAMER);
			return false;
		}
		
		return true;

	}

	@Service
	public void releaseWhiteDrink() {
		releaseBlackDrink();
		this.factory.getCreamerDispenser().release(100);
	}
}