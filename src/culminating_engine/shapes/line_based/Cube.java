/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package culminating_engine.shapes.line_based;

import java.awt.Color;

/**
 *
 * @author tristan
 */
public class Cube extends RectangularPrism{
    public Cube(double dimension, Color colour){
        super(dimension, dimension, dimension, colour);
    }
    public Cube(double dimension){
        super(dimension, dimension, dimension);
    }
}
