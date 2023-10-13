package vjvm.runtime.classdata.constant;

import lombok.var;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInput;

public class FloatConstant extends Constant {
  @Getter
  private final float value;

  @SneakyThrows
  FloatConstant(DataInput input) {
    value = input.readFloat();
  }

  @Override
  public String toString() {
    return String.format("Float: %a", value);
  }
}
