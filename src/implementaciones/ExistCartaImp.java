package implementaciones;

import java.util.ArrayList;
import java.util.List;

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

public class ExistCartaImp implements ICarta{
	private static ExistCartaImp cardExist;
	final String driver = "org.exist.xmldb.DatabaseImpl";
	private static String URI = "xmldb:exist://localhost:8888/exist/xmlrpc/db/Cartas";
	private Database database;
	Collection col;
	XMLResource res;
	Class cl;
	private ArrayList<Carta> cartas = new ArrayList<Carta>();

	private void connect() {
		try {
			cl = Class.forName(driver);
			database = (Database) cl.newInstance();
			DatabaseManager.registerDatabase(database);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// constructor privado para no poder instanciar directamente y tengamos que
	// llamar al getInstance.
	private ExistCartaImp() {
		// TODO Auto-generated constructor stub
		connect();
		try {
			Collection col = DatabaseManager.getCollection(URI);
			res = (XMLResource) col.getResource("Cartas.xml");
			JSONObject xmlJSONObj = XML.toJSONObject((String) res.getContent());

			JSONArray allCards = xmlJSONObj.getJSONObject("cards").getJSONArray("card");
			//System.out.println(allCards.toString());
			for (Object object : allCards) {
				Carta carta = new Gson().fromJson(object.toString(), Carta.class);
				cartas.add(carta);
			}

		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Patron signleton para instanciar una vez el objeto la primera vez que lo
	// llamemos y nunca mas.
	public static ExistCartaImp getInstance() {

		if (cardExist == null) {
			cardExist = new ExistCartaImp();
		}
		return cardExist;
	}

	public ArrayList<Carta> getCards() {
		// TODO Auto-generated method stub
		return cartas;
	}

	public Carta getCard(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	//Test
	public static void main(String[] args) {
		ExistCartaImp cardImpl = ExistCartaImp.getInstance();
		System.out.println(cardImpl.getCards());
	}

}
