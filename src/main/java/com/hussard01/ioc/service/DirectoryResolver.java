package com.hussard01.ioc.service;

import com.hussard01.ioc.model.Directory;

public interface DirectoryResolver {

    Directory resolve(Class<?> startupClass);
}
