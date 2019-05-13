package com.android.dimodule;

import com.android.dimodule.testinject.ComputerModule;
import dagger.Component;

//@Singleton
@Component(modules = ComputerModule.class)
public interface CarModuleComponent {

  void inject(CarEntity_Module entity);
}
