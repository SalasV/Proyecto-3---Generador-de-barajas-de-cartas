package implementaciones;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import com.google.gson.Gson;
import interfaces.ICarta;
import modelos.Carta;

public class ExistCartaImp implements ICarta {
	private static ExistCartaImp cardExist;
	final String driver = "org.exist.xmldb.DatabaseImpl";
	private static String URI = "xmldb:exist://localhost:2506/exist/xmlrpc/db/Cartas";
	private ArrayList<Carta> cartas = new ArrayList<Carta>();
	private Database database;
	Collection col;
	XMLResource res;
	Class cl;
	

	/**
	 * Metodo para conectarse a exist db
	 */
	private void connect() {
		try {
			cl = Class.forName(driver);
			database = (Database) cl.newInstance();
			DatabaseManager.registerDatabase(database);
			System.out.println("Conectado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor privado para obligar a llamar al getInstance y implementar el
	 * Sinlgeton
	 */
	private ExistCartaImp() {
		connect();
		try {
			Collection col = DatabaseManager.getCollection(URI);
			res = (XMLResource) col.getResource("Cartas.xml");
			JSONObject xmlJSONObj = XML.toJSONObject((String) res.getContent());
			JSONArray allCards = xmlJSONObj.getJSONObject("cards").getJSONArray("card");

			for (Object object : allCards) {
				Carta carta = new Gson().fromJson(object.toString(), Carta.class);
				cartas.add(carta);
			}

		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Patron signleton para instanciar solo una vez el objeto y que asi no de
	 * errores
	 * 
	 * @return
	 */
	public static ExistCartaImp getInstance() {

		if (cardExist == null) {
			cardExist = new ExistCartaImp();
		}
		return cardExist;
	}

	/**
	 * Metodo que nos devuelve un ArrayList con las cartas del xml
	 */
	public ArrayList<Carta> getCards() {
		return cartas;
	}

	public Carta getCard(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
