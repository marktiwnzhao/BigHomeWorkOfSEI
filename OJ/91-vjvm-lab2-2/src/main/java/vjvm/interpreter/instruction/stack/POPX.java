package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.Consumer;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class POPX extends Instruction {
  private final Consumer<OperandStack> popFunc;
  private final String name;

  public static POPX POP(ProgramCounter pc, MethodInfo method) {
    return new POPX(s -> s.popSlots(1), "pop");
  }

  public static POPX POP2(ProgramCounter pc, MethodInfo method) {
    return new POPX(s -> s.popSlots(2), "pop2");
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    popFunc.accept(stack);
  }

  @Override
  public String toString() {return name;}

}
