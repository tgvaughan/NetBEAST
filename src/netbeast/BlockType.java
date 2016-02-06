/*
 * Copyright (c) 2016 Tim Vaughan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */

package netbeast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Tim Vaughan <tgvaughan@gmail.com>
 */
public abstract class BlockType {

    public static Map<Class, BaseBlockType> boMap = new HashMap<>();

    String blockFactoryName;
    Map<String, BlockType> singletonInputs;
    Map<String, List<BlockType>> nonSingletonInputs;

    List<String> requiredInputs;
    List<Set<String>> xorInputs;

    public abstract boolean isPrimative();
}
