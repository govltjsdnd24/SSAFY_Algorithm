package com.ssafy.g_interface.abs;

public class VehicleTest {

  public static void main(String[] args) {
    // TODO: DieselSUV와 ElectricCar를 각각 한대씩 관리하는배열을 만들고 사용해보자.
	  Vehicle []vehicles= {
			  new ElectricCar(),
			  new DieselSUV(),
			  new HorseCart()
			  };
	  
	  for(Vehicle vehicle: vehicles) {
		  vehicle.addFuel();

		  vehicle.reportPosition();
	  }
    // END
  }

}
