package mx.edu.ittepic.dadm_u3_ejercicio7;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

public class Lienzo extends View {

    Nave n4,punteN,puntero,puntero2,dis,n1,n2,n3,disMa,disMa2,disMa3,dispa,relo,isla,isla2,win,over,jefe,jefeDisparo,jefeDisparo2;
    MainActivity musicaInicio,musicaPerdi,corrido;
    String Mensaje,nuevo,nuevoPierde;
    int ganador=0,eliJefe;
    int gana=00000,pierde=10000,pararMusica;
    float punto,puntoy;


    //por cada cosa que recibes tiens que enviar un envio


    public Lienzo(Context context) {
        super(context);
        n1 = new Nave(R.drawable.nave,10,50,this,n1);//mandamos pa
        n2 = new Nave(R.drawable.nave,300,100,this,n2);//mandamos pa
        n3 = new Nave(R.drawable.nave,700,100,this,n3);
        jefe = new Nave(R.drawable.jefe,300,100,this,jefe);
        nuevo="puntaje "+gana;
        nuevoPierde="vida: "+pierde;


        /////////7nave


        n4 = new Nave(R.drawable.pri,500,1450,this,n4);
        dis = new Nave(R.drawable.bala,600,1450,this,n4);


        //disM = new Nave(R.drawable.bal,80,150,this);
        disMa = new Nave(R.drawable.bal,140,100,this,n1);
        disMa2 = new Nave(R.drawable.bal,420,100,this,n2);
        //disMa2 = new Nave(R.drawable.bal,450,100,this);
        disMa3 = new Nave(R.drawable.bal,825,100,this,n3);
        jefeDisparo = new Nave(R.drawable.bal,350,120,this,jefe);
        jefeDisparo2 = new Nave(R.drawable.bal,650,120,this,jefe);

        //disMaOtro = new Nave(R.drawable.bal,180,100,this);

        relo = new Nave(R.drawable.recar,310,700,this,relo);
        over = new Nave(R.drawable.over,180,250,this,over);
        win = new Nave(R.drawable.win,100,300,this,win);

        isla = new Nave(R.drawable.isla,110,400,this,isla);
        isla2 = new Nave(R.drawable.isla,610,300,this,isla);

        puntero=null;
        punteN = null;


        /////////////////////////////// movivimientos y hacer visibleñs objetos
        relo.hacerVisible(false);
        win.hacerVisible(false);
        over.hacerVisible(false);

        jefe.hacerVisible(false);
        jefeDisparo.hacerVisible(false);
        jefeDisparo2.hacerVisible(false);

        isla.moverMar(15);
        isla2.moverMar(13);

        n1.moverMar(18);
        n2.moverMar(15);
        n3.moverMar(8);
        jefe.moverMar(22);
        //jefe.time.cancel();

        dis.moverDis(20);

        disMa.moverMarBala(30);
        disMa2.moverMarBala(28);
        disMa3.moverMarBala(20);
        jefeDisparo.moverMarBala(35);
        jefeDisparo2.moverMarBala(35);
        //disMaOtro.moverMarBala(15);

        musicaInicio = (MainActivity) context;
        musicaInicio.empiezo();
        musicaPerdi = (MainActivity) context;
        corrido= (MainActivity) context;

        Mensaje="";
    }


