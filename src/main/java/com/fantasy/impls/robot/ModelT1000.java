package com.fantasy.impls.robot;

import com.fantasy.impls.enums.ColorStyle;
import com.fantasy.interfaces.Hand;
import com.fantasy.interfaces.Head;
import com.fantasy.interfaces.Leg;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import javax.persistence.NamedEntityGraph;


@Configuration
//@ComponentScan(
//        basePackages = "com.fantasy",
//        includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "/*.*Pool*"))
@Named(value = "t1000")
public class ModelT1000 extends BaseModel/* implements InitializingBean, DisposableBean*/{

	private ColorStyle color;
	private int year;
	private boolean soundEnabled;

	@Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ModelT1000 beanModel1(){
        return new ModelT1000();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ModelT1000 beanModel2(){
        return new ModelT1000(ColorStyle.BLACK, 2005, true);
    }

	public ModelT1000() {
	}

	public ModelT1000(Hand hand, Leg leg, Head head) {
		super(hand, leg, head);
	}

	public ModelT1000(ColorStyle color, int year, boolean soundEnabled) {
		this.color = color;
		this.year = year;
		this.soundEnabled = soundEnabled;
	}

	public ModelT1000(Hand hand, Leg leg, Head head, ColorStyle color, int year, boolean soundEnabled) {
		super(hand,leg,head);

		this.color = color;
		this.year = year;
		this.soundEnabled = soundEnabled;
	}

	public void action() {
        System.out.println();
        getHand().catchSomething();
		getLeg().go();
		getHead().calc();
	}

	public void dance() {
		System.out.println("T1000 is dancing!");
	}

	public void printPars() {
		System.out.println("color: " + color);
		System.out.println("year: " + year);
		System.out.println("can play sound: " + soundEnabled);
	}

	public ColorStyle getColor() {
		return color;
	}

	public void setColor(ColorStyle color) {
		this.color = color;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isSoundEnabled() {
		return soundEnabled;
	}

	public void setSoundEnabled(boolean soundEnabled) {
		this.soundEnabled = soundEnabled;
	}

	public void destroy() throws Exception {
		System.out.println("destroy");
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("init");
	}
}
