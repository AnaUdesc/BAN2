
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
public class ReservasBean {
   private Date data_reserva;    
   private LocalTime hora_entrada;
   private LocalTime hora_saida;
    /*ADICIONAR A CHAVE ESTRANGEIRA DO CLIENTE (CPF)*/

   public ReservasBean(Date data_reserva, LocalTime hora_entrada, LocalTime hora_saida) {
       this.data_reserva = data_reserva;
       this.hora_entrada = hora_entrada;
       this.hora_saida = hora_saida;
   }
   

    /**
     * @return the data_reserva
     */
    public Date getData_reserva() {
        return data_reserva;
    }

    /**
     * @param data_reserva the data_reserva to set
     */
    public void setData_reserva(Date data_reserva) {
        this.data_reserva = data_reserva;
    }

    /**
     * @return the hora_entrada
     */
    public LocalTime getEspecie_animal() {
        return hora_entrada;
    }

    /**
     * @param hora_entrada the hora_entrada to set
     */
    public void setHora_entrada(LocalTime hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    /**
     * @return the hora_saida
     */
    public LocalTime getHora_saida() {
        return hora_saida;
    }

    /**
     * @param hora_saida the hora_saida to set
     */
    public void setRaca(LocalTime hora_saida) {
        this.hora_saida = hora_saida;
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
