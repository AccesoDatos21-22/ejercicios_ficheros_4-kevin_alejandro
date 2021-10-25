package Main;

import com.thoughtworks.xstream.XStream;
import dao.JCCPokemonJAXB;
import dao.MedicamentoAleatorio;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import modelo.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Main {

	private static final String JAXB_XML_FILE = "xml/EmpresaJAXB.xml";
	private static final String XSTREAM_XML_FILE = "xml/EmpresaXTREAM.xml";
	private static final String DOM_XML_FILE = "xml/EmpleadosDOM.xml";

	private static Scanner sc = new Scanner(System.in);
	private static Scanner sn = new Scanner(System.in);
	private static MedicamentoAleatorio mA = new MedicamentoAleatorio();
	private static JCCPokemonJAXB jccPokemonJAXB  = new JCCPokemonJAXB();

	public static void main(String[] args) {
		// ejemploJaxb();
		// ejemploEscribirDOM();
		// ejemploLeerDOM();
		// ejemploEscribirXSTREAM();
		// ejemploLeerXSTREAM();
		int opc = 0;
		String opcString ="";

		do {
			System.out.println("*****\n Intoduzca una opción: *****");
			System.out.println("1. Añadir un medicamento.");
			System.out.println("3. Guardar Pokémon en XML.");
			System.out.println("4. Leer Pokémon del XML.");
			System.out.println("5. Salir.");
			opcString =  sc.nextLine();

			if (esNumero(opcString)){
				opc = Integer.parseInt(opcString);

				switch (opc) {
					case 1 :
						System.out.println("Introduzca el nombre del medicamento: ");
						String nombreMedicamento = sc.nextLine();
						System.out.println("Introduzca el precio del medicamento: ");
						double precioMedicamento = sn.nextDouble();
						System.out.println("Introduzca el stock actual del medicamento: ");
						int stock = sn.nextInt();
						System.out.println("Introduzca el stock máximo del medicamento: ");
						int stockMaximo = sn.nextInt();
						System.out.println("Introduzca el stock mínimo del medicamento: ");
						int stockMinimo = sn.nextInt();
						System.out.println("Introduzca el código del proveedor del medicamento: ");
						int codProveedor = sn.nextInt();
						Medicamento medicamento = new Medicamento(nombreMedicamento,
								precioMedicamento, 0, stock, stockMaximo, stockMinimo, codProveedor);
						mA.guardar(medicamento);
						break;

					case 3 :
						List<Pokemon> nuevosPokemos = new ArrayList<Pokemon>();
						Pokemon pokemon = new Pokemon("Paco", 23, 248, 357, 214, 304, 109, 124);
						Pokemon pokemon1 = new Pokemon("Paca", 28, 237, 645, 156, 584, 465, 706);
						Pokemon pokemon2 = new Pokemon("Pika", 31, 167, 456, 405, 210, 543, 640);
						Pokemon pokemon3 = new Pokemon("Roler", 26, 301, 783, 640, 745, 654, 540);

						nuevosPokemos.add(pokemon);
						nuevosPokemos.add(pokemon1);
						nuevosPokemos.add(pokemon2);
						nuevosPokemos.add(pokemon3);

						JCCPokemon jccPokemon = new JCCPokemon(nuevosPokemos, new Date(), 23);

						if (jccPokemonJAXB.guardar(jccPokemon)){
							System.out.println("Pokemón guardado correctamente");
						}else{
							System.out.println("Error al guardar el Pokemón");
						}
						break;
					case 4 :
						System.out.println(jccPokemonJAXB.leer().toString());
						break;
					case 5:
						System.out.println("Adiós");
						System.exit(0);
						break;
					default:
						System.out.println("Opción no válida");
						break;
				}
			}else {
				System.out.println("Introduzca un número válido");
			}

		}while (opc != 5);
	}

	private static boolean esNumero(String numero) {
		return numero.chars().allMatch(Character::isDigit);
	}

	private static void ejemploEscribirXSTREAM() {

		try {

			System.out.println("Comienza el proceso de creación del fichero a XML...");

			XStream xstream = new XStream();

			long time = System.currentTimeMillis();
			System.out.println("Inicio: " + new Date(time));
			Empresa cc = new Empresa();
			cc.setIdEmpresa(1);
			cc.setDireccion("En la nube");
			cc.setNombreEmpresa("IES");
			cc.setNumEmpleados(10);

			ArrayList<Empleado> alCU = new ArrayList<Empleado>();
			int init = 20000;
			for (int i = 1; i < 10; i++) {
				Empleado cu = new Empleado();
				cu.setId(i);
				cu.setActivo(true);
				cu.setNumeroEmpl(init++);
				cu.setNombre("Empleado " + i);
				cu.setTitulo("SW Architect");
				cu.setFechaAlta(new Date(System.currentTimeMillis()));

				alCU.add(cu);
			}

			cc.setEmpleados(alCU);

			// cambiar de nombre a las etiquetas XML
			xstream.alias("Empleado", Empleado.class);
			xstream.alias("Empresa", Empresa.class);

			// quitar etiqueta lista (Atributo de la clase ListaEmpleados)
			xstream.addImplicitCollection(Empresa.class, "Empresa");
			// Insertar los objetos en XML
			xstream.toXML(cc, new FileOutputStream(XSTREAM_XML_FILE));
			System.out.println("Creado fichero XML....");

		} catch (IOException e) {
			System.err.println("Error: " + e);
		}
	}

	private static void ejemploLeerXSTREAM() {
		Empresa empresa = new Empresa();
		try {
			Class<?>[] classes = new Class[] { Empresa.class, Empleado.class };

			XStream xstream = new XStream();
			//XStream.setupDefaultSecurity(xstream);
			//xstream.allowTypes(classes);

			xstream.alias("Empresa", Empresa.class);
			xstream.alias("Empleado", Empleado.class);
			xstream.addImplicitCollection(Empresa.class, "Empresa");

			empresa = (Empresa) xstream
					.fromXML(new FileInputStream(XSTREAM_XML_FILE));

			for(Empleado e: empresa.getEmpleados()) {
				System.out.println(e);
			}

		} catch (FileNotFoundException e) {
			System.err.println("Error: " + e);
		}

	}

	private static void ejemploLeerDOM() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();

			Document document = builder.parse(new File(DOM_XML_FILE));
			document.getDocumentElement().normalize();

			// Obtenemos la lista de nodos con nombre empleado de todo el documento
			NodeList empleados = document.getElementsByTagName("empleado");

			for (int i = 0; i < empleados.getLength(); i++) {
				Node emple = empleados.item(i); // obtener un nodo
				if (emple.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) emple; // tipo de nodo
					System.out.println("ID: " + getNodo("id", elemento));
					System.out.println("Apellido: " + getNodo("nombre", elemento));
					System.out.println("Departamento: " + getNodo("dep", elemento));
					System.out.println("Salario: " + getNodo("salario", elemento));
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
	}

	// obtener información de un nodo
	private static String getNodo(String etiqueta, Element elem) {
		NodeList nodo = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
		Node valornodo = (Node) nodo.item(0);
		return valornodo.getNodeValue(); // devuelve el valor del nodo
	}

	private static void ejemploEscribirDOM() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();

			DOMImplementation implementation = builder.getDOMImplementation();

			Document document = implementation.createDocument(null, "Empleados", null);
			document.setXmlVersion("1.0"); // asignamos la version de nuestro XML

			for (int i = 1; i < 10; i++) {
				Element raiz = document.createElement("empleado");

				document.getDocumentElement().appendChild(raiz);

				CrearElemento("id", Integer.toString(i), raiz, document);
				CrearElemento("nombre", "Empleado " + i, raiz, document);
				CrearElemento("dep", "01", raiz, document);
				CrearElemento("salario", "1000.0", raiz, document);
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
			Result console = new StreamResult(System.out);

			transformer.transform(source, console);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static void CrearElemento(String datoEmple, String valor, Element raiz, Document document) {
		Element elem = document.createElement(datoEmple);
		Text text = document.createTextNode(valor);
		raiz.appendChild(elem);
		elem.appendChild(text);
	}

	private static void ejemploJaxb() {
		long time = System.currentTimeMillis();
		System.out.println("Inicio: " + new Date(time));
		Empresa cc = new Empresa();
		cc.setIdEmpresa(1);
		cc.setDireccion("En la nube");
		cc.setNombreEmpresa("IES");
		cc.setNumEmpleados(10);

		ArrayList<Empleado> alCU = new ArrayList<Empleado>();
		int init = 20000;
		for (int i = 1; i < 10; i++) {
			Empleado cu = new Empleado();
			cu.setId(i);
			cu.setActivo(true);
			cu.setNumeroEmpl(init++);
			cu.setNombre("Empleado " + i);
			cu.setTitulo("SW Architect");
			cu.setFechaAlta(new Date(System.currentTimeMillis()));

			alCU.add(cu);
		}

		cc.setEmpleados(alCU);

		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Empresa.class);

			// Si las clases a serializar están en otro paquete se indica el paquete
			// al crear el marshall
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// Provincia provincia = fillProvincia();
			// Mostramos el documento XML generado por la salida estandar
			marshaller.marshal(cc, System.out);
			// guardamos el objeto serializado en un documento XML
			marshaller.marshal(cc, Files.newOutputStream(Paths.get(JAXB_XML_FILE)));
			Unmarshaller unmarshaller = context.createUnmarshaller();
			// Deserealizamos a partir de un documento XML
			Empresa empresa = (Empresa) unmarshaller.unmarshal(Files.newInputStream(Paths.get(JAXB_XML_FILE)));
			System.out.println("********* Empresa cargado desde fichero XML***************");
			// Mostramos por linea de comandos el objeto Java obtenido
			// producto de la deserialziacion
			marshaller.marshal(empresa, System.out);
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}
}
