package com.fantasy.impls.sony;

import com.fantasy.interfaces.Head;
import org.springframework.stereotype.Component;

@Component
public class SonyHead implements Head{
	
	public void calc(){
		System.out.println("Thinking about Sony...");
	}

}
