/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SiteMain.Main;

import DataBase.DbMongoDB;
import DataBase.DbSQL;
import DataBase.Doc;
import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebControler {
    
    @RequestMapping("/Menuindex")
    public String Menuindex(Model modelo){
        return "Menuindex";
    }
    
    @RequestMapping("/cadastro")
    public String login(Model modelo){
        return "cadastro";
    }
    
    @RequestMapping("/catalogo")
    public String catalogo(Model modelo){
        return "catalogo";
    }
    
    @RequestMapping("/contato")
    public String contato(Model modelo){
        System.out.println("Chamando o metodo SayHello");
        return "contato";
    }
    
    @RequestMapping(value = "teste", method = RequestMethod.POST)
    public String Cadastro(Model modelo, String InputName,String InputNick, String InputEmail, String InputSenha, String InputSenhaConfirm){
            //Instanciando objetos para realização dos metodos
            Doc doc = new Doc();
            DbMongoDB mongo = new DbMongoDB();
            DbSQL SQL = new DbSQL();
            //Instanciando objetos da classe usuario 
            //U2 usuario para validação para verificar a existencia de um usuario
            //U1 usuario criado durante o cadastro
            User U2 = SQL.sqlUserSelectNickName(InputNick);
            User U1 = new User(0,InputName,InputNick, InputEmail, InputSenha);
            //Verificando a existencia de um usuario com o mesmo Nickname
            if(U2.getNickName().equals(InputNick)){
                 modelo.addAttribute("mensagem", "Usuario ja existente: "+ U1.getNickName());
                 return "login";
            }else{
                mongo.MongoInsertDB("Main", "UserMain", doc.UserToDoc(U1));
                SQL.sqlDbUserInsert(U1);
                modelo.addAttribute("mensagem", "Cadastro Realizado: "+ U1.getNickName());
            }
        return "login";
    }
}
