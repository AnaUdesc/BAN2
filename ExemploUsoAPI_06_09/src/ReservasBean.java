
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
    private int id_cliente;
    private int id_animal;
    private Date data;
    private int id_plano_reserva;
    private String observacoes_reserva;
    private String status_reserva;
    private String tipo_acomodacao;

    // Construtor
    public ReservasBean(int id_cliente, int id_animal, Date data, int id_plano_reserva, 
                        String observacoes_reserva, String status_reserva, String tipo_acomodacao) {
        this.id_cliente = id_cliente;
        this.id_animal = id_animal;
        this.data = data;
        this.id_plano_reserva = id_plano_reserva;
        this.observacoes_reserva = observacoes_reserva;
        this.status_reserva = status_reserva;
        this.tipo_acomodacao = tipo_acomodacao;
    }

    // Getters e Setters
    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public Date getdata() {
        return data;
    }

    public void setdata(Date data) {
        this.data = data;
    }

    public int getId_plano_reserva() {
        return id_plano_reserva;
    }

    public void setId_plano_reserva(int id_plano_reserva) {
        this.id_plano_reserva = id_plano_reserva;
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

    public String getTipo_acomodacao() {
        return tipo_acomodacao;
    }

    public void setTipo_acomodacao(String tipo_acomodacao) {
        this.tipo_acomodacao = tipo_acomodacao;
    }
}

    
    
    /**
     * @return the ambulatorio
     */
    /*public AmbulatoriosBean getAmbulatorio() {
        return ambulatorio;
    }*/

    /**
     * @param //the ambulatorio to set
     */
    /*public void setAmbulatorio(AmbulatoriosBean ambulatorio) {
        this.ambulatorio = ambulatorio;
    }
    
    
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("codm: "+codm+" nome: "+nome+" idade: "+idade+
                " especialidade: "+especialidade+" cpf: "+cpf+" cidade: "+cidade+" nroa: "+nroa);
        if(ambulatorio!= null)
            sb.append(" andar: "+ambulatorio.getAndar()+" capacidade: "+ambulatorio.getCapacidade());
        return sb.toString();
    }   */     
}
