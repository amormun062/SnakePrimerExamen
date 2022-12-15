package com.politecnicomalaga.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Cuadrado {

    public enum Direccion {ARRIBA,ABAJO,DERECHA,IZQUIERDA};
    public enum Tema {CUADRADO_VERDE, CUADRADO_AZUL, CUADRADO_ROJO};

    private static final String IMG_CABEZA = "cabeza.png";
    private static final String IMG_CUADRADO_VERDE = "cuadrado.png";
    private static final String IMG_CUADRADO_AZUL = "cuadrado2.png";
    private static final String IMG_CUADRADO_ROJO = "cuadrado3.png";

    //Atributos del objeto Cuadrado
    private Texture img;
    private final Texture imgCabeza;
    private float posX,posY;
    private final int lado;
    private final Tema temaCuadrado;

    //Constructor 1
    public Cuadrado(float X, float Y, int l, Tema temaCuadrado) {
        posX = X;
        posY = Y;
        lado = l;
        this.temaCuadrado = temaCuadrado;
        asignarImagen(temaCuadrado);
        imgCabeza = new Texture(IMG_CABEZA);
    }

    //Constructor 2 (Se crea a partir de otro objeto Cuadrado)
    public Cuadrado(Cuadrado otro) {
        posX = otro.getPosX();
        posY = otro.getPosY();
        lado = otro.getLado();
        this.temaCuadrado = otro.temaCuadrado;
        asignarImagen(otro.temaCuadrado);
        imgCabeza = new Texture(IMG_CABEZA);
    }
    ///////////////////////////////////////////////////////////////////////////
    //Asignamos una imagen a partir del tema pasado por parametro
    public void asignarImagen(Tema temaCuadrado){
        switch (temaCuadrado){
            case CUADRADO_VERDE:
                img = new Texture(IMG_CUADRADO_VERDE);
                break;
            case CUADRADO_AZUL:
                img = new Texture(IMG_CUADRADO_AZUL);
                break;
            case CUADRADO_ROJO:
                img = new Texture(IMG_CUADRADO_ROJO);
                break;
            default:
                img = null;
                break;
        }
    }

    //Pintarse
    public void pintarse(SpriteBatch sb, Boolean isCabeza) {
        sb.begin();
        if (isCabeza)
            sb.draw(imgCabeza,posX,posY,lado,lado);
        else
            sb.draw(img,posX,posY,lado,lado);
        sb.end();
    }

    //Moverse
    public void moverse(Direccion dir) {
        switch (dir) {
            case ABAJO: posY = posY - lado;
            break;
            case ARRIBA:posY = posY + lado;
            break;
            case DERECHA: posX = posX + lado;
            break;
            case IZQUIERDA: posX = posX - lado;
            break;
        }
    }

    //Colisiona
    public boolean colisiona(Cuadrado otro) {
        return (otro.getPosX()==posX && otro.getPosY()==posY);
    }

    //Getter PosX
    public float getPosX() {
        return posX;
    }

    //Getter PosY
    public float getPosY() {
        return posY;
    }

    //Getter Lado
    public int getLado() {
        return lado;
    }

    //DISPOSE imagen del cuadrado
    public void dispose() {
        if (img != null) img.dispose();
    }

}
