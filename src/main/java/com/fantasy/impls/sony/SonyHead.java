package com.fantasy.impls.sony;

import com.fantasy.interfaces.Head;
import org.springframework.stereotype.Component;

import javax.inject.Named;

@Named
public class SonyHead implements Head{
	
	public void calc(){
		System.out.println("Thinking about Sony...");
	}

}
