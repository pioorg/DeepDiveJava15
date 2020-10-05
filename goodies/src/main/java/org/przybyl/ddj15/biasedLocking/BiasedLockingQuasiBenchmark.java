/*
 *  Copyright (C) 2020 Jarosław Pałka
 *  Copyright (C) 2020 Piotr Przybył
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.przybyl.ddj15.biasedLocking;

import java.time.*;
import java.util.*;


/**
 * Please remember this is NOT a proper benchmark!
 *
 * You might need to skip time on Windows and tune the size of the heap if you don't have enough RAM and change the loop boundary.
 *
 * Let's try to run this with and without BiasedLocking enabled. You can do that using
 * -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
 * or
 * -XX:-UseBiasedLocking
 *
 */
public class BiasedLockingQuasiBenchmark {
	private final static Vector<Object> memory = new Vector<>(4096);

	public static void main(String... args) {
		Instant start = Instant.now();

		performSomeSynchronizedActions();

		reportTimeRunning(start);
	}

	static void performSomeSynchronizedActions() {
		Random r = new Random();
		int i = 0;
		int j = 0;
		while (j++ < 1_000_000) {
			StringBuffer sb = new StringBuffer();
			memory.insertElementAt(sb, i++);
			if (i >= memory.size()) {
				i = 0;
			}
			for (int k = 0; k < 1000; k++) {
				sb.append(r.nextInt(26) + 64);
			}
		}
	}


	static void reportTimeRunning(Instant start) {
		Instant stop = Instant.now();
		Duration between = Duration.between(start, stop);
		System.out.printf("I was running for ~%2d.%03d sec.%n", between.toSecondsPart(), between.toMillisPart());
	}
}
