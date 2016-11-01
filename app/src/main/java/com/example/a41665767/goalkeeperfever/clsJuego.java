package com.example.a41665767.goalkeeperfever;

import android.util.Log;

import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Label;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCColor3B;
import org.cocos2d.types.CCSize;

/**
 * Created by 41665767 on 11/10/2016.
 */
public class clsJuego {
    CCGLSurfaceView _VistaDelJuego;
    CCSize PantallaDelDispositivo;
    Sprite Pelota;
    Sprite Arco;
    Sprite Guantes;
    Sprite Fondo;
    Label lblPuntaje;

    public clsJuego(CCGLSurfaceView VistaDelJuego){
        Log.d("Bob", "Comienza el constructor de la clase");
        _VistaDelJuego = VistaDelJuego;
    }

    public void ComenzarJuego(){
        Log.d("Comenzar", "Comienza el juego");
        Director.sharedDirector().attachInView(_VistaDelJuego);

        PantallaDelDispositivo = Director.sharedDirector().displaySize();
        Log.d("Comenzar", "Pantalla del dispositivo - Ancho: " + PantallaDelDispositivo.width + " - Alto: " + PantallaDelDispositivo.height);

        Log.d("Comenzar", "Le digo al director que ejecute la escena");
        Director.sharedDirector().runWithScene(EscenaDelJuego());
    }

    private Scene EscenaDelJuego(){
        Log.d("Escena del juego" , "Comienza el armado de la escena del juego");

        Log.d("Escena del juego" , "Declaro la escena a devolver");
        Scene EscenaADevolver;
        EscenaADevolver = Scene.node();

        Log.d("Escena del juego" , "Declaro la instancia que va a tener a la capa del fondo");
        CapaDeFondo MiCapaFondo;
        MiCapaFondo = new CapaDeFondo();

        Log.d("Escena del juego" , "Declaro la instancia que va a tener la capa del jugador, pelota y arquero");
        CapaDelFrente MiCapaFrente;
        MiCapaFrente = new CapaDelFrente();

        Log.d("Escena del juego" , "Agrego a la escena la capa del fondo mas atras, y la del frente mas adelante");
        EscenaADevolver.addChild(MiCapaFondo, -10);
        EscenaADevolver.addChild(MiCapaFrente, 10);

        return EscenaADevolver;
    }

    class CapaDeFondo extends Layer{
        public CapaDeFondo(){
            Log.d("CapaDeFondo", "Comienza el constructor de la capa de fondo");

            Log.d("CapaDeFondo", "Ubico el fondo del juego");
            PonerFondo();
            PonerPuntaje();
        }

        private void PonerFondo(){
            Log.d("PonerFondo", "Comienzo a poner la imagen del fondo");

            Log.d("PonerFondo", "Instancio el sprite");
            Fondo = Sprite.sprite("fondo.png");

            Log.d("PonerFondo","La ubico en el centro de la pantalla");
            Fondo.setPosition(PantallaDelDispositivo.width/2, PantallaDelDispositivo.height/2);

            Log.d("PonerFondo", "Agrando la imagen al doble");
            Fondo.runAction(ScaleBy.action(0.01f, 2.0f, 2.0f));

            //hjkjh
            Log.d("PonerFondo", "Lo agrego a la capa");
            super.addChild(Fondo);
        }

        private void PonerPuntaje(){
            Log.d("PonerPuntaje", "Comienzo a poner el puntaje");
            lblPuntaje = Label.label("Puntaje: ", "Microsoft Sans Serif", 45);

            Log.d("PonerPuntaje", "Pongo el color");
            CCColor3B ColorPuntaje = new CCColor3B(255,255,255);
            lblPuntaje.setColor(ColorPuntaje);

            Log.d("PonerPuntaje", "Lo ubico en la posicion deseada");
            float AltoPuntaje = lblPuntaje.getHeight();
            lblPuntaje.setPosition(PantallaDelDispositivo.width/2, PantallaDelDispositivo.height - AltoPuntaje/2);

            Log.d("PonerPuntaje", "Lo agrego a la capa");
            super.addChild(lblPuntaje);
        }

    }
    class CapaDelFrente extends Layer{
        public CapaDelFrente(){
            Log.d("CapaDelFrente", "Comienza el constructor de la capa del frente");

            Log.d("CapaDelFrente", "Pongo los elementos en su posicion inicial");
            InicializarElementos();
        }

        private void InicializarElementos(){
            Log.d("PonerElementosIniciales", "Comienzo a poner los elementos en su posicion inicial");

            Log.d("PonerElementosIniciales", "Instancio los Sprites");
            Pelota = Sprite.sprite("pelota.png");
            Pelota.runAction(ScaleBy.action(0.01f, 0.75f, 0.75f));
            Arco = Sprite.sprite("arco.png");
            Arco.runAction(ScaleBy.action(0.01f, 2.0f, 2.0f));
            Guantes = Sprite.sprite("guantes.png");
            Guantes.runAction(ScaleBy.action(0.01f, 1.5f, 1.5f));

            float PosicionInicialX;
            Log.d("PonerElementosIniciales", "Obtengo la mitad del ancho de la pantalla");
            PosicionInicialX = PantallaDelDispositivo.width/2;

            Log.d("PonerElementosIniciales", "Inicializo en X: " + PosicionInicialX + " - Y: Cualquiera");
            Pelota.setPosition(PosicionInicialX, 300);
            Guantes.setPosition(PosicionInicialX, 800);
            Arco.setPosition(PosicionInicialX, 800);

            Log.d("PonerElementosIniciales", "Lo agrego a la capa");
            super.addChild(Pelota);
            super.addChild(Guantes);
            super.addChild(Arco);
        }
    }
}
