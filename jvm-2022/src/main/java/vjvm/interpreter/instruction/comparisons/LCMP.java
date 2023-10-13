package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.BiFunction;
import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LCMP extends Instruction {
  private final Function<OperandStack, Long> popFunc;
  private final BiFunction<Long, Long, Integer> compareFunc;
  private final String name;

  public LCMP(ProgramCounter pc, MethodInfo method) {
    this.popFunc = OperandStack::popLong;
    this.compareFunc = Long::compare;
    this.name = "lcmp";
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    var right = popFunc.apply(stack);
    var left = popFunc.apply(stack);
    stack.pushInt(compareFunc.apply(left, right));
  }

  @Override
  public String toString() {return name;}
}
