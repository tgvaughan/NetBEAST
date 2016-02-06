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

import beast.core.BEASTObject;
import beast.core.Input;

/**
 * @author Tim Vaughan <tgvaughan@gmail.com>
 */
public class BaseBlockType extends BlockType {

    Class beastObjectClass;

    public BaseBlockType(Class beastObjectClass) {

        if (!BEASTObject.class.isAssignableFrom(beastObjectClass))
            throw new IllegalArgumentException("Error creating BlockFactory: "
                    + beastObjectClass.getName() + " is not a BEASTObject");

        this.beastObjectClass = beastObjectClass;
        blockFactoryName = this.beastObjectClass.getSimpleName();

        // Instantiate BEASTObject
        // (this is why BEASTObjects need default constructors)

        BEASTObject instance = null;
        try {
            instance = (BEASTObject) beastObjectClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.err.println("Error instantiating class "
                    + beastObjectClass.getName()
                    + ". Maybe it is missing a default constructor?");
            System.exit(1);
        }

        // Populate inputs
        try {
            for (Input input : instance.listInputs()) {
                try {
                    input.determineClass(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(input.getName() + " " + input.getType().getCanonicalName());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isPrimative() {
        return false;
    }
}