    protected void onDraw(Canvas c){
        super.onDraw(c);
        Paint p = new Paint();
        p.setTextSize(50);
        p.setColor(Color.RED);

        c.drawColor(Color.BLUE); //cmbiiar color atodo el onddrow
        c.drawText(Mensaje,100,150,p);
        if(ganador==3){
            c.drawText(nuevo,100,50,p);
            c.drawText(nuevoPierde,600,50,p);
        }


        isla.pintar(c,p);
        isla2.pintar(c,p);
        dis.pintar(c,p);
        //disM.pintar(c,p);
        disMa.pintar(c,p);
        disMa2.pintar(c,p);
        disMa3.pintar(c,p);
        jefeDisparo.pintar(c,p);
        jefeDisparo2.pintar(c,p);
        n1.pintar(c,p);
        n2.pintar(c,p);
        n3.pintar(c,p);
        n4.pintar(c,p);
        // if(ganador==3){

        jefe.pintar(c,p);
        //}

        win.pintar(c,p);
        over.pintar(c,p);
        relo.pintar(c,p);

        puntero=dis;
        punteN=n4;
        /////////////////////77disparar
        if(puntero.colision(n1)&&puntero==dis){

            ganador++;
            n1.hacerVisible(false);
            disMa.hacerVisible(false);



        }
        if(puntero.colision(n2)&&puntero==dis){

            ganador++;
            n2.hacerVisible(false);
            disMa2.hacerVisible(false);

        }
        if(puntero.colision(n3)&&puntero==dis){

            ganador++;

            n3.hacerVisible(false);
            disMa3.hacerVisible(false);


        }
        ///////////////////elimitar jefe
        if(puntero.colision(jefe)&&puntero==dis){
            gana=100+gana;
            nuevo="puntaje "+gana;
        }

        /////////////////////////////////////////////////////////aparesza el jefe
        if(ganador==3){
            jefe.hacerVisible(true);
            //jefe.time.start();
            jefeDisparo.hacerVisible(true);
            jefeDisparo2.hacerVisible(true);


        }
        /////////////////////////////////////////////condicion para destruir al jefe
        if(gana==10500){
            jefe.hacerVisible(false);
            jefeDisparo.hacerVisible(false);
            jefeDisparo2.hacerVisible(false);
            win.hacerVisible(true);
            dis.hacerVisible(false);
            relo.hacerVisible(true);


            //musicaInicio.Pararempiezo();
            //corrido.corrido();


        }

        //////////////////////////////para qu me elimne el jefe
        if(pierde==0){
            over.hacerVisible(true);
            n4.hacerVisible(false);
            relo.hacerVisible(true);
            dis.hacerVisible(false);
            jefe.hacerVisible(false);
            jefeDisparo2.hacerVisible(false);
            jefeDisparo.hacerVisible(false);

            isla.time.cancel();
            isla2.time.cancel();
            //Mensaje="puntos: "+pierde;
            // musicaInicio.Pararempiezo();
            //corrido.corrido();


        }

        ////////////////////////77si te tocan las balas del marciano
        if(punteN.colision(disMa)&&punteN==n4){
            over.hacerVisible(true);
            n4.hacerVisible(false);
            relo.hacerVisible(true);
            dis.hacerVisible(false);
            dis.hacerVisible(false);
            dis.timerBala.cancel();


        }
        if(punteN.colision(disMa2)&&punteN==n4){
            over.hacerVisible(true);
            n4.hacerVisible(false);
            relo.hacerVisible(true);
            dis.hacerVisible(false);

            dis.timerBala.cancel();

        }
        if(punteN.colision(disMa3)&&punteN==n4){
            over.hacerVisible(true);
            n4.hacerVisible(false);
            relo.hacerVisible(true);
            dis.hacerVisible(false);
            dis.timerBala.cancel();



        }
        /////////////////////////////nave marciana
        if(punteN.colision(n1)&&punteN==n4){

            over.hacerVisible(true);
            n4.hacerVisible(false);
            relo.hacerVisible(true);
            dis.hacerVisible(false);
            dis.timerBala.cancel();




        }
        if(punteN.colision(n2)&&punteN==n4){
            over.hacerVisible(true);
            n4.hacerVisible(false);
            relo.hacerVisible(true);
            dis.hacerVisible(false);
            dis.timerBala.cancel();



        }
        if(punteN.colision(n3)&&punteN==n4){
            over.hacerVisible(true);
            n4.hacerVisible(false);
            relo.hacerVisible(true);
            dis.hacerVisible(false);
            dis.timerBala.cancel();

        }
        //////////////////////////////////////////////si te toca el jefe
        if(punteN.colision(jefe)&&punteN==n4){
            pierde=pierde-100;
            nuevoPierde="vida "+pierde;
        }


        ////////////////////////////////////////////si te tocan las balas del jefe
        if(punteN.colision(jefeDisparo)&&punteN==n4){
            pierde=pierde-100;
            nuevoPierde="vida "+pierde;

        }
        if(punteN.colision(jefeDisparo2)&&punteN==n4){
            pierde=pierde-100;
            nuevoPierde="vida "+pierde;

        }

    }

    public boolean onTouchEvent(MotionEvent e){
        float xp= e.getX();
        float yp=e.getY();
        //mensaje="no se ha tocado onjeto";

        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(n4.estaEnArea(xp,yp)){//si el toque esta en el area
                    //mensaje="se toco icono1";
                    //puntero apunta a icono1

                    //disM = new Nave(R.drawable.bal,180,yp,this);
                    // dis = new Nave(R.drawable.bala,xp,yp,this);
                    punteN=n4;
                    puntero=dis;
                    //md.stop();


                }
                if(relo.estaEnArea(xp,yp)){
                    Intent otra = new Intent(musicaInicio,MainActivity.class);
                    musicaInicio.startActivity(otra);
                    musicaInicio.Pararempiezo();

                }

                // if (xp >= 100 && yp >= 900) {
                //dis.moverDis(-30);


                //disM.moverMarBala(yp+10);

                //}

                break;

            case MotionEvent.ACTION_MOVE:



                if(punteN!=null){

                    punteN.mover(xp);
                    punto=punteN.getX()+90;
                    // puntoy=punteN.getY();


                }
                break;

            case MotionEvent.ACTION_UP:
                puntero=null;//para que se desactive la seleccion anterior
                punteN=null;


                break;
        }
        invalidate();
        return true;
    }
}
