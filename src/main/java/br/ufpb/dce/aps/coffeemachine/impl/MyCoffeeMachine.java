package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.ComporFacade;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class MyCoffeeMachine extends ComporFacade implements CoffeeMachine{
	
	int totalcoin;
	private  ComponentsFactory factory;
	
	public MyCoffeeMachine(ComponentsFactory factory){
		
		this.factory = factory;
		factory.getDisplay().info("Insert coins and select a drink!");
	}

	public void insertCoin(Coin coin) {
		
		totalcoin += coin.getValue();
		factory.getDisplay().info("Total: US$ " + totalcoin / 100 + "." +totalcoin % 100);
		
		
	}

}
