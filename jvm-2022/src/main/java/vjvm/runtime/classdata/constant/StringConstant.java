package vjvm.runtime.classdata.constant;

import lombok.var;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;
import vjvm.runtime.JClass;

import java.io.DataInput;

public class StringConstant extends Constant {
  private final int stringIndex;
  private final JClass self;
  private String string;

  @SneakyThrows
  StringConstant(DataInput input, JClass self) {
    stringIndex = input.readUnsignedShort();
    this.self = self;
  }

  public String getString() {
    if (string == null) {
      string = ((UTF8Constant) self.constantPool().constant(stringIndex)).value();
    }

    return string;
  }

  @Override
  public String toString() {
    return String.format("String: \"%s\"", getString());
  }
}
