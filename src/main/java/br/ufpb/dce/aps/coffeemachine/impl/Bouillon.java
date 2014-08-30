package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class Bouillon extends CoffeeBlack{
	
	public Bouillon(ComponentsFactory factory) {
		super(factory);
	}
	
	@Service
	public boolean verifyBouillonDrink() {

		if (!this.factory.getCupDispenser().contains(1)) {
			this.factory.getDisplay().warn(Messages.OUT_OF_CUP);
			return false;
		}
		if (!this.factory.getWaterDispenser().contains(100)) {
			this.factory.getDisplay().warn(Messages.OUT_OF_WATER);
			return false;
		}
		if (!this.factory.getBouillonDispenser().contains(10)){
			this.factory.getDisplay().warn(Messages.OUT_OF_BOUILLON_POWDER);
			return false;
		}
		
		return true;
	}
	
	@Service
	public void releaseBouillonDrink() {
		
		//this.factory.getDisplay().info(Messages.MIXING);
		this.factory.getBouillonDispenser().release(10);
		this.factory.getWaterDispenser().release(100);

}
}
