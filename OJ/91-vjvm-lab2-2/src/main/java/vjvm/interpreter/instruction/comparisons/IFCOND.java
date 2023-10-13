package vjvm.interpreter.instruction.comparisons;

import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class IFCOND extends Instruction {
  private final IntPredicate compareFunc;
  private final String name;
  private final int offset;

  private IFCOND(IntPredicate compareFunc, String name, ProgramCounter pc) {
    this.compareFunc = compareFunc;
    this.name = name;
    this.offset = pc.short_() - 3;
  }

  public static IFCOND IFEQ(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(x -> x == 0, "ifeq", pc);
  }

  public static IFCOND IFNE(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(x -> x != 0, "ifne", pc);
  }

  public static IFCOND IFLT(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(x -> x < 0, "iflt", pc);
  }

  public static IFCOND IFGE(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(x -> x >= 0, "ifge", pc);
  }

  public static IFCOND IFGT(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(x -> x > 0, "ifgt", pc);
  }

  public static IFCOND IFLE(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(x -> x <= 0, "ifle", pc);
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    var value = stack.popInt();
    var pc = thread.pc();
    if (compareFunc.test(value)) {
      pc.move(offset);
    }
  }

  @Override
  public String toString() {return name;}
}
