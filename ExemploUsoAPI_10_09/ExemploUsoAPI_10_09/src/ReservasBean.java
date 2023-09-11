
import java.sql.Time;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Date;
import java.time.LocalTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ana Clara
 */
import java.util.Date;

public class ReservasBean {
    private int id_reserva;
    //private int id_cliente;
    private int id_animal;
    private int id_plano;
    private Date data;
    private LocalTime hora_entrada;
    private LocalTime hora_saida;
    private String observacoes_reserva;
    private String status_reserva;
    //private String tipo_acomodacao;

    // Construtor
    public ReservasBean(int id_animal, int id_plano, Date data, LocalTime hora_entrada, 
                        LocalTime hora_saida, String observacoes_reserva, String status_reserva) {
        //this.id_cliente = id_cliente;
        this.id_animal = id_animal;
        this.id_plano = id_plano;
        this.data = data;
        this.hora_entrada = hora_entrada;
        this.hora_saida = hora_saida;
        this.observacoes_reserva = observacoes_reserva;
        this.status_reserva = status_reserva;
    }

    // Getters e Setters
    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    /*public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }*/

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }
    
    public int getId_plano() {
        return id_plano;
    }

    public void setId_plano(int id_plano) {
        this.id_plano = id_plano;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public LocalTime getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(LocalTime hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public LocalTime getHora_saida() {
        return hora_saida;
    }

    public void setHora_saida(LocalTime hora_saida) {
        this.hora_saida = hora_saida;
    }

    
    public String getObservacoes_reserva() {
        return observacoes_reserva;
    }

    public void setObservacoes_reserva(String observacoes_reserva) {
        this.observacoes_reserva = observacoes_reserva;
    }

    public String getStatus_reserva() {
        return status_reserva;
    }

    public void setStatus_reserva(String status_reserva) {
        this.status_reserva = status_reserva;
    }

    /*public String getTipo_acomodacao() {
        return tipo_acomodacao;
    }

    public void setTipo_acomodacao(String tipo_acomodacao) {
        this.tipo_acomodacao = tipo_acomodacao;
    }*/

    
    
    /**
     * @return the ambulatorio
     */
    /*public AmbulatoriosBean getAmbulatorio() {
        return ambulatorio;
    }*/

    /**
     * @param //the ambulatorio to set
    
    public void setAmbulatorio(AmbulatoriosBean ambulatorio) {
        this.ambulatorio = ambulatorio;
    }
    */
    
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append(" ID Animal: "+id_animal+" Id Plano: "+id_plano+" Data: "+data+" Hora Entrada: "+hora_entrada+" Hora Saida: "+hora_saida+" Observacoes Reserva: "+observacoes_reserva+" Status Reserva: "+status_reserva);
        /*if(ambulatorio!= null)
            sb.append(" andar: "+ambulatorio.getAndar()+" capacidade: "+ambulatorio.getCapacidade());*/
        return sb.toString();
    }    
}