package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;



/**
 * Objek.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Objek {
    
   static void pillar (GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glColor3f(0, 1, 0);
        
        gl.glBegin(gl.GL_POLYGON);
        gl.glVertex3f(0, -2f, 0); // A
        gl.glVertex3f(1f, -2f, 0); // B
        gl.glVertex3f(1f, 5f, 0); // C
        gl.glVertex3f(0, 5f, 0); // D
        
        gl.glVertex3f(0, 5f, -0.5f); // E
        gl.glVertex3f(1f, 5f, -0.5f); // F
        gl.glVertex3f(1f, 5f, 0); // C
        
        gl.glVertex3f(1f, -2f, 0); // B
        gl.glVertex3f(1f, -2f, -0.5f); // G
        gl.glVertex3f(1f, 5f, -0.5f); // F
        
        gl.glVertex3f(0, 5f, -0.5f); // E
        gl.glVertex3f(0, -2f, -0.5f); // H
        gl.glVertex3f(1f, -2f, -0.5f); // G
        
        gl.glVertex3f(1f, -2f, 0); // B
        gl.glVertex3f(0, -2f, 0); // A
        gl.glVertex3f(0, -2f, -0.5f); // H
        
        gl.glVertex3f(0, 5f, -0.5f); // E
        gl.glVertex3f(0, 5f, 0); // D
        gl.glVertex3f(0, -2f, 0); // A
        gl.glEnd();
}
    
    static void bridge (GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glColor3f(0, 0, 0);
        
        gl.glBegin(gl.GL_POLYGON);
        gl.glVertex3f(0, 0, 0); // A
        gl.glVertex3f(5f, 0, 0); // B
        gl.glVertex3f(5f, 0.5f, 0); // C
        gl.glVertex3f(0, 0.5f, 0); // D
        
        gl.glVertex3f(0, 0.5f, -0.5f); // E
        gl.glVertex3f(5f, 0.5f, -0.5f); // F
        gl.glVertex3f(5f, 0.5f, 0); // C
        
        gl.glVertex3f(5f, 0, 0);//B
        gl.glVertex3f(5f, 0, -0.5f);//G
        gl.glVertex3f(5f, 0.5f, -0.5f);//F
        
        gl.glVertex3f(0, 0.5f, -0.5f); // E
        gl.glVertex3f(0, 0f, -0.5f); // H
        gl.glVertex3f(5f, 0, -0.5f);//G
        
        gl.glVertex3f(5f, 0, 0); // B
        gl.glVertex3f(0, 0, 0); // A
        gl.glVertex3f(0, 0f, -0.5f); // H
        
        gl.glVertex3f(0, 0.5f, -0.5f); // E
        gl.glVertex3f(0, 0.5f, 0); // D
        gl.glVertex3f(0, 0, 0); // A
        gl.glEnd();
    }
    
    static void road (GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glColor3f(1, 0, 0);
        
        gl.glBegin(gl.GL_POLYGON);
        gl.glVertex3f(0, 0, -0.5f); // A
        gl.glVertex3f(5f, 0, -0.5f); // B
        gl.glVertex3f(5f, 0.2f, -0.5f); // C
        gl.glVertex3f(0, 0.2f, -0.5f); // D
        
        gl.glVertex3f(0, 0.2f, -3f); // E
        gl.glVertex3f(5f, 0.2f, -3f); // F
        gl.glVertex3f(5f, 0.2f, -0.5f); // C
        
        gl.glVertex3f(5f, 0, -0.5f); // B
        gl.glVertex3f(5f, 0, -3f); // G
        gl.glVertex3f(5f, 0.2f, -3f); // F
        
        gl.glVertex3f(0, 0.2f, -3f); // E
        gl.glVertex3f(0, 0f, -3f); // H
        gl.glVertex3f(5f, 0, -3f); // G
        
        gl.glVertex3f(5f, 0, -0.5f); // B
        gl.glVertex3f(0, 0, -0.5f); // A
        gl.glVertex3f(0, 0f, -3f); // H
        
        gl.glVertex3f(0, 0.2f, -3f); // E
        gl.glVertex3f(0, 0.2f, -0.5f); // D
        gl.glVertex3f(0, 0, -0.5f); // A
        gl.glEnd();
    }


}

