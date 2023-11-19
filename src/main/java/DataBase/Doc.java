/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import com.SiteMain.Main.User;
import com.mongodb.MongoClient;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class Doc {
    public Document UserToDoc(User U1) {
        Document doc = new Document();
        
        //doc.append("_id", U1.getID());
        doc.append("UserName", U1.getNome());
        doc.append("NickName", U1.getNickName());
        doc.append("Email", U1.getEmail());
        doc.append("PassWord", U1.getSenha());
        
        //doc.append("Personagens", U1.getUserPersonagens()+";");
        
        return doc;
    }
    
    public User DocToUser(Document Doc){
        User p1 = new User();
        
        p1.setID((int) Doc.get("_id"));
        p1.setNome((String) Doc.get("UserName"));
        p1.setNickName((String) Doc.get("NickName"));
        p1.setEmail((String) Doc.get("Email"));
        p1.setSenha((String) Doc.get("PassWord"));
        
        
        return p1;
    }
    
}
