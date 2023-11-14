
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
public class AnimaisBean {
   private String nome_animal;    
   private String especie_animal;
   private String raca;
   private int idade;
   private String sexo_animal;
   private String observacao;
   private int id_animal;
   private int cpf_cliente;

   public AnimaisBean(String nome_animal, String especie_animal, String raca, int idade, String sexo_animal, String observacao, int cpf_cliente) {
       this.nome_animal = nome_animal;
       this.especie_animal = especie_animal;
       this.raca = raca;
       this.idade = idade;
       this.sexo_animal = sexo_animal;
       this.observacao = observacao;
       this.cpf_cliente = cpf_cliente;
   }
   
    // Getters e Setters para o campo id
    public int getId_animal() {
    return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    /**
     * @return the nome
     */
    public String getNome_animal() {
        return nome_animal;
    }

    /**
     * @param nome_animal the nome_animal to set
     */
    public void setNome_animal(String nome_animal) {
        this.nome_animal = nome_animal;
    }

    /**
     * @return the especie
     */
    public String getEspecie_animal() {
        return especie_animal;
    }

    /**
     * @param especie_animal the especie_animal to set
     */
    public void setEspecie_animal(String especie_animal) {
        this.especie_animal = especie_animal;
    }

    /**
     * @return the raca
     */
    public String getRaca() {
        return raca;
    }

    /**
     * @param raca the raca to set
     */
    public void setRaca(String raca) {
        this.raca = raca;
    }

     /**
     * @return the idade
     */
    public int getIdade() {
        return idade;
    }

    /**
     * @param idade the idade to set
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }

    /**
     * @return the sexo_animal
     */
    public String getSexo_animal() {
        return sexo_animal;
    }

    /**
     * @param sexo_animal the sexo_animal to set
     */
    public void setSexo_animal(String sexo_animal) {
        this.sexo_animal = sexo_animal;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    // Getters e Setters para o campo id
    public int getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCpf_cliente(int cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    /**
     * @return the cpf_cliente
     */
    
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
        sb.append("Nome Animal: "+nome_animal+" Especie Animal: "+especie_animal+" Raca Animal: "+raca+
                " Idade Animal: "+idade+" Sexo Animal: "+sexo_animal+" Observacao: "+observacao+" CPF Tutor: "+cpf_cliente);
        /*if(ambulatorio!= null)
            sb.append(" andar: "+ambulatorio.getAndar()+" capacidade: "+ambulatorio.getCapacidade());*/
        return sb.toString();
    }       
}