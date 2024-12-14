/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.EA2.service;

/**
 *
 * @author aaron
 */

import com.example.EA2.Repository.TrabajadorRepository;
import com.example.EA2.model.Trabajador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class services {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    public List<Trabajador> listarTrabajadores() {
        return trabajadorRepository.findAll();
    }

    public Optional<Trabajador> encontrarTrabajadorPorId(Long id) {
        return trabajadorRepository.findById(id);
    }

    public Trabajador guardarTrabajador(Trabajador trabajador) {
        return trabajadorRepository.save(trabajador);
    }

    public void eliminarTrabajador(Long id) {
        trabajadorRepository.deleteById(id);
    }
}