package domain.service;

import de.cunc.autochef.domain.service.io.InputReader;

public class ConsoleInputReaderFake implements InputReader {
  
  String content;
  
  public ConsoleInputReaderFake(String content) {
    this.content = content;
  }
  
  @Override
  public String readLine() {
    return this.content;
  }
}
