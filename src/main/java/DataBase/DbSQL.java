package DataBase;

import com.SiteMain.Main.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DbSQL {
    private static Connection conexao_MySql = null;
    private static String localBD = "localhost";
    private static String LINK =
            "jdbc:mysql://" + localBD + ":3306/Site"; // MUDAR NOME DA DB SELECIONADA
    private static final String usuario = "root";
    private static final String senha = "";
    
    //CRIANDO CONEXÃO COM MYSQL
    public Connection connectionMySql(){
        try{
            conexao_MySql = DriverManager.getConnection(LINK, usuario, senha);
            System.out.println("Conexao OK!");
        }catch(SQLException e){
            throw new RuntimeException("Ocorreu um Erro");
                
        }
        return conexao_MySql;
    }
    // FECHANDO CONEXÃO COM MYSQL
    public void closeConnectionMySql(Connection con){
        try{
            if(con != null){
                con.close();
                System.out.println("DB Fechado");
            }
        }catch(SQLException e){
            throw new RuntimeException("Ocorreu um problema para encerrar"+"a conexâo com o BD.", e);
        }
    }
    
    //INSERINDO USUARIO NA TEBELA USUARIO
    public void sqlDbUserInsert(User user){
        Connection connection = connectionMySql();
        
        String StringSQL = "insert into usuario(ID,Nome,NickName,email, Senha)values(null,?,?,?,? )";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = connection.prepareStatement(StringSQL);
            preparedStmt.setString(1, user.getNome());
            preparedStmt.setString(2, user.getNickName());
            preparedStmt.setString(3, user.getEmail());
            preparedStmt.setString(4, user.getSenha());
            preparedStmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();}
        closeConnectionMySql(connection);
    }
    
    //SELECIONANDO USUARIO PELO NICKNAME
    public User sqlUserSelectNickName(String Nome){
        User p1 = new User();
        
        Connection connection = connectionMySql();
        String StringSQL = "Select * from usuario where NickName  = ?";
        PreparedStatement preparedStmt;
        
        try{
            preparedStmt = connection.prepareStatement(StringSQL);
            preparedStmt.setString(1,Nome);
            ResultSet result = preparedStmt.executeQuery();
            while(result.next()){
                p1.setID(result.getInt("ID"));
                p1.setNome(result.getString("Nome"));
                p1.setNickName(result.getString("NickName"));
                p1.setEmail(result.getString("email"));
               p1.setSenha(result.getString("Senha"));
            }
        }catch  (SQLException e){
            e.printStackTrace();
            
        }
        closeConnectionMySql(connection);
        
        if(p1.getNickName() != null){
           return p1; 
        }else{
            p1 = new User();
            p1.setID(0);
            p1.setNickName("");
            return p1;
        }
        
        
    }
}
