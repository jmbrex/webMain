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
    
    @RequestMapping("/login")
    
    public String login(Model modelo){
        return "login";
                
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
        
            Doc doc = new Doc();
            DbMongoDB mongo = new DbMongoDB();
            DbSQL SQL = new DbSQL();
            
            
            User U2 = SQL.sqlUserSelectNickName(InputNick);
            User u1 = new User(0,InputName,InputNick, InputEmail, InputSenha);
            
            if(U2.getNickName().equals(InputNick)){
                 modelo.addAttribute("mensagem", "NickName ja existente: "+ u1.getNickName());
                 return "login";
            }else{
                System.out.println("a");
                mongo.MongoInsertDB("Main", "UserMain", doc.UserToDoc(u1));
                SQL.sqlDbUserInsert(u1);
                modelo.addAttribute("mensagem", "Cadastro Realizado: "+ u1.getNickName());
            }
            
        return "login";
        
    }
}
