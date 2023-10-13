package vjvm.runtime.classdata.constant;

import lombok.var;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;
import vjvm.runtime.JClass;

import java.io.DataInput;

public class FieldrefConstant extends Constant {
  private final int classIndex;
  private final int nameAndtypeIndex;
  private String classString;
  private String nameAndtypeString;
  private JClass self;

  @SneakyThrows
  FieldrefConstant(DataInput input, JClass self) {
    classIndex = input.readUnsignedShort();
    nameAndtypeIndex = input.readUnsignedShort();
    this.self = self;
  }

  public String toString() {
    if (classString == null) {
      classString = ((ClassConstant) self.constantPool().constant(classIndex)).getClassString();
    }

    if (nameAndtypeString == null) {
      nameAndtypeString = ((NameAndTypeConstant) self.constantPool().constant(nameAndtypeIndex)).name()
                          + ":" + ((NameAndTypeConstant) self.constantPool().constant(nameAndtypeIndex)).type();
    }
    return String.format("Fieldref: %s.%s", classString, nameAndtypeString);
  }
}
