package com.fantasy.impls.robot;

import com.fantasy.interfaces.Hand;
import com.fantasy.interfaces.Head;
import com.fantasy.interfaces.Leg;
import com.fantasy.interfaces.Robot;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public abstract class BaseModel implements Robot{

	@Autowired
	private Hand hand;
	@Autowired
	private Leg leg;
	@Autowired
	private Head head;

	public BaseModel() {
	}

	@Autowired
	public BaseModel(Hand hand, Leg leg, Head head) {
		super();
		this.hand = hand;
		this.leg = leg;
		this.head = head;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public Leg getLeg() {
		return leg;
	}

	public void setLeg(Leg leg) {
		this.leg = leg;
	}

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

}
