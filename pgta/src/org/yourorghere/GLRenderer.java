/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

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
    int gerakin = 0;
    int gerakin2 = 0; 
    boolean angkat = false;
    boolean maju = false;

    public void display(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();
        GLU glu = new GLU();
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST);
        // Reset the current matrix to the "identity"
        // Move the "drawing cursor" around
        
        gl.glLoadIdentity();
        glu.gluLookAt(Cx, Cy, Cz, Lx, Ly, Lz, vertikal.x, vertikal.y, vertikal.z);
        //Pasif Objek
        Objek.air(gl);
        gl.glTranslatef(5f, 0.5f, -10f);
        Objek.bridge(drawable);
        Objek.roadpasif(drawable);
        gl.glTranslatef(0f, 0f, -3f);
        Objek.bridge(drawable);
        gl.glTranslatef(-15f, 0f, 3f);
        Objek.roadpasif(drawable);
        Objek.bridge(drawable);
        gl.glTranslatef(0f, 0f, -3f);
        Objek.bridge(drawable);
        
        //Kapal
        gl.glLoadIdentity();
        glu.gluLookAt(Cx, Cy, Cz, Lx, Ly, Lz, vertikal.x, vertikal.y, vertikal.z);
        //Maju Kapal
        gl.glTranslatef(0f, -1f, -10f);
        if (maju) {
            drawmaju(drawable, 66);
        } else if (maju == false && gerakin >= 5) {
            drawmaju2(drawable, 66);
        }
//        gl.glRotatef(silinderAngle, x, y, z);
//        gl.glTranslatef(0, 0, 0f);
        Objek.Kapal(drawable);
       
        gl.glLoadIdentity();
        glu.gluLookAt(Cx, Cy, Cz, Lx, Ly, Lz, vertikal.x, vertikal.y, vertikal.z);
        // jalan jembatan kiri
        gl.glTranslatef(-5f, 0.5f, -10.0f);
        gl.glTranslatef(-1f, 0, 0);// tiang depan
        gl.glRotatef(silinderAngle, x, y, z);
        Objek.pillar(drawable);
        gl.glTranslatef(0, 0, -3f); // tiang belakang
        Objek.pillar(drawable);
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
        gl.glTranslatef(0, 0, -3f); // tiang belakang
        Objek.pillar(drawable);
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

    void drawingkiri(GLAutoDrawable drawable, int KeyCode) {
        GL gl = drawable.getGL();
        if (KeyCode == 32) {
            gl.glRotatef(angle, 0, 0, 1);
        }
        if (angle <= 45) {
            angle++;
        }
    }
    
    void drawingkanan(GLAutoDrawable drawable, int KeyCode) {
        GL gl = drawable.getGL();
        gl.glTranslatef(5f, 0, 0);
        if (KeyCode == 32) {
            gl.glRotatef(angle2, 0, 0, -1);
        }
        if (angle2 <= 45) {
            angle2++;
        }
        gl.glTranslatef(-5f, 0, 0);
    }

    void drawdownkiri(GLAutoDrawable drawable, int KeyCode) {
        GL gl = drawable.getGL();
        if (KeyCode == 32) {
            gl.glRotatef(angle, 0, 0, 1);
        }
        if (angle >= 0) {
            angle--;
        }
    }

    void drawdownkanan(GLAutoDrawable drawable, int KeyCode) {
        GL gl = drawable.getGL();
        gl.glTranslatef(5f, 0, 0);
        if (KeyCode == 32) {
            gl.glRotatef(angle2, 0, 0, -1);
        }
        if (angle2 >= 0) {
            angle2--;
        }
        gl.glTranslatef(-5f, 0, 0);
    }

        
    void drawmaju(GLAutoDrawable drawable, int KeyCode) {
        GL gl = drawable.getGL();
        gl.glTranslatef(0, 0, 5f);
        if (KeyCode == 66) {
//          gl.glRotatef(gerakin, 3, 0, 0);
            gl.glTranslatef(0, 0, gerakin);
        }
        if (gerakin <= 19) {
            gerakin++;
        }
        gl.glTranslatef(0, 0, 0);
    }

    void drawmaju2(GLAutoDrawable drawable, int KeyCode) {
        GL gl = drawable.getGL();
        gl.glTranslatef(0, 0, 19);
        if (KeyCode == 78) {
//          gl.glRotatef(gerakin2, 0, 0, -1);
            gl.glTranslatef(0, 0, gerakin);
        }
        if (gerakin2 >= 10) {
            gerakin2--;
        }
        gl.glTranslatef(0, 0, -18f);
    }


    void Key_Pressed(int keyCode) {
        //huruf Q
        if (keyCode == 81) {
            vectorMovement(depanBelakang, 2f, 1f);
        } //huruf E
        else if (keyCode == 69) {
            vectorMovement(depanBelakang, 2f, -1f);
        } //huruf D
        else if (keyCode == 65) {
            vectorMovement(samping, 2f, 1f);
        } //huruf A
        else if (keyCode == 68) {
            vectorMovement(samping, 2f, -1f);
        } //huruf W
        else if (keyCode == 87) {
            vectorMovement(vertikal, 2f, 1f);
        } //panah S
        else if (keyCode == 83) {
            vectorMovement(vertikal, 2f, -1f);
        } //J
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
        } //tombol banckspace
        else if (keyCode == 98) {
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
        } else if (keyCode == 66) { //B
            if (maju) {
                maju = false;
            } else {
                maju = true;
            }
        } else if (keyCode == 78) { //N
            if (maju) {
                maju = false;
            } else {
                maju = true;
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
        } //enter
        else if (keyCode == 10) {
            angle_depanBelakang += 15f;
            samping.vectorRotation(depanBelakang, angle_depanBelakang - angle_depanBelakang2);
            vertikal.vectorRotation(depanBelakang, angle_depanBelakang - angle_depanBelakang2);
            cameraRotation(vertikal, angle_samping - angle_samping2);
            angle_depanBelakang2 = angle_depanBelakang;
        }
    }
}
