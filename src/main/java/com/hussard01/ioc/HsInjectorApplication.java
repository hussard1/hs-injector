package com.hussard01.ioc;

import com.hussard01.ioc.config.HsInjectorConfig;
import com.hussard01.ioc.enums.DirectoryType;
import com.hussard01.ioc.model.Directory;
import com.hussard01.ioc.service.ClassLoaderForDirectory;
import com.hussard01.ioc.service.ClassLoaderForJarFile;
import com.hussard01.ioc.service.DirectoryResolverImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class HsInjectorApplication {

  public static void main(final String[] args) {
    run(HsInjectorApplication.class);
  }

  public static void run(final Class<?> startupClass) {
    run(startupClass, new HsInjectorConfig());
  }

  private static void run(final Class<?> startupClass, final HsInjectorConfig hsInjectorConfig) {
    final Directory directory = new DirectoryResolverImpl().resolve(startupClass);
    final Set<Class<?>> classes = new HashSet<>();
    if (directory.getDirectoryType() == DirectoryType.JAR_FILE) {
      classes.addAll(new ClassLoaderForJarFile().loadClasses(directory.getDirectoryName()));
    } else {
      classes.addAll(new ClassLoaderForDirectory().loadClasses(directory.getDirectoryName()));
    }
    log.info("HsInjectorApplication is running...");
    log.info("Dir is : " + classes);
  }
}
