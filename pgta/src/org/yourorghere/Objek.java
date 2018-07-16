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

public class Objek {
    
   static void pillar (GLAutoDrawable drawable){
        GL gl = drawable.getGL();
        gl.glColor3f(0.4f, 0.4f, 0.4f);
        
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
        gl.glColor3f(0.7f, 0.7f, 0.7f);
        
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
        gl.glColor3f(0, 0, 0);
        
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
    
    static void roadpasif (GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glColor3f(0, 0, 0);
        
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
    
    static void Kapal (GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glColor3f(0.5f, 0.5f, 0.5f);
        
        //tampak Belakang
        gl.glBegin(gl.GL_QUADS);
        gl.glVertex3f(0.5f, -0.5f, -15f);
        gl.glVertex3f(-0.5f, -0.5f, -15f);
        gl.glVertex3f(-2.0f, 2.5f, -15f);
        gl.glVertex3f(2.0f, 2.5f, -15f);
        gl.glEnd();
        
        //tampak depan
        gl.glBegin(gl.GL_QUADS);
        gl.glVertex3f(0.5f, -0.5f, -8f);
        gl.glVertex3f(-0.5f, -0.5f, -8f);
        gl.glVertex3f(-2.0f, 2.5f, -8f);
        gl.glVertex3f(2.0f, 2.5f, -8f);
        gl.glVertex3f(0f, 2.5f, -5f);      
        gl.glVertex3f(1f, -0.5f, -8f);
        gl.glEnd();
        
        //tampak atas
        gl.glColor3f(1, 0, 0);
        gl.glBegin(gl.GL_QUADS);
        gl.glVertex3f(2.0f, 2.5f, -8f);
        gl.glVertex3f(2.0f, 2.5f, -15f);
        gl.glVertex3f(-2.0f, 2.5f, -15f);
        gl.glVertex3f(-2.0f, 2.5f, -8f);
        gl.glEnd();
        
        //bangunan
        gl.glColor3f(0.5f, 0.5f, 0.5f);
        gl.glBegin(gl.GL_QUADS);
        gl.glVertex3f(1.0f, 2.5f, -9f);
        gl.glVertex3f(1.0f, 3.5f, -9f);
        gl.glVertex3f(-1.0f, 3.5f, -9f);
        gl.glVertex3f(-1.0f, 2.5f, -9f);
        gl.glEnd();
        
        gl.glBegin(gl.GL_QUADS);
        gl.glVertex3f(1.0f, 2.5f, -13f);
        gl.glVertex3f(1.0f, 3.5f, -13f);
        gl.glVertex3f(-1.0f, 3.5f, -13f);
        gl.glVertex3f(-1.0f, 2.5f, -13f);
        gl.glEnd();
        
        
    }
    
    static void air(GL gl) {
        gl.glColor3f(0, 0, 1);
        float amb[] = {0, 0.5f, 0, 1};
        float diff[] = {0, 0.5f, 0, 1};
        float spec[] = {0, 0.5f, 0, 1};
        float shine = 0;

        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, amb, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, diff, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, spec, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(-10000, -1, -10000);
        gl.glVertex3f(10000, -1, -10000);
        gl.glVertex3f(10000, -1, 10000);
        gl.glVertex3f(-10000, -1, 10000);
        gl.glEnd();
    }
}

