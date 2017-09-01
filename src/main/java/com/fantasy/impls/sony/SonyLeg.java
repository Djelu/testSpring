package com.fantasy.impls.sony;

import com.fantasy.interfaces.Leg;
import org.springframework.stereotype.Component;

@Component
public class SonyLeg implements Leg {
	
	public void go(){
		System.out.println("Go to Sony!");
	}

}
