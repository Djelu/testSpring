package com.fantasy.first.impls.robot;

import com.fantasy.first.interfaces.Hand;
import com.fantasy.first.interfaces.Head;
import com.fantasy.first.interfaces.Leg;
import com.fantasy.first.interfaces.Robot;

import javax.inject.Inject;

public abstract class BaseModel implements Robot{

	@Inject
	private Hand hand;
	@Inject
	private Leg leg;
	@Inject
	private Head head;

	public BaseModel() {
	}

	@Inject
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
