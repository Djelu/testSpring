package com.fantasy.first.impls.sony;

import com.fantasy.first.interfaces.Leg;

import javax.inject.Named;

@Named
public class SonyLeg implements Leg {
	
	public void go(){
		System.out.println("Go to Sony!");
	}

}
