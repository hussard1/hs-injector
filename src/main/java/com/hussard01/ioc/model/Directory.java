package com.hussard01.ioc.model;

import com.hussard01.ioc.enums.DirectoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Directory {
  private String directoryName;
  private DirectoryType directoryType;
}
