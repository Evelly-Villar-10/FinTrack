/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bianca
 */
public class RepositorioGenerico<T> {
    private List<T> registros = new ArrayList<>();

    public RepositorioGenerico() {
    }
    
    public RepositorioGenerico(List<T> registros) {
        this.registros = registros;
    }

    public List<T> getRegistros() {
        return registros;
    }

    
    
    
}
