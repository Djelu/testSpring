package com.fantasy.impls.toshiba;

import com.fantasy.interfaces.Hand;
import org.springframework.stereotype.Component;

import javax.inject.Named;

@Named
public class ToshibaHand implements Hand{
	
	public void catchSomething(){
		System.out.println("Catched from Toshiba!");
	}

}
