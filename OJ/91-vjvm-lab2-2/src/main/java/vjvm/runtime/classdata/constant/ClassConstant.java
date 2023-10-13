package vjvm.runtime.classdata.constant;

import lombok.var;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;
import vjvm.classfiledefs.Descriptors;
import vjvm.runtime.JClass;

import java.io.DataInput;

public class ClassConstant extends Constant {
  private final int classIndex;
  private final JClass self;
  private String classString;
  private JClass ref;

  @SneakyThrows
  ClassConstant(DataInput input, JClass self) {
    classIndex = input.readUnsignedShort();
    this.self = self;
  }

  public JClass value() {
    if(ref != null) {
      return ref;
    }
    if (getClassString().equals(self.index().thisClass())) {
      ref = self;
    } else {
      ref = self.classLoader().loadClass(Descriptors.of(getClassString()));
    }
    return ref;
  }

  public String getClassString() {
    if (classString == null) {
      classString = ((UTF8Constant) self.constantPool().constant(classIndex)).value();
    }

    return classString;
  }

  @Override
  public String toString() {
    return String.format("Class: %s", getClassString());
  }
}
