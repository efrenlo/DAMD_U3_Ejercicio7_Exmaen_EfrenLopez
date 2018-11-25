package mx.edu.ittepic.dadm_u3_ejercicio7;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.CountDownTimer;

public class Nave {

    private Bitmap icono;
    float x,y;
    int desplazamiento;
    CountDownTimer time,time2,time3,timerBala,timeCir;
    private boolean visible;



    //oblifara  podener cardenadas
    public Nave(int resource, float _x, float _y, final Lienzo l,final Nave n){
        icono = BitmapFactory.decodeResource(l.getResources(),resource);
        x = _x;
        y = _y;
        visible = true;

        ///////////////////timer macianos
        time = new CountDownTimer(1000,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                y+=desplazamiento;



                if (y>= l.getHeight()){
                    y=1;
                  //  l.disMa = new Nave(R.drawable.bal,180,150,l);
                    //l.disMa.moverMarBala(20);

                }



                l.invalidate();
            }

            @Override
            public void onFinish() {

                start();

            }
        };



        //////////////////////////////////////////timer para la bala
        timerBala = new CountDownTimer(1000,100) {
            @Override
            public void onTick(long millisUntilFinished) {


                if (y==50){
                    y=1450;
                    x=l.punto;
                    //l.dispa = new Nave(R.drawable.bala,x,1450,l,n);
                    //l.dispa.moverDis(20);
                }else{
                    y+=-desplazamiento;

                }


                l.invalidate();
                /*if (y>= l.getHeight()){
                    y=1300;
                }

                if(y==-50){
                    y=1200;
                    x=l.punto;
                    //l.dispa = new Nave(R.drawable.bala,x,1450,l);
                    //l.dispa.moverDis(-20);
                }else{
                    y+=-desplazamiento;
                }*/
            }

            @Override
            public void onFinish() {
                start();
            }
        };

        timeCir = new CountDownTimer(1000,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                y+=desplazamiento;

                if (y>= l.getHeight()){
                    y=n.getY()+180;
                }


                l.invalidate();
            }

            @Override
            public void onFinish() {
                start();
            }
        };


    }



    public void pintar(Canvas c, Paint p){
        if(visible)c.drawBitmap(icono,x,y,p);
    }

    public void hacerVisible(boolean v){
        visible = v;
    }

    public void moverDis (int desplaza){//le asigna a desplazamiento del valor de desplaza
        desplazamiento = desplaza;
        timerBala.start();
    }
/////////////////////////////////marcuano
    public void moverMar (int desplaza){//le asigna a desplazamiento del valor de desplaza
        desplazamiento = desplaza;
        time.start();
    }

    ////////////////////////////bala del marciano movimento
    public void moverMarBala (int desplaza){//le asigna a desplazamiento del valor de desplaza
        desplazamiento = desplaza;
        timeCir.start();
    }

    ////////////////////// hacer mover a la balaaaa

    public boolean estaEnArea(float xp, float yp){ //xp y yp son del toque

        if (!visible) return false;

        float x2,y2;
        x2=x+icono.getWidth();
        y2=y+icono.getHeight();

        if(xp>=x && xp<=x2){
            if(yp>=y &&yp<=y2){
                return true;
            }
        }


        //metodo mover

        return false;
    }
    //para saber si se movio ocupamos eiste metodo

    public void mover(float xp){
        x=xp-(icono.getWidth()/2);
        //y=yp-(icono.getHeight()/2);

    }



    public boolean colision(Nave objetoB){
        float x2=x+icono.getWidth();
        float y2=y+icono.getHeight();

        if(objetoB.estaEnArea(x2, y)){
            //revisando caso 1
            return true;
        }
        if (objetoB.estaEnArea(x,y)){
            //caso 2
            return true;
        }
        //caso2 x,y
        //caso4 x y2

        if (objetoB.estaEnArea(x2,y2)){
            //revisando caso 3
            return true;
        }

        if (objetoB.estaEnArea(x,y2)){
            //revisando casoo4
            return true;
        }

        return false;

    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
}
