package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CoffeeWhiteSugar extends CoffeeBlack {

	public CoffeeWhiteSugar(ComponentsFactory factory) {
		super(factory);
	}

	@Service
	public boolean verifyWhiteSugarDrink() {
		if (!this.factory.getCupDispenser().contains(1)) {
			this.factory.getDisplay().warn(Messages.OUT_OF_CUP);
			return false;
		}
		if (!this.factory.getWaterDispenser().contains(80)) {
			this.factory.getDisplay().warn(Messages.OUT_OF_WATER);
			return false;
		}
		if (!this.factory.getCoffeePowderDispenser().contains(15)) {
			this.factory.getDisplay().warn(Messages.OUT_OF_COFFEE_POWDER);
			return false;
		}
		if (!factory.getCreamerDispenser().contains(20)) {
			this.factory.getDisplay().warn(Messages.OUT_OF_CREAMER);
			return false;
		}
		factory.getSugarDispenser().contains(100);
		return true;

	}

	@Service
	public void releaseWhiteSugarDrink() {
		this.factory.getCoffeePowderDispenser().release(15);
		this.factory.getWaterDispenser().release(80);
		this.factory.getCreamerDispenser().release(20);;
		factory.getSugarDispenser().release(100);
	}
}