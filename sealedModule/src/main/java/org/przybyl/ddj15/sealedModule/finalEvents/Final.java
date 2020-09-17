/*
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
package org.przybyl.ddj15.sealedModule.finalEvents;

import java.util.stream.*;

import org.przybyl.ddj15.sealedModule.*;
import org.przybyl.ddj15.sealedModule.nonSealedEvents.*;

final public class Final implements Sealed {
	public static void main(String[] args) {
		System.out.println("I implement a sealed type " + Sealed.class);
		examineSealsOf(Sealed.class);
		examineSealsOf(AlsoSealed.class);
		examineSealsOf(NonSealed.class);
		examineSealsOf(Final.class);

	}

	private static void examineSealsOf(Class<?> aClass) {
		System.out.printf("Is %s a sealed type? [%s]%n", aClass.getCanonicalName(), aClass.isSealed());
		Stream.of(aClass.permittedSubclasses()).map(cd -> cd.packageName() + "." + cd.displayName()).forEach(System.out::println);
	}

	public int finalMethod() {
		return 3;
	}
}
