package com.hussard01.ioc.service;

import com.hussard01.ioc.exception.ClassNotLoadedException;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassLoaderForJarFile implements ClassLoader {
  public Set<Class<?>> loadClasses(final String directoryName) {
    final Set<Class<?>> classes = new HashSet<>();
    try (final JarFile jarFile = new JarFile(directoryName)) {
      final Enumeration<JarEntry> entries = jarFile.entries();
      while (entries.hasMoreElements()) {
        final JarEntry jarEntry = entries.nextElement();
        if (jarEntry.isDirectory() || jarEntry.getName().endsWith(".class")) {
          continue;
        }
        classes.add(Class.forName(jarEntry.getName()));
      }
    } catch (final IOException | ClassNotFoundException e) {
      throw new ClassNotLoadedException(e.getMessage(), e);
    }
    return classes;
  }
}
