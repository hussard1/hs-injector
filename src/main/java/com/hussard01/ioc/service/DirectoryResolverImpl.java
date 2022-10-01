package com.hussard01.ioc.service;

import com.hussard01.ioc.enums.DirectoryType;
import com.hussard01.ioc.model.Directory;

import java.io.File;

public class DirectoryResolverImpl implements DirectoryResolver {

  private static final String JAR_EXTENSION = ".jar";

  @Override
  public Directory resolve(final Class<?> startupClass) {
    final String directoryName = getDirectoryName(startupClass);
    return new Directory(directoryName, getDirectoryType(directoryName));
  }

  private String getDirectoryName(final Class<?> startupClass) {
    return startupClass.getProtectionDomain().getCodeSource().getLocation().getFile();
  }

  private DirectoryType getDirectoryType(final String directoryName) {
    final File file = new File(directoryName);
    if (!file.isDirectory() && directoryName.endsWith(JAR_EXTENSION)) {
      return DirectoryType.JAR_FILE;
    }
    return DirectoryType.DIRECTORY;
  }
}
