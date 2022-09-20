package com.grupo2.springboot.backend.apirest.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CorreoDTO {

    private List<String> destinatarios;
    private String titulo;
    private String contenido;
    private List<File> adjuntos;

    public CorreoDTO() {
        destinatarios = new ArrayList<>();
        adjuntos = new ArrayList<>();
    }

    public List<String> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<String> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public List<File> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(List<File> adjuntos) {
        this.adjuntos = adjuntos;
    }


}

