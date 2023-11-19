/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;//add
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;


public class DbMongoDB {
    public void MongoInsertDB (String dataBaseName, String dataBaseCollection, Document ParameterDoc){
        MongoClient Client = MongoClients.create("mongodb://localhost");
        MongoDatabase DT = Client.getDatabase(dataBaseName);
        MongoCollection<Document> docDataBase = DT.getCollection(dataBaseCollection);
        docDataBase.insertOne(ParameterDoc);
        System.out.println("insertValues ok");
        
    }
    
    public void MongoUpdateDB(String dataBaseName, String DataBaseCollection, String FilterAtributo, String FilterAtributoValue, String UpdatedAtributo, String UpdatedValue){
        System.out.println("updateValues");
        MongoClient mongo = MongoClients.create("mongodb://localhost");
        MongoDatabase db = mongo.getDatabase(dataBaseName);
        MongoCollection<Document> docs = db.getCollection(DataBaseCollection);
        docs.updateOne(Filters.eq(FilterAtributo, FilterAtributoValue), Updates.set(UpdatedAtributo, UpdatedValue));
        System.out.println("Documento teve sucesso no update...");
        for (Document doc : docs.find()) {
            System.out.println("item update: " + doc);
        }
    }
    public void MongoDeleteDB(String dataBase, String DataBaseCollection, String FilterAtributo, String FilterAtributoValue){
        System.out.println("deleteValues");
        MongoClient mongo = MongoClients.create("mongodb://localhost");

        MongoDatabase db = mongo.getDatabase(dataBase);
        MongoCollection<Document> docs = db.getCollection(DataBaseCollection);

        docs.deleteOne(Filters.eq(FilterAtributo, FilterAtributoValue));
        System.out.println("Documento teve sucesso no delete...");
    }
    
    public Document MongoGetDB(String dataBase, String DataBaseCollection,String VarSearchType, String  FilterAtributo, String FilterAtributoValue){
        System.out.println("getValues");
        MongoClient mongo = MongoClients.create("mongodb://localhost");
        MongoDatabase db = mongo.getDatabase(dataBase);
        MongoCollection<Document> docs = db.getCollection(DataBaseCollection);
        for (Document doc : docs.find()) {
            if(VarSearchType.equals("String")){
                if(doc.get(FilterAtributo).equals(FilterAtributoValue)){
                    return doc;
                }else{
                    System.out.println("Não encontrado");
                    return null;
                }
            }else{
                if(doc.get(FilterAtributo).equals(Integer.parseInt(FilterAtributoValue))){
                    return doc;
                }else{
                    System.out.println("Não encontrado");
                    return null;
                }
            }
            
        }
        return null;

    }
    
    public void MongoDropCollection(String dataBase ,String DataBaseCollection){
        System.out.println("deleteValues");
        MongoClient mongo = MongoClients.create("mongodb://localhost");

        MongoDatabase db = mongo.getDatabase(dataBase);
        MongoCollection<Document> docs = db.getCollection(DataBaseCollection);

        docs.drop();
        System.out.println("Documento teve sucesso no delete...");
    }
    
    public Document MongoGetDBcollection(String dataBase, String DataBaseCollection){
        System.out.println("getValues");
        MongoClient mongo = MongoClients.create("mongodb://localhost");
        MongoDatabase db = mongo.getDatabase(dataBase);
        MongoCollection<Document> docs = db.getCollection(DataBaseCollection);
        for (Document doc : docs.find()) {
            return doc;
            
        }
        return null;

    }
}
