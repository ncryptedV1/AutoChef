package de.cunc.autochef.domain.service.io;

public interface OutputService {
  
  void info(String msg);

  void warning(String msg);

  void severe(String msg);

  void rawOut(Object msg);

  void rawErr(Object msg);
  
}
