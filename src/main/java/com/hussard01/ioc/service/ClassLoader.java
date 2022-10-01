package com.hussard01.ioc.service;

import java.util.Set;

public interface ClassLoader {

  Set<Class<?>> loadClasses(String directory);
}
