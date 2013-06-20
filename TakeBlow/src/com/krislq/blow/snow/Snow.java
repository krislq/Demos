package com.krislq.blow.snow;


/**
 * 
 * @author <a href="mailto:kris@krislq.com">Kris.lee</a>
 * @since Jun 20, 2013
 * @version 1.0.0
 */
public class Snow {
	Coordinate coordinate;
	int speed;
	
	public Snow(int x, int y, int speed){
		coordinate = new Coordinate(x, y);
		System.out.println("Speed:"+speed);
		this.speed = speed;
		if(this.speed == 0) {
			this.speed =1;
		}
	}
	
}
