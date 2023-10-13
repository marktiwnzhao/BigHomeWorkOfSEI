package vjvm.runtime;

import lombok.var;
import vjvm.utils.UnimplementedError;
import lombok.Getter;

public class OperandStack {
  @Getter
  private final Slots slots;

  @Getter
  private int top;

  public OperandStack(int stackSize) {
    // TODO: initialize data structures
    slots = new Slots(stackSize);
    top = 0;
  }

  public void pushInt(int value) {
    // TODO: push value
    slots.int_(top++, value);
  }

  public int popInt() {
    // TODO: pop value
    return slots.int_(--top);
  }

  public void pushFloat(float value) {
    // TODO: push value
    slots.float_(top++, value);
  }

  public float popFloat() {
    // TODO: pop value
    return slots.float_(--top);
  }

  public void pushLong(long value) {
    // TODO: push value
    slots.long_(top, value);
    top += 2;
  }

  public long popLong() {
    // TODO: pop value
    top -= 2;
    return slots.long_(top);
  }

  public void pushDouble(double value) {
    // TODO: push value
    slots.double_(top, value);
    top += 2;
  }

  public double popDouble() {
    // TODO: pop value
    top -= 2;
    return slots.double_(top);
  }

  public void pushByte(byte value) {
    // TODO: push value
    slots.byte_(top++, value);
  }

  public byte popByte() {
    // TODO: pop value
    return slots.byte_(--top);
  }

  public void pushChar(char value) {
    // TODO: push value
    slots.char_(top++, value);
  }

  public char popChar() {
    // TODO: pop value
    return slots.char_(--top);
  }

  public void pushShort(short value) {
    // TODO: push value
    slots.short_(top++, value);
  }

  public short popShort() {
    // TODO: pop value
    return slots.short_(--top);
  }

  public void pushSlots(Slots slots) {
    // TODO: push slots
    slots.copyTo(0, slots.size(), this.slots, top);
    top += slots.size();
  }

  public Slots popSlots(int count) {
    // TODO: pop count slots and return
    assert top >= count;

    var ret = new Slots(count);
    top -= count;
    slots.copyTo(top, count, ret, 0);

    return ret;
  }

  public void clear() {
    // TODO: pop all slots
    top = 0;
  }

  @Override
  public String toString() {
    return "OperandStack{" + "slots=" + slots +
      ", top=" + top +
      '}';
  }


}
