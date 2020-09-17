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
package org.przybyl.ddj15.sealedModule;

import org.przybyl.ddj15.sealedModule.finalEvents.*;
import org.przybyl.ddj15.sealedModule.nonSealedEvents.*;

sealed public interface Sealed permits AlsoSealed, NonSealed, Final {

	static int describe(Sealed sealed) {
		if (sealed instanceof AlsoSealed alsoSealed) {
			return alsoSealed.alsoSealedMethod();
		} else if (sealed instanceof NonSealed nonSealed) {
			return nonSealed.nonSealedMethod();
		} else if (sealed instanceof Final aFinal) {
			return aFinal.finalMethod();
		}
		// in the future this should not be needed
		return 0;
	}

/*
	static int describeWithSwitch(Sealed sealed) {
		return switch (sealed) {
			case AlsoSealed as -> as.alsoSealedMethod();
			case NonSealed ns -> ns.nonSealedMethod();
			case Final f -> aFinal.finalMethod();
			default -> {
					System.out.println("This should not be needed thanks to sealing!");
					yield 0;
				}
		}
	}
 /* */
}
