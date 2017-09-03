package com.fantasy.first.impls.sony;

import com.fantasy.first.interfaces.Head;

import javax.inject.Named;

@Named
public class SonyHead implements Head{
	
	public void calc(){
		System.out.println("Thinking about Sony...");
	}

}
