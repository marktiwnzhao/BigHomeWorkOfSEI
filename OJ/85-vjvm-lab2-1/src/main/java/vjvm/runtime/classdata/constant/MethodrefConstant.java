package vjvm.runtime.classdata.constant;

import lombok.var;
import lombok.SneakyThrows;
import org.apache.commons.lang3.tuple.Pair;
import vjvm.runtime.JClass;
import vjvm.runtime.classdata.MethodInfo;

import java.io.DataInput;

public class MethodrefConstant extends Constant {
  private final int classIndex;
  private final int nameAndtypeIndex;
  private ClassConstant classRef;
  private NameAndTypeConstant nameAndType;
  private String classString;
  private String nameAndtypeString;
  private JClass self;
  private MethodInfo method;

  @SneakyThrows
  MethodrefConstant(DataInput input, JClass self) {
    classIndex = input.readUnsignedShort();
    nameAndtypeIndex = input.readUnsignedShort();
    this.self = self;
  }

  public JClass jClass() {return classRef().value();}

  private ClassConstant classRef() {
    if(classRef == null) {
      classRef = (ClassConstant) self.constantPool().constant(classIndex);
    }
    return classRef;
  }

  private NameAndTypeConstant nameAndType() {
    if (nameAndType == null) {
      nameAndType = (NameAndTypeConstant) self.constantPool().constant(nameAndtypeIndex);
    }
    return nameAndType;
  }

  public MethodInfo value() {
    if (method != null) {
      return method;
    }
    method = jClass().findMethod(nameAndType().name(), nameAndType().type());
    if (method == null) {
      throw new Error("No such method");
    }
    return method;
  }

  public String toString() {
    if (classString == null) {
      classString = ((ClassConstant) self.constantPool().constant(classIndex)).getClassString();
    }

    if (nameAndtypeString == null) {
      nameAndtypeString = ((NameAndTypeConstant) self.constantPool().constant(nameAndtypeIndex)).name()
        + ":" + ((NameAndTypeConstant) self.constantPool().constant(nameAndtypeIndex)).type();
    }
    return String.format("Methodref: %s.%s", classString, nameAndtypeString);
  }
}
