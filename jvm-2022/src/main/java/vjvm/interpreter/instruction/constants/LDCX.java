package vjvm.interpreter.instruction.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;

import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.DoubleConstant;
import vjvm.runtime.classdata.constant.FloatConstant;
import vjvm.runtime.classdata.constant.IntegerConstant;
import vjvm.runtime.classdata.constant.LongConstant;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LDCX extends Instruction {
  private final int value;
  private final String name;

  public static LDCX LDC(ProgramCounter pc, MethodInfo method) {
    return new LDCX(pc.ubyte(), "ldc");
  }

  public static LDCX LDC_W(ProgramCounter pc, MethodInfo method) {
    return new LDCX(pc.ushort(), "ldc_w");
  }

  public static LDCX LDC2_W(ProgramCounter pc, MethodInfo method) {
    return new LDCX(pc.ushort(), "ldc2_w");
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    var pc = thread.top().jClass().constantPool();
    var constant = pc.constant(value);
    if (constant instanceof IntegerConstant) {
      stack.pushInt(((IntegerConstant) constant).value());
    } else if (constant instanceof FloatConstant) {
      stack.pushFloat(((FloatConstant) constant).value());
    } else if (constant instanceof LongConstant) {
      stack.pushLong(((LongConstant) constant).value());
    } else if (constant instanceof DoubleConstant) {
      stack.pushDouble(((DoubleConstant) constant).value());
    }
  }

  @Override
  public String toString() {return name;}
}
