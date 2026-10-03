/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bianca.fintrack.model;

import com.bianca.fintrack.controller.FinTracker;
import com.bianca.fintrack.exceptions.EntradaInvalidaException;

/**
 *
 * @author bianca
 */
/*Interface para usar o fintracker nas telas */
public interface FinTrackerInterface {
    void setFinTracker(FinTracker fintracker)throws EntradaInvalidaException;
}
