/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ana Clara
 */
public class ClientesBean {
   private int cpf_cliente;
   private String nome_cliente;
   private String sobrenome_cliente; 
   private String email;
   private String rua;
   private String cidade;
   private String estado;
   private String pais;
   private String telefone;

   
   public ClientesBean(int cpf_cliente, String nome_cliente, String sobrenome_cliente, String email, String rua, String cidade, String estado, String pais, String telefone) {
       this.cpf_cliente= cpf_cliente;
       this.nome_cliente = nome_cliente;
       this.sobrenome_cliente = sobrenome_cliente;
       this.email = email;
       this.rua = rua;
       this.cidade = cidade;
       this.estado = estado;
       this.pais   = pais;
       this.telefone = telefone;
   }  
   
    /**
     * @return the nroa
     */
    public int getCpf_cliente() {
        return cpf_cliente;
    }

    /**
     * @param cpf_cliente the nroa to set
     */
    public void setCpf_cliente(int cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    /**
     * @return the capacidade
     */
    public String getNome_cliente() {
        return nome_cliente;
    }

    /**
     * @param nome_cliente the capacidade to set
     */
    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    /*Sobrenome cliente*/
    public String getSobrenome_cliente() {
        return sobrenome_cliente;
    }

    /**
     * @param sobrenome_cliente the capacidade to set
     */
    public void setSobrenome_cliente(String sobrenome_cliente) {
        this.sobrenome_cliente = sobrenome_cliente;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return the rua
     */
    public String getRua() {
        return rua;
    }

    /**
     * @param rua the email to set
     */
    public void setRua(String rua) {
        this.rua = rua;
    }
   
   /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the email to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the email to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the email to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the email to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    public String toString(){
        return "CPF: "+cpf_cliente+" Nome: "+nome_cliente+" Sobrenome: "+sobrenome_cliente+" Email: "+email+" Rua: "+rua+" Cidade: "+cidade+" Estado: "+estado+" Pais: "+pais+" Telefone "+telefone;
    }
}