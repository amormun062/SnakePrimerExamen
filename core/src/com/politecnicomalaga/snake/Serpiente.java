package com.politecnicomalaga.snake;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import javax.swing.Spring;

public class Serpiente {

    //Atributos
    ArrayList<Cuadrado> miCuerpo;
    Cuadrado.Direccion miDireccion;


    //Métodos
    public Serpiente() {
        miCuerpo = new ArrayList<Cuadrado>();
        miDireccion = Cuadrado.Direccion.ARRIBA;
        Cuadrado cabeza;

        switch (random(0,2)){
            case 0:
                cabeza = new Cuadrado(200,200,20, Cuadrado.Tema.CUADRADO_VERDE);
                break;
            case 1:
                cabeza = new Cuadrado(200,200,20, Cuadrado.Tema.CUADRADO_AZUL);
                break;
            case 2:
                cabeza = new Cuadrado(200,200,20, Cuadrado.Tema.CUADRADO_ROJO);
                break;
            default:
                cabeza = null;
                break;
        }

        miCuerpo.add(cabeza);
    }

    //moverse
    public void moverse() {
        this.crecer();
        Cuadrado elemento = miCuerpo.remove(miCuerpo.size()-1);
        elemento.dispose();
    }

    //crecer
    public void crecer() {
        Cuadrado nuevaCabeza, cabeza;
        cabeza = miCuerpo.get(0);
        nuevaCabeza = new Cuadrado(cabeza);
        nuevaCabeza.moverse(miDireccion);

        miCuerpo.add(0,nuevaCabeza);
    }

    public void pintarse(SpriteBatch sb) {
        for (int i=0; i<miCuerpo.size(); i++){
            if (i == 0)
                miCuerpo.get(i).pintarse(sb, true);
            else
                miCuerpo.get(i).pintarse(sb, false);
        }
    }

    public boolean colisiona() {
        Cuadrado cabeza = miCuerpo.get(0);
        for (int i=4;i<miCuerpo.size();i++) {
            if (cabeza.colisiona(miCuerpo.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean estaDentro(int limiteMinX, int limiteMaxX, int limiteMinY, int limiteMaxY) {
        Cuadrado cabeza = miCuerpo.get(0);
        return (cabeza.getPosX()>limiteMinX && cabeza.getPosX()<limiteMaxX-cabeza.getLado() &&
                cabeza.getPosY()>limiteMinY && cabeza.getPosY()<limiteMaxY-cabeza.getLado());
    }

    public float getCabezaX() {
        return miCuerpo.get(0).getPosX();
    }

    public float getCabezaY() {
        return miCuerpo.get(0).getPosY();
    }

    public void cambiaDir(Cuadrado.Direccion nuevaDir) {
        miDireccion = nuevaDir;
    }

    public void dispose() {
       for (Cuadrado c: miCuerpo) {
           c.dispose();
       }
    }

}
