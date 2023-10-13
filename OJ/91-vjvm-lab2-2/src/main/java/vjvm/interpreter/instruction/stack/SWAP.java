package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.Slots;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.BiConsumer;
import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SWAP extends Instruction {
  public SWAP(ProgramCounter pc, MethodInfo method) {
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    var value = stack.popSlots(1);
    var value2 = stack.popSlots(2);
    stack.pushSlots(value);
    stack.pushSlots(value2);
  }

  @Override
  public String toString() {return "swap";}

}
