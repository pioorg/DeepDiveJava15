package org.przybyl.ddj15.foreignMemAccess;


import java.lang.invoke.*;
import java.nio.*;

import jdk.incubator.foreign.*;

/**
 * Demo of Foreign Memory Access API incubator feature of Java 15, second preview,
 * based on https://openjdk.java.net/jeps/383
 *
 * Please don't forget to --add-modules jdk.incubator.foreign
 */
public class ForeignMemoryAccessAPIDemo {

	public static void main(String[] args) {
		SequenceLayout intArrayLayout = MemoryLayout.ofSequence(100, MemoryLayout.ofValueBits(32, ByteOrder.nativeOrder()));
		VarHandle intElemHandle = intArrayLayout.varHandle(int.class, MemoryLayout.PathElement.sequenceElement());

		try (MemorySegment segment = MemorySegment.allocateNative(intArrayLayout)) {
			MemoryAddress base = segment.baseAddress();
			for (int i = 0; i < intArrayLayout.elementCount().getAsLong(); i++) {
				intElemHandle.set(base, (long) i, i);
			}

			for (int i = 0; i < intArrayLayout.elementCount().getAsLong(); i++) {
				System.out.println(intElemHandle.get(base, (long) i));
			}

		}
	}
}
