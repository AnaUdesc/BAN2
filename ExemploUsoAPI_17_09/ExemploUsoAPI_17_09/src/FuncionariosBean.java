import java.sql.Date;

public class FuncionariosBean {
    private String nome_funcionario;
    private String sobrenome_funcionario;
    private Date data_nascimento;
    private int cpf_funcionario;
    private String telefone;
    private String email;
    private Date data_contratacao;
    private String status;
    private String observacoes;
    private int matricula;

    // Construtor

    public FuncionariosBean(String nome_funcionario, String sobrenome_funcionario, Date data_nascimento,int cpf_funcionario , String telefone, String email, Date data_contratacao, String status, String observacoes) {
        //this.matricula = matricula;
        this.nome_funcionario = nome_funcionario;
        this.sobrenome_funcionario = sobrenome_funcionario;
        this.data_nascimento = data_nascimento;
        this.cpf_funcionario = cpf_funcionario;
        this.telefone = telefone;
        this.email = email;
        this.data_contratacao = data_contratacao;
        this.status = status;
        this.observacoes = observacoes;
    }

    // Métodos getters e setters

    public int getMatriculaFuncionario() {
        return matricula;
    }

    public void setMatriculaFuncionario(int matricula) {
        this.matricula = matricula;
    }
    
    public String getNomeFuncionario() {
        return nome_funcionario;
    }

    public void setNomeFuncionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getSobrenomeFuncionario() {
        return sobrenome_funcionario;
    }

    public void setSobrenomeFuncionario(String sobrenome_funcionario) {
        this.sobrenome_funcionario = sobrenome_funcionario;
    }
    
    public Date getDataNascimento() {
        return data_nascimento;
    }

    public void setDataNascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    
    public int getCpfFuncionario() {
        return cpf_funcionario;
    }

    public void setCpfFuncionario(int cpf_funcionario) {
        this.cpf_funcionario = cpf_funcionario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataContratacao() {
        return data_contratacao;
    }

    public void setDataContratacao(Date data_contratacao) {
        this.data_contratacao = data_contratacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("Nome funcionario: "+nome_funcionario+" Sobrenome Funcionario: "+sobrenome_funcionario+" Data Nascimento: "+data_nascimento+
                " CPF : "+cpf_funcionario+" Telefone: "+telefone+" Email: "+email+" Data Contratacao: "+data_contratacao+" Status: "+status+" Observacoes: "+observacoes);
        /*if(ambulatorio!= null)
            sb.append(" andar: "+ambulatorio.getAndar()+" capacidade: "+ambulatorio.getCapacidade());*/
        return sb.toString();
    } 
    
}
