package vjvm.interpreter.instruction.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.DoubleConstant;
import vjvm.runtime.classdata.constant.FloatConstant;
import vjvm.runtime.classdata.constant.IntegerConstant;
import vjvm.runtime.classdata.constant.LongConstant;

import java.util.function.BiConsumer;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LDCX<T> extends Instruction {
  private final T value;
  private final BiConsumer<OperandStack, T> pushFunc;
  private String name;

  public static final LDCX<Object> LDC(ProgramCounter pc, MethodInfo method) {
    int index = pc.ubyte();
    Object con = method.jClass().constantPool().constant(index);
    if (con instanceof IntegerConstant) {
      return new LDCX<>(((IntegerConstant) con).value(), OperandStack::pushObject, "ldc");
    } else if (con instanceof FloatConstant) {
      return new LDCX<>(((FloatConstant) con).value(), OperandStack::pushObject, "ldc");
    } else {
      throw new ClassCastException();
    }
  }

  public static final LDCX<Object> LDC_W(ProgramCounter pc, MethodInfo method) {
    int index = pc.ushort();
    Object con = method.jClass().constantPool().constant(index);
    if (con instanceof IntegerConstant) {
      return new LDCX<>(((IntegerConstant) con).value(), OperandStack::pushObject, "ldc_w");
    } else if (con instanceof FloatConstant) {
      return new LDCX<>(((FloatConstant) con).value(), OperandStack::pushObject, "ldc_w");
    } else {
      throw new ClassCastException();
    }
  }

  public static final LDCX<Object> LDC2_W(ProgramCounter pc, MethodInfo method) {
    int index = pc.ushort();
    Object con = method.jClass().constantPool().constant(index);
    if (con instanceof DoubleConstant) {
      return new LDCX<>(((DoubleConstant) con).value(), OperandStack::pushObject, "ldc2_w");
    } else if (con instanceof LongConstant) {
      return new LDCX<>(((LongConstant) con).value(), OperandStack::pushObject, "ldc2_w");
    } else {
      throw new ClassCastException();
    }
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    pushFunc.accept(stack, value);
  }

  @Override
  public String toString() {return name;}
}
