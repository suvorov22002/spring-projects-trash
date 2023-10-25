package com.features;

import com.features.Util.*;
import lombok.Data;

import java.util.logging.Level;
import java.util.logging.Logger;


@Data
public class InstanceOfStatement {
    static Logger logger = Logger.getLogger(InstanceOfStatement.class.getName());

    public static void main(String[] args) {
        Point p =  new Point(3, 2);
        beforeJava17(p);
        withJava17(p);
    }

    private static void beforeJava17(Object obj) {
        if(obj instanceof Util.Point) {
            logger.log(Level.INFO, "instance of Point {0}.", ((Point) obj).x());
        }
    }

    private static void withJava17(Object obj) {
        if(obj instanceof Point pt) {
            logger.log(Level.INFO, "Since java 17, instance of point: {0}.", pt.x());
        }
    }
}
