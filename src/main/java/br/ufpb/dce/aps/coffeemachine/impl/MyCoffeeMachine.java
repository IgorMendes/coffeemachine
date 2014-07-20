package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.ComporFacade;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class MyCoffeeMachine extends ComporFacade implements CoffeeMachine {

	int totalCoin;
	private ComponentsFactory factory;

	public MyCoffeeMachine(ComponentsFactory factory) {

		this.factory = factory;
		factory.getDisplay().info("Insert coins and select a drink!");
	}

	public void insertCoin(Coin coin) throws CoffeeMachineException {

		try {
			totalCoin += coin.getValue();
			factory.getDisplay().info(
					"Total: US$ " + totalCoin / 100 + "." + totalCoin % 100);

		} catch (NullPointerException e) {
			throw new CoffeeMachineException("erro");
		}

	}

	public void cancel() throws CoffeeMachineException {

		if (this.totalCoin == 0) {
			throw new CoffeeMachineException("Nenhuma moeda inserida");
		}
		{

		}

	}
}
