package dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import modelo.Farmacia;
import modelo.Medicamento;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class FarmaciaDOM{
	private static final String DOM_XML_FILE = "xml/MedicamentosDOM.xml";
	/**
	 * Lee los medicamentos de la farmacia de un fichero xml
	 * mediante DOM
	 * @paramfarmacia
	 * @return
	 */
	public Farmacia leer(Path farmaciaXML) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Farmacia farmacia = new Farmacia();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();

			Document document = builder.parse(new File(DOM_XML_FILE));
			document.getDocumentElement().normalize();

			// Obtenemos la lista de nodos con nombre empleado de todo el documento
			NodeList medicamentos = document.getElementsByTagName("medicamento");

			for (int i = 0; i < medicamentos.getLength(); i++) {
				Node medica = medicamentos.item(i); // obtener un nodo
				if (medica.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) medica; // tipo de nodo
					System.out.println("Codido: " + getNodo("cod", elemento));
					System.out.println("Nombre: " + getNodo("nombre", elemento));
					System.out.println("Precio: " + getNodo("precio", elemento));
					System.out.println("Stock: " + getNodo("stock", elemento));
					System.out.println("Stock Maximo: " + getNodo("stockMaximo", elemento));
					System.out.println("Stock Minimo: " + getNodo("stockMinimo", elemento));
					System.out.println("Cod. Proveedor: " + getNodo("codProveedor", elemento));
				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return farmacia;
		
	}

	private static String getNodo(String etiqueta, Element elem) {
		NodeList nodo = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
		Node valornodo = (Node) nodo.item(0);
		return valornodo.getNodeValue(); // devuelve el valor del nodo
	}

	/**
	 * Guarda los medicamentos de la farmacia en un fichero XML 
	 * mediamente DOM
	 * @paramfarmacia
	 * @return
	 */
	public boolean guardar(Farmacia farmacia) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();

			DOMImplementation implementation = builder.getDOMImplementation();

			Document document = implementation.createDocument(null, "Medicamentos", null);
			document.setXmlVersion("1.0"); // asignamos la version de nuestro XML

			for (Medicamento med: farmacia.getMedicamentos()) {
				Element raiz = document.createElement("medicamento");

				document.getDocumentElement().appendChild(raiz);

				CrearElemento("cod", String.valueOf(med.getCod()), raiz, document);
				CrearElemento("nombre", med.getNombre(), raiz, document);
				CrearElemento("precio", String.valueOf(med.getPrecio()), raiz, document);
				CrearElemento("stock", String.valueOf(med.getStock()), raiz, document);
				CrearElemento("stockMaximo", String.valueOf(med.getStockMaximo()), raiz, document);
				CrearElemento("stockMinimo", String.valueOf(med.getStockMinimo()), raiz, document);
				CrearElemento("codProveedor", String.valueOf(med.getCodProveedor()), raiz, document);
			}

			// Creamos la fuente XML a partir del documento
			Source source = new DOMSource(document);
			// Creamos el resultado en el fichero Empleados.xml
			Result result = new StreamResult(new java.io.File(DOM_XML_FILE));
			// Obtenemos un TransformerFactory
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			// Le damos formato y realizamos la transformación del documento a fichero
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(source, result);
			// Mostramos el documento por pantalla especificando el canal de salida el
			// System.out
			//Result console = new StreamResult(System.out);
			//transformer.transform(source, console);

		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
		
	}

	static void CrearElemento(String datoMedi, String valor, Element raiz, Document document) {
		Element elem = document.createElement(datoMedi);
		Text text = document.createTextNode(valor);
		raiz.appendChild(elem);
		elem.appendChild(text);
	}

}
