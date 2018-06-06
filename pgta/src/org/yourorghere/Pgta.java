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
 * Pgta.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Pgta implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Pgta());
        frame.add(canvas);
        frame.setSize(1240, 680);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));
        GL gl = drawable.getGL();
        gl.setSwapInterval(1);
//        float ambient[] = {1.0f,1.0f,1.0f,1.0f };
//        float diffuse[] = {1.0f,1.0f,1.0f,1.0f };
//        float position[] = {1.0f,1.0f,1.0f,0.0f };
//        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambient,0);
//        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse,0);
//        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position,0);
//        gl.glEnable(GL.GL_LIGHT0);
//        gl.glEnable(GL.GL_LIGHTING);
//        gl.glEnable(GL.GL_DEPTH_TEST);
//        gl.glShadeModel(GL.GL_SMOOTH);

        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1f, 1.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    
    void pillar (GLAutoDrawable drawable){
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
    
    void bridge (GLAutoDrawable drawable){
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
    
    void road (GLAutoDrawable drawable) {
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
    int angle = 0;
    int angleplus = 1;
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        // jalan jembatan kiri    
        gl.glTranslatef(-5f, -2.0f, -10.0f);
        gl.glTranslatef(-1f, 0, 0); // tiang depan
        pillar(drawable);
        gl.glTranslatef(0, 0, -3f); // tiang belakang
        pillar(drawable);
        gl.glTranslatef(0, 0, 3f);
        gl.glTranslatef(1f, 0, 0);
        
        if (angle < 45 && angle >= 0) {
        gl.glRotatef(angle, 0, 0, 1);
        } 
        else if (angle >= 45){
        gl.glRotatef(45, 0, 0, 1);
        }
        
        bridge(drawable);
        road(drawable);
        gl.glTranslatef(0, 0, -3f);
        bridge(drawable);

        
        // jalan jembatan kanan
        gl.glLoadIdentity();
        gl.glTranslatef(0.1f, -2.0f, -10.0f);
        gl.glTranslatef(5f, 0, 0); // tiang depan
        pillar(drawable);
        gl.glTranslatef(0, 0, -3f); // tiang belakang
        pillar(drawable);
        gl.glTranslatef(0, 0, 3f);
        gl.glTranslatef(-5f, 0, 0);
        
        gl.glTranslatef(5f, 0, 0); // mengubah titik rotasi
        if (angle < 45 && angle >= 0) {
        gl.glRotatef(-angle, 0, 0, 1);
        } 
        else if (angle >= 45){
        gl.glRotatef(-45, 0, 0, 1);
        }
        gl.glTranslatef(-5f, 0, 0); //mengembalikan titik ke awal untuk menggambar objek
        
        bridge(drawable);
        road(drawable);
        gl.glTranslatef(0, 0, -3f);
        bridge(drawable);
        
        if (angle == 90)
        {
            angleplus = -angleplus;
        }
        else if (angle == -90)
        {
            angleplus = -angleplus;
        }
        
        angle += angleplus;
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

