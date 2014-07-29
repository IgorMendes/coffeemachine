package br.ufpb.dce.aps.coffeemachine.impl;

import static org.mockito.Matchers.anyDouble;

import java.util.ArrayList;

import net.compor.frameworks.jcf.api.ComporFacade;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class MyCoffeeMachine extends ComporFacade implements CoffeeMachine {

	int totalCoin;
	private ComponentsFactory factory;
	private Drink drink;
	private Dispenser dispensa;

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
			this.factory.getDisplay().warn(
					"Cancelling drink. Please, get your coins.");
			for (Coin rev : reverso) {

				for (Coin aux : this.moedas) {
					if (aux == rev) {
						this.factory.getCashBox().release(aux);
					}
				}
			}
			this.factory.getDisplay().info("Insert coins and select a drink!");
		}
	}

	public void cancelSemIgredientes() throws CoffeeMachineException {

		if (this.totalCoin == 0) {
			throw new CoffeeMachineException("Não houve moeda inserida");
		}

		if (this.moedas.size() > 0) {

			Coin[] reverso = Coin.reverse();
			for (Coin rev : reverso) {

				for (Coin aux : this.moedas) {
					if (aux == rev) {
						this.factory.getCashBox().release(aux);
					}
				}
			}
			this.factory.getDisplay().info("Insert coins and select a drink!");
		}
	}

	public void select(Drink drink) {

		if (!this.factory.getCupDispenser().contains(1)) {
			this.factory.getDisplay().warn(Messages.OUT_OF_CUP);
			cancelSemIgredientes();
			return;
		} else {

			if (!this.factory.getWaterDispenser().contains(100)) {
				this.factory.getDisplay().warn(Messages.OUT_OF_WATER);
				cancelSemIgredientes();
				return;
			} else {

				if (!this.factory.getCoffeePowderDispenser().contains(100)) {
					this.factory.getDisplay().warn(
							Messages.OUT_OF_COFFEE_POWDER);
					cancelSemIgredientes();
					return;
				} else {

					if (drink == drink.BLACK_SUGAR
							&& !this.factory.getSugarDispenser().contains(100)) {

						this.factory.getDisplay().warn(Messages.OUT_OF_SUGAR);
						cancelSemIgredientes();
						return;

					}

					if (drink == drink.WHITE) {

						this.factory.getCreamerDispenser().contains(100);

						this.factory.getDisplay().info(Messages.MIXING);
						this.factory.getCoffeePowderDispenser().release(202);
						this.factory.getWaterDispenser().release(231);

						this.factory.getCreamerDispenser().release(100);
						this.factory.getDisplay().info(Messages.RELEASING);
						this.factory.getCupDispenser().release(1);
						this.factory.getDrinkDispenser().release(200);
						this.factory.getDisplay().info(Messages.TAKE_DRINK);
						this.factory.getDisplay().info(
								"Insert coins and select a drink!");

						this.moedas.clear();

					} else {

						this.factory.getDisplay().info(Messages.MIXING);
						this.factory.getCoffeePowderDispenser().release(202);
						this.factory.getWaterDispenser().release(231);

						if (drink == drink.BLACK_SUGAR) {

							this.factory.getSugarDispenser().release(100);

						}

						this.factory.getDisplay().info(Messages.RELEASING);
						this.factory.getCupDispenser().release(1);
						this.factory.getDrinkDispenser().release(200);
						this.factory.getDisplay().info(Messages.TAKE_DRINK);
						this.factory.getDisplay().info(
								"Insert coins and select a drink!");

						this.moedas.clear();

					}
				}

			}
		}
	}
}