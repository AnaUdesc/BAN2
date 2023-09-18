
import java.util.HashSet;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ana Clara
 */
public class PlanosBean {
   private String nome_plano;    
   private String descricao_plano;
   private int duracao_plano;
   private String tipo_acomodacao;
   private double preco_plano;        //revisar tipo variavel
   private String restricao_especie;
   private String disponibilidade; //revisar tipo variavel
    private int id_plano;

   public PlanosBean(String nome_plano, String descricao_plano, int duracao_plano, String tipo_acomodacao, double preco_plano, String restricao_especie, String disponibilidade) {
       this.nome_plano = nome_plano;
       this.descricao_plano = descricao_plano;
       this.duracao_plano = duracao_plano;
       this.tipo_acomodacao = tipo_acomodacao;
       this.preco_plano = preco_plano;
       this.restricao_especie = restricao_especie;
       this.disponibilidade = disponibilidade;
   }

   /* PlanosBean(String nome_plano, String descricao_plano, String duracao_plano, String tipo_acomodacao, double preco_plano, String restricao_especie, String disponibilidade) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
   
    
     // Getters e Setters para o campo id
    public int getId_plano() {
    return id_plano;
}

    public void setId_plano(int id_plano) {
        this.id_plano = id_plano;
    }
 
   

    /**
     * @return the nome_plano
     */
    public String getNome_plano() {
        return nome_plano;
    }

    /**
     * @param nome_plano the nome_plano to set
     */
    public void setNome_plano(String nome_plano) {
        this.nome_plano = nome_plano;
    }

    /**
     * @return the des
     */
    public String getDescricao_plano() {
        return descricao_plano;
    }

    /**
     * @param descricao_plano the descricao_plano to set
     */
    public void setDescricao_plano(String descricao_plano) {
        this.descricao_plano = descricao_plano;
    }
	
	 /**
     * @return the duracao_plano
     */
    
    public int getDuracao_plano() {
        return duracao_plano;
    }

    /**
     * @param duracao_plano the duracao_plano to set
     */
    public void setDuracao_plano(int duracao_plano) {
        this.duracao_plano = duracao_plano;
    }

    /**
     * @return the tipo_acomodacao
     */
    public String getTipo_acomodacao() {
        return tipo_acomodacao;
    }

    /**
     * @param tipo_acomodacao the tipo_acomodacao to set
     */
    public void setTipo_acomodacao(String tipo_acomodacao) {
        this.tipo_acomodacao = tipo_acomodacao;
    }

     /**
     * @return the preco_plano
     */
    public double getPreco_plano() {
        return preco_plano;
    }

    /**
     * @param preco_plano the preco_plano to set
     */
    public void setPreco_plano(double preco_plano) {
        this.preco_plano = preco_plano;
    }

    /**
     * @return the restricao_especie
     */
    public String getRestricao_especie() {
        return restricao_especie;
    }

    /**
     * @param restricao_especie the restricao_especie to set
     */
    public void setRestricao_especie(String restricao_especie) {
        this.restricao_especie = restricao_especie;
    }

    /**
     * @return the disponibilidade
     */
    public String getDisponibilidade() {
        return disponibilidade;
    }

    /**
     * @param disponibilidade the disponibilidade to set
     */
    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    //REVISAR ESSE TRECHO
    int Duracao_plano() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    
    */
    
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("Nome plano: "+nome_plano+" Descricao plano: "+descricao_plano+" Duracao plano: "+duracao_plano+
                " Tipo acomodacao: "+tipo_acomodacao+" Preco plano: "+preco_plano+" Restricao Especie: "+restricao_especie+" Disponibilidade: "+disponibilidade);
        /*if(ambulatorio!= null)
            sb.append(" andar: "+ambulatorio.getAndar()+" capacidade: "+ambulatorio.getCapacidade());*/
        return sb.toString();
    }     
}
