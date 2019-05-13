package com.android.dimodule;

import com.android.dimodule.testinject.ComputerModule;
import javax.inject.Inject;

public class CarEntity_Module {

  @Inject
//  @Named("A")
  WheelModuleEntity wheelEntity;
//  @Inject
//  @Named("B")
//  WheelModuleEntity wheelEntity2;

  public CarEntity_Module() {
//    DaggerCarModuleComponent.builder().carModule(new ComputerModule()).build().inject(this);
  }

  public WheelModuleEntity getWheelEntity() {
    return wheelEntity;
  }

//  public WheelModuleEntity getWheelEntity2() {
//    return wheelEntity2;
//  }

}
