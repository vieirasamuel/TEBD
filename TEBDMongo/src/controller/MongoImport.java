package controller;
import mysql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.TransactionBody;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;

import model.Artigo;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;









public class MongoImport {
	
	private BDjdbc conexao = new BDjdbc();
	private ArtigoBD artigoBD;
	public static final int CONSULTAR = 1;
	public static final int INSERIR = 2;

		
	
	public MongoImport() throws Exception {
		conexao.setConexao();
		artigoBD = new ArtigoBD(conexao);
	}
	
	public int criaMenuPrincipal(){
		int opcao;
		System.out.println("Menu de Opcoes:");
		System.out.println("-------------------");
		System.out.println("1. Consultar Artigos");
		System.out.println("2. Importar Artigos");
		System.out.println("4. Sair do Programa");
		System.out.println("-------------------");
		return opcao = Console.readInt();
	}
	
	
	
	
	public ArrayList<Document> geraDocument() throws Exception {
		
		ArrayList<Artigo> artigos = new ArrayList<Artigo>();
				
		ArrayList<Document> documents = new ArrayList<Document>();
		
		artigos = artigoBD.ConsultarArtigoBD();
		
		System.out.println(artigos);
		
		for(int i = 0; i< artigos.size();i++) {
			Document document = new Document();
			document.append("TITULO", artigos.get(i).getTitulo());
			document.append("NOTA", artigos.get(i).getNota());
			documents.add(document);
		}

		return documents;
	}
	
	public void importarArtigos(MongoClient mongoClient, ArrayList<Document> documents) {
        
		ClientSession session = mongoClient.startSession();
        
        TransactionOptions txnOptions = TransactionOptions.builder()
        			.readPreference(ReadPreference.primary())
        			.readConcern(ReadConcern.LOCAL)
        			.writeConcern(WriteConcern.MAJORITY)
        			.build();
        
        TransactionBody txnBody = new TransactionBody<String>() {
        	public String execute() {
        		MongoCollection<Document> coll = mongoClient.getDatabase("TEBDMONGO").getCollection("ARTIGO");
        		
        		try {
					coll.insertMany(session, documents);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	
        		return "Registros inseridos com sucesso";
        	}
        };      
        
        try {
        	session.withTransaction(txnBody,txnOptions);
        } catch (RuntimeException e) {
        	System.out.println(e);
        } finally {
        	session.close();
        }
	}
	
	public void consultarArtigos(MongoCollection<Document> collection) {
		Block<Document> printBlock = new Block<Document>() {
		       @Override
		       public void apply(final Document document) {
		           System.out.println(document.toJson());
		       }
		};
			    
     
		collection.find().forEach(printBlock);
	}
	
	

	public static void main(String[] args) {
		
		MongoClient mongoClient = MongoClients.create();
		
		MongoDatabase database = mongoClient.getDatabase("TEBDMONGO");
		
		for (String name : database.listCollectionNames()) {
		    System.out.println(name);
		}
		
		MongoCollection<Document> collection = database.getCollection("ARTIGO");
		
		try {
			MongoImport cp = new MongoImport();
			int opcao;
			while((opcao = cp.criaMenuPrincipal()) != 3){
				if(opcao == cp.INSERIR) {
					ArrayList<Document> documents = new ArrayList<Document>();
					documents = cp.geraDocument();
					cp.importarArtigos(mongoClient, documents);
				} else if (opcao == cp.CONSULTAR)
					cp.consultarArtigos(collection);
				else System.out.println("Escolha uma opcao correta.");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
        
        
	}

}
