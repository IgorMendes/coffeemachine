package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;

import net.compor.frameworks.jcf.api.ComporFacade;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class MyCoffeeMachine extends ComporFacade implements CoffeeMachine {

	private ComponentsFactory factory;
	private ArrayList<Coin> moedas;
	private int cedulas, centavos;
	boolean condicao = true;
	private final int PRECODOCAFE = 35;

	public MyCoffeeMachine(ComponentsFactory factory) {
		this.factory = factory;
		this.factory.getDisplay().info("Insert coins and select a drink!");
		this.cedulas = 0;
		this.centavos = 0;
		this.moedas = new ArrayList<Coin>();
		this.addComponents();
	}

	@Override
	protected void addComponents() {

		this.add(new CoffeeBlack(this.factory));
		this.add(new CoffeeBlackSugar(this.factory));
		this.add(new CoffeeWhite(this.factory));
		this.add(new CoffeeWhiteSugar(this.factory));
	}

	public void insertCoin(Coin coin) {
		if (coin == null) {
			throw new CoffeeMachineException("Error: Sem Moedas");
		}
		this.moedas.add(coin);
		this.cedulas += coin.getValue() / 100;
		this.centavos += coin.getValue() % 100;
		this.factory.getDisplay().info(
				"Total: US$ " + this.cedulas + "." + this.centavos);
	}

	public void cancel() {
		if (this.cedulas == 0 && this.centavos == 0) {
			throw new CoffeeMachineException("Não houve moeda inserida");
		}
		this.factory.getDisplay().warn(Messages.CANCEL);
		this.devolveMoedas();
	}

	private void limpaListaMoedas() {
		this.moedas.clear();
	}

	private void devolveMoedas() {
		for (Coin r : Coin.reverse()) {
			for (Coin aux : this.moedas) {
				if (aux == r) {
					this.factory.getCashBox().release(aux);
				}
			}
		}
		this.limpaListaMoedas();
		this.factory.getDisplay().info(Messages.INSERT_COINS);
	}

	private boolean retornaTroco(int troco) {
		for (Coin rev : Coin.reverse()) {
			if (rev.getValue() <= troco && factory.getCashBox().count(rev) > 0) {
				troco -= rev.getValue();
			}
		}
		boolean b = troco == 0;
		return !b;
	}

	private void releaseCoins(int troco) {
		for (Coin r : Coin.reverse()) {
			while (r.getValue() <= troco) {
				this.factory.getCashBox().release(r);
				troco -= r.getValue();
			}
		}
	}

	private int calculaTroco() {
		int count = 0;
		for (Coin rev : Coin.reverse()) {
			for (Coin aux : this.moedas) {
				if (aux == rev) {
					count += aux.getValue();
				}
			}
		}
		return count - this.PRECODOCAFE;
	}

	public void select(Drink drink) {

		if (calculaTroco() < 0) {
			this.factory.getDisplay().warn(Messages.NO_ENOUGHT_MONEY);
			this.devolveMoedas();
			return;
		}
		condicao = (Boolean) requestService("verifyDrinkType", drink);
		if (!condicao) {
			devolveMoedas();
			return;
		}
		if (retornaTroco(calculaTroco())) {
			devolveMoedas();
			return;
		}
		this.factory.getDisplay().info(Messages.MIXING);
		requestService("selectDrinkType", drink);
		requestService("releaseDrink");
		releaseCoins(calculaTroco());

		this.limpaListaMoedas();
		this.factory.getDisplay().info(Messages.INSERT_COINS);
	}
}