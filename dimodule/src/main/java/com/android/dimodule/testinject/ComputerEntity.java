package com.android.dimodule.testinject;

import javax.inject.Inject;

public class ComputerEntity {

  @Inject
  PartEntity partEntity;

  public ComputerEntity() {
    DaggerComputerComponent.builder().build().inject(this);
  }

  public PartEntity getPartEntity() {
    return partEntity;
  }

}
