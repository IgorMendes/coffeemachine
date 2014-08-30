package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Drink;
import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class Drinks extends Component {

	public Drinks() {
		super("Drinks");
	}

	@Service
	public boolean verifyDrinkType(Drink drink) {
		boolean tipoCondicao = false;
		
		
		
		if (drink.equals(Drink.BLACK)) {
			tipoCondicao = (Boolean) requestService("verifyBlackDrink");
		}

		if (drink.equals(Drink.WHITE)) {
			tipoCondicao = (Boolean) requestService("verifyWhiteDrink");
		}

		if (drink.equals(Drink.WHITE_SUGAR)) {
			tipoCondicao = (Boolean) requestService("verifyWhiteSugarDrink");
		}

		if (drink.equals(Drink.BLACK_SUGAR)) {
			tipoCondicao = (Boolean) requestService("verifyBlackSugarDrink");
		}
		if (drink.equals(Drink.BOUILLON)) {
			tipoCondicao = (Boolean) requestService("verifyBouillonDrink");
		}
		return tipoCondicao;
	}

	@Service
	public void selectDrinkType(Drink drink) {
		
		if (drink.equals(Drink.BOUILLON)) {
			requestService("releaseBouillonDrink");
		}
		
		if (drink.equals(Drink.BLACK)) {
			requestService("releaseBlackDrink");
		}

		if (drink.equals(Drink.BLACK_SUGAR)) {
			requestService("releaseBlackSugarDrink");
		}
		if (drink.equals(Drink.WHITE)) {
			requestService("releaseWhiteDrink");
		}

		if (drink.equals(Drink.WHITE_SUGAR)) {
			requestService("releaseWhiteSugarDrink");
		}
	}
}