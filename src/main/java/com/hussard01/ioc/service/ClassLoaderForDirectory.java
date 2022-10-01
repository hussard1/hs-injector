package com.hussard01.ioc.service;

import com.hussard01.ioc.exception.ClassNotLoadedException;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class ClassLoaderForDirectory implements ClassLoader {

  private static final String JAVA_CLASS_EXTENSION = ".class";

  @Override
  public Set<Class<?>> loadClasses(final String directory) {
    final Set<Class<?>> classes = new HashSet<>();

    try {
      final File file = new File(directory);
      final Set<String> classPaths = new HashSet<>();
      for (final File innerFile : file.listFiles()) {
        classPaths.addAll(this.scanDir(innerFile, ""));
      }
      for (final String classPath : classPaths) {
        classes.add(Class.forName(classPath));
      }
    } catch (final ClassNotFoundException e) {
      throw new ClassNotLoadedException(e.getMessage(), e);
    }
    return classes;
  }

  private Set<String> scanDir(final File file, String packageName) throws ClassNotFoundException {
    final Set<String> classPaths = new HashSet<>();
    if (file.isDirectory()) {
      packageName += file.getName() + ".";

      for (final File innerFile : file.listFiles()) {
        classPaths.addAll(this.scanDir(innerFile, packageName));
      }
    } else {
      if (!file.getName().endsWith(JAVA_CLASS_EXTENSION)) {
        return classPaths;
      }

      classPaths.add(packageName + file.getName().replace(JAVA_CLASS_EXTENSION, ""));
    }
    return classPaths;
  }
}
