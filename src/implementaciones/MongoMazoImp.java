package implementaciones;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.bson.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import interfaces.IMazo;
import modelos.Mazo;

public class MongoMazoImp implements IMazo {
	
	private static MongoMazoImp mazoMongo;
	final String db = "Proyecto_3";
	final String col = "Mazos";
	private static String URI = "mongodb://localhost:27017";
	private MongoClientURI connector;
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection coleccion;
	
	
	/**
	 * Metodo para conectarse a la base de datos mongo y la coleccion
	 */
	private void connect() {
		try {
			connector = new MongoClientURI(URI);
			mongoClient = new MongoClient(connector);
			database = mongoClient.getDatabase(db);
			coleccion = database.getCollection(col);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor privado para obligar a llamar al getInstance y implementar el
	 * Sinlgeton
	 */
	private MongoMazoImp() {
		connect();
	}

	/**
	 * Patron signleton para instanciar solo una vez el objeto y que asi no de
	 * errores
	 * 
	 * @return
	 */
	public static MongoMazoImp getInstance() {

		if (mazoMongo == null) {
			mazoMongo = new MongoMazoImp();
		}
		return mazoMongo;
	}

	/**
	 * Metodo para obtener un mazo por el nombre
	 */
	@Override
	public Mazo obtenerMazoPorNombre(String name) {
		Mazo mazo;
		MongoCursor<Document> cursor = coleccion.find(Filters.eq("deckName", name)).iterator();
		
		try {
			Document document = cursor.next();
			mazo = new Gson().fromJson(document.toJson(), Mazo.class);
		} catch (NoSuchElementException e) {
			mazo = null;
		}
		return mazo;
	}


	/**
	 * Metodo que inserta el mazo en la BBDD
	 */
	@Override
	public void insertarMazo(Mazo mazo) {
		ArrayList<Object> cartasMazo = new BasicDBList();
		Document doc = new Document();
		
		doc.put("deckName", mazo.getNombre());
		for (int i = 0; i < mazo.getCartas().size(); i++) {
			DBObject object = new BasicDBObject();
			object.put("id", mazo.getCartas().get(i).getId());
			object.put("name", mazo.getCartas().get(i).getNombre());
			object.put("summonCost", mazo.getCartas().get(i).getCoste());
			object.put("attack", mazo.getCartas().get(i).getAtaque());
			object.put("defense", mazo.getCartas().get(i).getDefensa());
			object.put("value", mazo.getCartas().get(i).getValorCarta());
			cartasMazo.add(object);
		}
		doc.put("deckValue", mazo.getValorMazo());
		doc.put("deck", cartasMazo);
		
		coleccion.insertOne(doc);
	}
	

	@Override
	public void actualizarMazo(Mazo mazo) {
		ObjectMapper mapper = new ObjectMapper();
			String mazoJson = null;
			try {
				//pasamos el mazo a Json
				mazoJson = mapper.writeValueAsString(mazo);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			//Realizamos la actualizacion
			Document userDoc = Document.parse(mazoJson);
			coleccion.replaceOne(Filters.eq("deckName", mazo.getNombre()), userDoc);
	}
}
