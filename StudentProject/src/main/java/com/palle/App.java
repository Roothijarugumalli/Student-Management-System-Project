package com.palle;

import com.palle.controller.StudentController;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	   StudentController controller =
                   new StudentController();

           controller.start();
    }
}
