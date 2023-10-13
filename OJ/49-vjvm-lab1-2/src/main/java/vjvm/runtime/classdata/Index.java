package vjvm.runtime.classdata;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.JClass;
import vjvm.runtime.classdata.constant.ClassConstant;

import java.io.DataInput;

public class Index {
  @Getter
  private final int thisClassIndex;
  private final int superClassIndex;
  @Getter
  private final int interfacesCount;
  private final int[] interfacesIndex;
  private JClass jClass;

  @Getter
  private String thisClass;
  @Getter
  private String superClass;
  private String[] Interfaces;

  @SneakyThrows
  public Index(DataInput dataInput, JClass jClass) {
    thisClassIndex = dataInput.readUnsignedShort();
    superClassIndex = dataInput.readUnsignedShort();
    interfacesCount = dataInput.readUnsignedShort();
    interfacesIndex = new int[interfacesCount];
    for (int i = 0; i < interfacesCount; i++) {
      interfacesIndex[i] = dataInput.readUnsignedShort();
    }
    this.jClass = jClass;
    thisClass = SearchClass_info(thisClassIndex);
    superClass = SearchClass_info(superClassIndex);
    Interfaces = new String[interfacesCount];
    for (int i = 0; i < interfacesCount; i++) {
      Interfaces[i] = SearchClass_info(interfacesIndex[i]);
    }
  }

  public String SearchClass_info(int index) {
    return ((ClassConstant) jClass.constantPool().constant(index)).getClassString();
  }
  public String Interfaces(int index) {
    return Interfaces[index];
  }

}
