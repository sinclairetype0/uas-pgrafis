/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.j2d.TextRenderer;
import java.awt.Color;
import java.awt.Font;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class GLRenderer implements GLEventListener {
//class vector untuk memudah vektor-isasi

    class vector {

        float x;
        float y;
        float z;

        public vector(float startX, float startY, float startZ) {
            x = startX;
            y = startY;
            z = startZ;
        }

        void vectorRotation(vector reference, float angle) {
            vector temp = reference;
            float magnitude = (float) Math.sqrt(Math.pow(temp.x, 2) + Math.pow(temp.y, 2) + Math.pow(temp.z, 2));
            temp.x = temp.x / magnitude;
            temp.y = temp.y / magnitude;
            temp.z = temp.z / magnitude;
            float dot_product = (x * temp.x) + (y * temp.y) + (z * temp.z);
            float cross_product_x = (y * temp.z) - (temp.z * z);
            float cross_product_y = -((x * temp.z) - (z * temp.x));
            float cross_product_z = (x * temp.y) - (y * temp.x);
            float last_factor_rodrigues = (float) (1 - Math.cos(Math.toRadians(angle % 360)));
            x = (float) ((x * Math.cos(Math.toRadians(angle % 360)))
                    + (cross_product_x * Math.sin(Math.toRadians(angle % 360)))
                    + (dot_product * last_factor_rodrigues * x));
            y = (float) ((this.y * Math.cos(Math.toRadians(angle % 360)))
                    + (cross_product_y * Math.sin(Math.toRadians(angle % 360)))
                    + (dot_product * last_factor_rodrigues * y));
            z = (float) ((z * Math.cos(Math.toRadians(angle % 360)))
                    + (cross_product_z * Math.sin(Math.toRadians(angle % 360)))
                    + (dot_product * last_factor_rodrigues * z));
        }
    }
    vector depanBelakang = new vector(0f, 0f, 1f);//deklarasi awal vektor untuk maju & mundur
    vector samping = new vector(1f, 0f, 0f);//deklarasi awal vektor untuk gerakan ke kanan & kiri
    vector vertikal = new vector(0f, 1f, 0f);//deklarasi awal vetor untuk gerakan naik & turun
    float Cx = 0, Cy = 2.5f, Cz = 0;
    float Lx = 0, Ly = 2.5f, Lz = -20f;
    float angle_depanBelakang = 0f;
    float angle_depanBelakang2 = 0f;
    float angle_samping = 0f;
    float angle_samping2 = 0f;
    float angle_vertikal = 0f;
    float angle_vertikal2 = 0f;
    float silinderAngle = 0f;
    boolean ori = true, silinder = false, kamera = false;
    boolean silinderX = false, silinderY = false, silinderH = false, silinderC = false, silinderZ = false;
    boolean kamera8 = false, kamera2 = false, kamera4 = false, kamera6 = false, kamera1 = false;
    float x = 1, y = 0, z = 0;
    /*
     ini adalah metod untuk melakukan pergerakan.
     magnitude adalah besarnya gerakan sedangkan direction digunakan untuk menentukan arah.
     gunakan -1 untuk arah berlawanan dengan vektor awal
     */

    private void vectorMovement(vector toMove, float magnitude, float direction) {
        float speedX = toMove.x * magnitude * direction;
        float speedY = toMove.y * magnitude * direction;
        float speedZ = toMove.z * magnitude * direction;
        Cx += speedX;
        Cy += speedY;
        Cz += speedZ;
        Lx += speedX;
        Ly += speedY;
        Lz += speedZ;
    }

    private void cameraRotation(vector reference, double angle) {
        float M = (float) (Math.sqrt(Math.pow(reference.x, 2) + Math.pow(reference.y, 2) + Math.pow(reference.z, 2)));//magnitud
        float Up_x1 = reference.x / M; //melakukan
        float Up_y1 = reference.y / M; //normalisasi
        float Up_z1 = reference.z / M; //vektor patokan
        float VLx = Lx - Cx;
        float VLy = Ly - Cy;
        float VLz = Lz - Cz;
        float dot_product = (VLx * Up_x1) + (VLy * Up_y1) + (VLz * Up_z1);
        float cross_product_x = (Up_y1 * VLz) - (VLy * Up_z1);
        float cross_product_y = -((Up_x1 * VLz) - (Up_z1 * VLx));
        float cross_product_z = (Up_x1 * VLy) - (Up_y1 * VLx);
        float last_factor_rodriques = (float) (1 - Math.cos(Math.toRadians(angle % 360)));
        float Lx1 = (float) ((VLx * Math.cos(Math.toRadians(angle % 360)))
                + (cross_product_x * Math.sin(Math.toRadians(angle % 360)))
                + (dot_product * last_factor_rodriques * VLx));
        float Ly1 = (float) ((VLy * Math.cos(Math.toRadians(angle % 360)))
                + (cross_product_y * Math.sin(Math.toRadians(angle % 360)))
                + (dot_product * last_factor_rodriques * VLy));
        float Lz1 = (float) ((VLz * Math.cos(Math.toRadians(angle % 360)))
                + (cross_product_z * Math.sin(Math.toRadians(angle % 360)))
                + (dot_product * last_factor_rodriques * VLz));
        Lx = Lx1 + Cx;
        Ly = Ly1 + Cy;
        Lz = Lz1 + Cz;
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        // Enable VSync
        gl.setSwapInterval(1);
        //float ambient[] = {1.0f,1.0f,1.0f,1.0f };
        //float diffuse[] = {1.0f,1.0f,1.0f,1.0f };
        //float position[] = {1.0f,1.0f,1.0f,0.0f };
        //gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambient,0);
        //gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse,0);
        //gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position,0);
        //gl.glEnable(GL.GL_LIGHT0);
        //gl.glEnable(GL.GL_LIGHTING);
        //gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glClearColor(0.5f, 0.7f, 1.0f, 1.0f);
        //gl.glShadeModel(GL.GL_SMOOTH);
        //gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
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

    int angle = 0;
    int angle2 = 0;
    int angleplus = 1;
    int gerakin = 0;
    float jalan1 = 0;
    float jalan2 = 0;
    float acc = 0.2f;
    float sunk = 0;
    int spin = 1;
    boolean angkat = false;
    boolean maju = false;
    boolean trukkiri = false;
    boolean trukkanan = false;
    public void display(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();
        GLU glu = new GLU();
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        // Reset the current matrix to the "identity"
        // Move the "drawing cursor" around
        gl.glClearColor(1f, 1f, 0.8f, 0);
        TextRenderer textSpeed = new TextRenderer(new Font("Times New Roman", 10, 20));
        textSpeed.beginRendering(900, 700);
        textSpeed.setColor(Color.black);
        textSpeed.setSmoothing(true);
        textSpeed.draw("Kecepatan jembatan : " + angleplus, (int) (20), (int) (680));
        textSpeed.draw("Kecepatan truk : " + acc, (int) (20), (int) (650));
        textSpeed.endRendering();
        
        gl.glLoadIdentity();
        glu.gluLookAt(Cx, Cy, Cz, Lx, Ly, Lz, vertikal.x, vertikal.y, vertikal.z);
        //Pasif Objek
        Objek.air(gl);
        gl.glRotatef(silinderAngle, x, y, z);
        gl.glTranslatef(5f, 0.5f, -10f);
        Objek.bridgepasif(drawable); // jembatan pasif kanan
        Objek.roadpasif(drawable);
        gl.glTranslatef(0f, 0f, -3f);
        Objek.bridgepasif(drawable);
        
        gl.glTranslatef(-30f, 0f, 3f); // jembatan pasif kiri
        Objek.roadpasif(drawable);
        Objek.bridgepasif(drawable);
        gl.glTranslatef(0f, 0f, -3f);
        Objek.bridgepasif(drawable);
        
        //Kapal
        gl.glLoadIdentity();
        glu.gluLookAt(Cx, Cy, Cz, Lx, Ly, Lz, vertikal.x, vertikal.y, vertikal.z);
        gl.glRotatef(silinderAngle, x, y, z);
        //Maju Kapal
        gl.glTranslatef(0f, -1f, -10f);
        if (sunk == 0)
        {
            if (maju == true) {
                kapalmaju(drawable, 78);
            } 
            else if (maju == false) {
                kapalmundur(drawable, 78);
            }     
        }
        else {
            gl.glTranslatef(0, sunk, gerakin); // simpan gerakan kapal
        }
        if (angle <= 20)
        {
            if (gerakin >= 5 && gerakin <= 15) // jika kapal maju ketika angle <= 20 maka kapal tenggelam
            {
                sunk = sunk - 0.1f;
            }
        }
        Objek.Kapal(drawable);
       
        // jalan jembatan kiri
        gl.glLoadIdentity();
        glu.gluLookAt(Cx, Cy, Cz, Lx, Ly, Lz, vertikal.x, vertikal.y, vertikal.z);
        gl.glTranslatef(-5f, 0.5f, -10.0f);
        gl.glTranslatef(-1f, 0, 0);// tiang depan
        gl.glRotatef(silinderAngle, x, y, z);
        Objek.pillar(drawable);
        gl.glTranslatef(-10f, 0, 0); // tiang depan 2
        Objek.pillar(drawable);
        gl.glTranslatef(10f, 0, 0);
        gl.glTranslatef(0, 0, -3f); // tiang belakang
        Objek.pillar(drawable);
        gl.glTranslatef(-10f, 0, 0); // tiang blkng 2
        Objek.pillar(drawable);
        gl.glTranslatef(10f, 0, 0);
        gl.glTranslatef(0, 0, 3f);
        gl.glTranslatef(1f, 0, 0);

        if (angkat) {
            drawingkiri(drawable, 32);
        } else if (angkat == false && angle >= 0) {
            drawdownkiri(drawable, 32);
        }
        Objek.bridge(drawable);
        Objek.road(drawable);
        gl.glTranslatef(0, 0, -3f);
        Objek.bridge(drawable);

        // jalan jembatan kanan
        gl.glLoadIdentity();
        glu.gluLookAt(Cx, Cy, Cz, Lx, Ly, Lz, vertikal.x, vertikal.y, vertikal.z);
        gl.glTranslatef(0.1f, 0.5f, -10.0f);
        gl.glTranslatef(5f, 0, 0); // tiang depan
        gl.glRotatef(silinderAngle, x, y, z);
        Objek.pillar(drawable);
        gl.glTranslatef(10f, 0, 0); // tiang depan 2
        Objek.pillar(drawable);
        gl.glTranslatef(-10f, 0, 0);
        gl.glTranslatef(0, 0, -3f); // tiang belakang
        Objek.pillar(drawable);
        gl.glTranslatef(10f, 0, 0); // tiang blkng 2
        Objek.pillar(drawable);
        gl.glTranslatef(-10f, 0, 0);
        gl.glTranslatef(0, 0, 3f);
        gl.glTranslatef(-5f, 0, 0);
        if (angkat) {
            drawingkanan(drawable, 32);
        } else if (angkat == false && angle >= 0) {
            drawdownkanan(drawable, 32);
        }
        Objek.bridge(drawable);
        Objek.road(drawable);
        gl.glTranslatef(0, 0, -3f);
        Objek.bridge(drawable);
        
        gl.glLoadIdentity(); // truck merah kiri
        glu.gluLookAt(Cx, Cy, Cz, Lx, Ly, Lz, vertikal.x, vertikal.y, vertikal.z);
        gl.glRotatef(silinderAngle, x, y, z);
        gl.glTranslatef(-23f, 1f, -11f); 
        if (trukkiri)
        {
            if (angle == 0 || jalan1 <= 15 || jalan1 >= 27) // jika truk tidak ada di daerah jembatan angkat maka jalan terus
            {
                lajutrukkiri(drawable, 44);
            }
            else if (jalan1 > 15 && jalan1 < 27) { // jika truk berhenti ditengah jalan maka dimundurkan
                jalan1 = 15f;
                gl.glTranslatef(jalan1, 0, 0);
            }
            else if (angle >= 1) { // jika jembatan naik maka truk berhenti
                gl.glTranslatef(jalan1, 0, 0);
            }
        }
        else if (trukkiri == false)
        {
            gl.glTranslatef(jalan1, 0, 0);
        }
        Objek.btruck(drawable);
        gl.glTranslatef(1f, 0, 0);
        Objek.htruck(drawable);
        
        gl.glLoadIdentity(); // truck merah kanan
        glu.gluLookAt(Cx, Cy, Cz, Lx, Ly, Lz, vertikal.x, vertikal.y, vertikal.z);
        gl.glRotatef(silinderAngle, x, y, z);
        gl.glTranslatef(22f, 1f, -10.5f); 
        if (trukkanan) { 
            if (angle == 0 || jalan2 >= -15 || jalan2 <= -27) // jika truk tidak ada di daerah jembatan angkat maka jalan terus
            {
                lajutrukkanan(drawable, 46);
            }
            else if (jalan2 < -15f && jalan2 > -27f) { // jika truk berhenti ditengah jalan maka dimundurkan
                jalan2 = -15f;
                gl.glTranslatef(jalan2, 0, 0);
            }
            else if (angle >= 1) { // jika jembatan naik maka truk berhenti
                gl.glTranslatef(jalan2, 0, 0);
            }
        }
        else if (trukkanan == false) {
            gl.glTranslatef(jalan2, 0, 0);
        }
        Objek.btruck(drawable);
        gl.glTranslatef(-0.5f, 0, 0);
        Objek.htruck(drawable);
        
        gl.glLoadIdentity(); // baling kiri
        glu.gluLookAt(Cx, Cy, Cz, Lx, Ly, Lz, vertikal.x, vertikal.y, vertikal.z);
        gl.glRotatef(silinderAngle, x, y, z);
        gl.glTranslatef(-5.25f, 5f, -9.85f);
        gl.glRotatef(spin, 0, 0, 1);
        Objek.baling(drawable); 
        gl.glTranslatef(0, 0, -4.25f);
        Objek.baling(drawable);
        
        gl.glLoadIdentity(); // baling kanan
        glu.gluLookAt(Cx, Cy, Cz, Lx, Ly, Lz, vertikal.x, vertikal.y, vertikal.z);
        gl.glRotatef(silinderAngle, x, y, z);
        gl.glTranslatef(5.25f, 5f, -9.85f);
        gl.glRotatef(spin, 0, 0, 1);
        Objek.baling(drawable);
        gl.glTranslatef(0, 0, -4.25f);
        Objek.baling(drawable);
        System.out.println(spin);
        if (spin < 360){
        spin = spin + 10;
        }
        else {
            spin = 0;
        }
        
        //GERAKAN
        if (silinder) {
            x = 0;
            y = 0;
            z = 1f;
            silinderAngle += 15f;
        }
        if (silinder) {
            x = 1f;
            y = 0;
            z = 0;
            silinderAngle += 15f;
        }
        if (silinderX) {
            x = 1f;
            y = 0;
            z = 0;
            silinderAngle -= 15f;
        }
        if (silinderY) {
            x = 0;
            y = 1f;
            z = 0;
            silinderAngle += 15f;
        }
        if (silinderH) {
            x = 0;
            y = 1f;
            z = 0;
            silinderAngle -= 15f;
        }
        if (silinderC) {
            x = 0;
            y = 0f;
            z = 1f;
            silinderAngle += 15f;
        }
        if (silinderZ) {
            x = 0;
            y = 0;
            z = 1f;
            silinderAngle -= 15f;
        }
        //KAMERA
        if (kamera) {
            Key_Pressed(40); // looping panah bawah
        }
        if (kamera8) {
            Key_Pressed(38); // looping panah atas
        }
        if (kamera2) {
            Key_Pressed(37);
        }
        if (kamera4) {
            Key_Pressed(39); // looping panah kanan
        }
        if (kamera6) {
            Key_Pressed(10);
        }
        if (kamera1) {
            Key_Pressed(92); // belakang
        }
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    // pergerakkan
    void drawingkiri(GLAutoDrawable drawable, int KeyCode) {
        GL gl = drawable.getGL();
        if (KeyCode == 32) {
            gl.glRotatef(angle, 0, 0, 1);
        }
        if (angle < 45) {
            angle = angle + angleplus;
        }
    }
    
    void drawingkanan(GLAutoDrawable drawable, int KeyCode) {
        GL gl = drawable.getGL();
        gl.glTranslatef(5f, 0, 0);
        if (KeyCode == 32) {
            gl.glRotatef(angle2, 0, 0, -1);
        }
        if (angle2 < 45) {
            angle2 = angle2 + angleplus;
        }
        gl.glTranslatef(-5f, 0, 0);
    }

    void drawdownkiri(GLAutoDrawable drawable, int KeyCode) {
        GL gl = drawable.getGL();
        if (KeyCode == 32) {
            gl.glRotatef(angle, 0, 0, 1);
        }
        if (angle > 0) {
            angle = angle - angleplus;
        }
    }

    void drawdownkanan(GLAutoDrawable drawable, int KeyCode) {
        GL gl = drawable.getGL();
        gl.glTranslatef(5f, 0, 0);
        if (KeyCode == 32) {
            gl.glRotatef(angle2, 0, 0, -1);
        }
        if (angle2 > 0) {
            angle2 = angle2 - angleplus;
        }
        gl.glTranslatef(-5f, 0, 0);
    }


    void kapalmaju(GLAutoDrawable drawable, int KeyCode) {
        GL gl = drawable.getGL();
        if (KeyCode == 78) {
            gl.glTranslatef(0, 0, gerakin);
        }
        if (gerakin <= 20) {
            gerakin++;
        }
    }
    void kapalmundur(GLAutoDrawable drawable, int KeyCode) {
        GL gl = drawable.getGL();
        if (KeyCode == 78) {
            gl.glTranslatef(0, 0, gerakin);
        }
        if (gerakin > 0) {
            gerakin--;
        }
    }
    void lajutrukkiri(GLAutoDrawable drawable, int KeyCode) {
        GL gl = drawable.getGL();
        if (KeyCode == 44) {
            gl.glTranslatef(jalan1, 0, 0);
        }
        if ((jalan1 < 45f && acc >= 0) || (acc <= 0 && jalan1 >= 0))
        {
            jalan1 = jalan1 + acc;
        }
    }void lajutrukkanan(GLAutoDrawable drawable, int KeyCode) {
        GL gl = drawable.getGL();
        if (KeyCode == 46) {
            gl.glTranslatef(jalan2, 0, 0);
        }
        if ((jalan2 > -44f || acc <= 0) && (acc >= 0 || jalan2 <= 0))
        {
            jalan2 = jalan2 - acc;
        }
    }


    void Key_Pressed(int keyCode) {
        // Gerakan kamera
        //huruf Q
        if (keyCode == 81) {
            vectorMovement(depanBelakang, 2f, 1f);
        } //huruf E
        else if (keyCode == 69) {
            vectorMovement(depanBelakang, 2f, -1f);
        } //huruf D
        else if (keyCode == 68) {
            vectorMovement(samping, 2f, 1f);
        } //huruf A
        else if (keyCode == 65) {
            vectorMovement(samping, 2f, -1f);
        } //huruf W
        else if (keyCode == 87) {
            vectorMovement(vertikal, 2f, 1f);
        } //panah S
        else if (keyCode == 83) {
            vectorMovement(vertikal, 2f, -1f);
        } 
        // Tombol on / off
        //J
        else if (keyCode == 74) {
            if (silinder) {
                silinder = false;
            } else {
                silinder = true;
            }
        } // L
        else if (keyCode == 76) {
            if (silinderX) {
                silinderX = false;
            } else {
                silinderX = true;
            }
        } else if (keyCode == 85) { // o
            if (silinderY) {
                silinderY = false;
            } else {
                silinderY = true;
            }
        } else if (keyCode == 79) { // u
            if (silinderH) {
                silinderH = false;
            } else {
                silinderH = true;
            }
        } else if (keyCode == 73) { // i
            if (silinderC) {
                silinderC = false;
            } else {
                silinderC = true;
            }
        } else if (keyCode == 75) { // k
            if (silinderZ) {
                silinderZ = false;
            } else {
                silinderZ = true;
            }
        } 
        else if (keyCode == 98) { // 2
            if (kamera) {
                kamera = false;
            } else {
                kamera = true;
            }
        } else if (keyCode == 104) { // 8 nyalakan numlock
            if (kamera8) {
                kamera8 = false;
            } else {
                kamera8 = true;
            }
        } else if (keyCode == 100) { // 4
            if (kamera2) {
                kamera2 = false;
            } else {
                kamera2 = true;
            }
        } else if (keyCode == 102) { // 6
            if (kamera4) {
                kamera4 = false;
            } else {
                kamera4 = true;
            }
        } else if (keyCode == 105) { // 9
            if (kamera6) {
                kamera6 = false;
            } else {
                kamera6 = true;
            }
        } else if (keyCode == 103) { // 7
            if (kamera1) {
                kamera1 = false;
            } else {
                kamera1 = true;
            }
        } else if (keyCode == 32) { // spasi
            if (angkat) {
                angkat = false;
            } else {
                angkat = true;
            }
        } else if (keyCode == 78) { //N
            if (maju) {
                maju = false;
            } else {
                maju = true;
            }
        } else if (keyCode == 44) { // .
            if (trukkiri) {
                trukkiri = false;
            } else {
                trukkiri = true;
            }
        } else if (keyCode == 46) { // ,
            if (trukkanan) {
                trukkanan = false;
            } else {
                trukkanan = true;
            }
        }
        //panah kiri
        else if (keyCode == 37) {
            angle_vertikal += 15f;
            samping.vectorRotation(vertikal, angle_vertikal - angle_vertikal2);
            depanBelakang.vectorRotation(vertikal, angle_vertikal - angle_vertikal2);
            cameraRotation(vertikal, angle_vertikal - angle_vertikal2);
            angle_vertikal2 = angle_vertikal;
        } //panah kanan
        else if (keyCode == 39) {
            angle_vertikal -= 15f;
            samping.vectorRotation(vertikal, angle_vertikal - angle_vertikal2);
            depanBelakang.vectorRotation(vertikal, angle_vertikal - angle_vertikal2);
            cameraRotation(vertikal, angle_vertikal - angle_vertikal2);
            angle_vertikal2 = angle_vertikal;
        } //panah bawah
        else if (keyCode == 40) {
            angle_samping -= 15f;
            vertikal.vectorRotation(samping, angle_samping - angle_samping2);
            depanBelakang.vectorRotation(samping, angle_samping - angle_samping2);
            cameraRotation(samping, angle_samping - angle_samping2);
            angle_samping2 = angle_samping;
        } //panah atas
        else if (keyCode == 38) {
            angle_samping += 15f;
            vertikal.vectorRotation(samping, angle_samping - angle_samping2);
            depanBelakang.vectorRotation(samping, angle_samping - angle_samping2);
            cameraRotation(samping, angle_samping - angle_samping2);
            angle_samping2 = angle_samping;
        }        
        //\
         else if(keyCode == 92){
        angle_depanBelakang -= 15f;
        samping.vectorRotation(depanBelakang, angle_depanBelakang-angle_depanBelakang2);
        vertikal.vectorRotation(depanBelakang, angle_depanBelakang-angle_depanBelakang2);
        cameraRotation(vertikal, angle_samping-angle_samping2);
        angle_depanBelakang2 = angle_depanBelakang;
        }
        //enter
        else if (keyCode == 10) {
            angle_depanBelakang += 15f;
            samping.vectorRotation(depanBelakang, angle_depanBelakang - angle_depanBelakang2);
            vertikal.vectorRotation(depanBelakang, angle_depanBelakang - angle_depanBelakang2);
            cameraRotation(vertikal, angle_samping - angle_samping2);
            angle_depanBelakang2 = angle_depanBelakang;
        }      
        else if (keyCode == 109) { // -
            angleplus--;
            if (angleplus <=0)
            {
                angleplus =1;
            }
        }
        else if (keyCode == 107) { // +
            angleplus++;
            if (angleplus >= 10)
            { 
                angleplus = 10;
            }
        }
        else if (keyCode == 111) { // /
            acc = acc - 0.2f;
            if (acc <= -3f)
            {
                acc = -3f;
            }
        }
        else if (keyCode == 106) { // *
            acc = acc + 0.2f;
            if (acc >= 3f)
            { 
                acc= 3;
            }
        }
    }
}
