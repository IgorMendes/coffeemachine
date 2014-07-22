package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;

import net.compor.frameworks.jcf.api.ComporFacade;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class MyCoffeeMachine extends ComporFacade implements CoffeeMachine {

	int totalCoin;
	private ComponentsFactory factory;
	
	ArrayList<Coin> moedas = new ArrayList<Coin>();

	public MyCoffeeMachine(ComponentsFactory factory) {

		this.factory = factory;
		factory.getDisplay().info("Insert coins and select a drink!");
	}

	public void insertCoin(Coin coin) throws CoffeeMachineException {

		try {
			totalCoin += coin.getValue();
			this.moedas.add(coin);
			factory.getDisplay().info(
					"Total: US$ " + totalCoin / 100 + "." + totalCoin % 100);

		} catch (NullPointerException e) {
			throw new CoffeeMachineException("erro");
		}
		
	
				
			}
			
	

	public void cancel() throws CoffeeMachineException {
		
		if (this.totalCoin == 0) {
			throw new CoffeeMachineException("Não houve moeda inserida");
		}

		if (this.moedas.size() > 0) {
			
			Coin[] reverso = Coin.reverse();
			this.factory.getDisplay().warn("Cancelling drink. Please, get your coins.");
			for (Coin re : reverso) {

				for (Coin aux : this.moedas) {
					if (aux == re) {
						this.factory.getCashBox().release(aux);
					}
				}
			}
			this.factory.getDisplay().info("Insert coins and select a drink!");
		}
	}
}
