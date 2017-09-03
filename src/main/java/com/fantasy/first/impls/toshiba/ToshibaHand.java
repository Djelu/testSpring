package com.fantasy.first.impls.toshiba;

import com.fantasy.first.interfaces.Hand;

import javax.inject.Named;

@Named
public class ToshibaHand implements Hand{
	
	public void catchSomething(){
		System.out.println("Catched from Toshiba!");
	}

}
