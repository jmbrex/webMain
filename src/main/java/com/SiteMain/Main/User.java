
package com.SiteMain.Main;

public class User {
    private String Nome, Email, Senha, NickName;
    private int ID;
    public User(){}
    public User(int ID, String Nome,String NickName, String Email, String Senha) {
        this.NickName = NickName;
        this.Nome = Nome;
        this.Email = Email;
        this.Senha = Senha;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }
    
    
    
    
}
