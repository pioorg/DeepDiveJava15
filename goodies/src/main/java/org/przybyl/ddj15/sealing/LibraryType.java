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
package org.przybyl.ddj15.sealing;

sealed interface LibraryType
	permits AbstractLibTypeImpl, NoOpLibTypeImpl {}

sealed abstract class AbstractLibTypeImpl
	implements LibraryType
	permits LibTypeImpl1, LibTypeImpl2 {}

final class LibTypeImpl1 extends AbstractLibTypeImpl {}
final class LibTypeImpl2 extends AbstractLibTypeImpl {}

final class NoOpLibTypeImpl implements LibraryType {}

class UserLibTypeImpl
	// this doesn't work
	// extends AbstractLibTypeImpl implements LibraryType
{}

class SomeClass {
	AbstractLibTypeImpl typeImpl;
	void method(LibraryType libType) {}
}