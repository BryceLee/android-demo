package com.android.dimodule.testinject;

import com.android.dimodule.WheelModuleEntity;
import dagger.Module;
import dagger.Provides;

@Module
public class ComputerModule {

//  @Named("A")
  @Provides
//  @Singleton
  WheelModuleEntity provideWheelA() {
    return new WheelModuleEntity("A");
  }

//  @Named("B")
//  @Provides
//  WheelModuleEntity provideWheelB() {
//    return new WheelModuleEntity("B");
//  }

}
