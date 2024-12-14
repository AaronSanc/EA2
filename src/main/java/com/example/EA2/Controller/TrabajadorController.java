/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.EA2.Controller;

import com.example.EA2.Repository.TrabajadorRepository;
import com.example.EA2.model.Trabajador;
import com.example.EA2.service.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author aaron
 */
@Controller
@RequestMapping("/trabajadores")
public class TrabajadorController {

    @Autowired
    private services trabajadorService; // Cambiado de TrabajadorRepository a services

    /**
     * Listar todos los trabajadores.
     *
     * @param model Modelo para pasar datos a la vista.
     * @return Vista de listado de trabajadores.
     */
    @GetMapping
    public String listarTrabajadores(Model model) {
        List<Trabajador> trabajadores = trabajadorService.listarTrabajadores();
        model.addAttribute("trabajadores", trabajadores);
        return "listado"; // Sin prefijo "trabajadores/"
    }

    /**
     * Mostrar el formulario para añadir un nuevo trabajador.
     *
     * @param model Modelo para pasar datos a la vista.
     * @return Vista de formulario.
     */
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("trabajador", new Trabajador());
        return "formulario"; // Sin prefijo "trabajadores/"
    }

    /**
     * Guardar un nuevo trabajador o actualizar uno existente.
     *
     * @param trabajador Trabajador a guardar.
     * @return Redireccionar a la lista de trabajadores.
     */
    @PostMapping
    public String guardarTrabajador(@ModelAttribute Trabajador trabajador) {
        trabajadorService.guardarTrabajador(trabajador); // Se llama al servicio para guardar
        return "redirect:/trabajadores"; // Redirige sin prefijo "trabajadores/"
    }

    /**
     * Editar los datos de un trabajador existente.
     *
     * @param id    ID del trabajador.
     * @param model Modelo para pasar datos a la vista.
     * @return Vista de formulario para editar.
     */
    @GetMapping("/editar/{id}")
    public String editarTrabajador(@PathVariable Long id, Model model) {
        Optional<Trabajador> trabajador = trabajadorService.encontrarTrabajadorPorId(id); // Se obtiene el trabajador por ID
        if (trabajador.isPresent()) {
            model.addAttribute("trabajador", trabajador.get());
            return "formulario"; // Sin prefijo "trabajadores/"
        } else {
            throw new IllegalArgumentException("ID no válido");
        }
    }

    /**
     * Eliminar un trabajador.
     *
     * @param id ID del trabajador a eliminar.
     * @return Redireccionar a la lista de trabajadores.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarTrabajador(@PathVariable Long id) {
        trabajadorService.eliminarTrabajador(id); // Se llama al servicio para eliminar
        return "redirect:/trabajadores"; // Redirige sin prefijo "trabajadores/"
    }
}