package mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Artigo;

public class ArtigoBD {
	  private BDjdbc conexao;
	  
	  public ArtigoBD(BDjdbc conexao_){
	  	     this.conexao = conexao_;
	  }
		
	public ArrayList<Artigo> ConsultarArtigoBD()throws Exception{
			
		  
		  ArrayList<Artigo> artigos = new ArrayList<Artigo>();
		  PreparedStatement Stmt;
		  ResultSet  rs;
	      Stmt = conexao.getConexao().prepareStatement(
	      "SELECT TITULO,NOTA FROM avaliacao"
	      + " JOIN artigo ON artigo.id = avaliacao.artigo_id");
	      rs = Stmt.executeQuery();
	      if (!(rs.next())) return null;
	      
	      while (rs.next()){
		      Artigo art = new Artigo(
		    		  rs.getString("titulo"),
		    		  rs.getDouble("nota")
    		  );
		      artigos.add(art);

	      }
	      Stmt.close();
	      rs.close();
	      //String stringArtigos = artigosToString(artigos);
	      return artigos;
	      
		}
	public String artigosToString(ArrayList<Artigo> artigos) {
		String artigosString = "";
		for(int i = 0; i< artigos.size();i++) {
			artigosString += "Titulo: "+artigos.get(i).getTitulo()+" Nota:"+artigos.get(i).getNota()+"\n";
		}
		return artigosString;
	}
}
