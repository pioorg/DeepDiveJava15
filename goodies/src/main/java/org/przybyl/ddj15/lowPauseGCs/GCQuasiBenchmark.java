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
package org.przybyl.ddj15.lowPauseGCs;

import java.lang.management.*;
import java.time.*;
import java.util.stream.*;

/**
 * Please remember this is NOT a proper benchmark!
 *
 * You might need to skip time on Windows and tune the size of the heap if you don't have enough RAM and change the loop boundary.
 * Also, it might happen that your JVM reports missing Shenandoah GC, in that case you can try other JVMs.
 *
 * Let's try to run this the following ways (e.g. in /src/main/java/org/przybyl/ddj15/lowPauseGCs):
 *
 * time java -Xms4G -Xmx8G -XX:StartFlightRecording=disk=true,dumponexit=true,filename=G1.jfr,maxsize=1024m,settings=profile GCQuasiBenchmark.java
 * time java -XX:+UseParallelGC -Xms4G -Xmx8G -XX:StartFlightRecording=disk=true,dumponexit=true,filename=ParallelOld.jfr,maxsize=1024m,settings=profile GCQuasiBenchmark.java
 * //oops, removed in 14 ;-)
 * time java -XX:+UseConcMarkSweepGC -Xms4G -Xmx8G -XX:StartFlightRecording=disk=true,dumponexit=true,filename=CMS.jfr,maxsize=1024m,settings=profile GCQuasiBenchmark.java
 * time java -XX:+UseZGC -Xms4G -Xmx8G -XX:StartFlightRecording=disk=true,dumponexit=true,filename=ZGC.jfr,maxsize=1024m,settings=profile GCQuasiBenchmark.java
 * time java -XX:+UseShenandoahGC -Xms4G -Xmx8G -XX:StartFlightRecording=disk=true,dumponexit=true,filename=Shenandoah.jfr,maxsize=1024m,settings=profile GCQuasiBenchmark.java
 *
 * Now, open the recorded jfr files in Java Mission Control: https://jdk.java.net/jmc/
 */
public class GCQuasiBenchmark {
	private final static Object[] memory = new Object[8192];
	public static void main(String... args) {
		reportUsedGCName();
		Instant start = Instant.now();

		makeGcBusy();

		reportTimeRunning(start);
	}

	static void makeGcBusy() {
		int osize = 320_000;
		int i = 0;
		int j = 0;
		while (true) {
			memory[i++] = new byte[osize];
			if (i >= memory.length) {
				i = 0;
			}
			if (i % 1000 == 0) {
				if (j++ > 200) {
					break;
				}
			}
		}
	}

	static void reportUsedGCName() {
		System.out.println(ManagementFactory.getGarbageCollectorMXBeans()
			.stream()
			.map(GarbageCollectorMXBean::getName)
			.collect(Collectors.joining(", ", "I'm using the following GC MXBeans: ", ".")));
	}

	static void reportTimeRunning(Instant start) {
		Instant stop = Instant.now();
		Duration between = Duration.between(start, stop);
		System.out.printf("I was running for ~%2d.%03d sec.%n", between.toSecondsPart(), between.toMillisPart());
	}
}
