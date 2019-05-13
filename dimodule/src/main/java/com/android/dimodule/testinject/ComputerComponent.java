package com.android.dimodule.testinject;

import dagger.Component;

@Component
public interface ComputerComponent {

  void inject(ComputerEntity entity);

}
