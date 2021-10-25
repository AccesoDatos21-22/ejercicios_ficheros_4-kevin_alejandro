package dao;

import com.thoughtworks.xstream.XStream;
import modelo.Empleado;
import modelo.Empresa;
import modelo.Farmacia;
import modelo.Medicamento;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class FarmaciaXSTREAM implements FarmaciaDAO{
	
	final static String rutaFarmcia =""; // localizacion del fichero XML
	final static String rutaMedicamento =""; // localizacion del fichero XML

	@Override
	public Farmacia leer() {


		return null;
	}

	@Override
	public boolean guardar(Farmacia farmacia) {
		try {

			System.out.println("Comienza el proceso de creación del fichero a XML...");

			XStream xstream = new XStream();

			// cambiar de nombre a las etiquetas XML
			xstream.alias("Medicamento", Empleado.class);
			xstream.alias("Empresa", Empresa.class);

			// quitar etiqueta lista (Atributo de la clase ListaEmpleados)
			xstream.addImplicitCollection(Empresa.class, "Empresa");
			// Insertar los objetos en XML
			xstream.toXML(medList, new FileOutputStream("xml/MedicamentosXTREAM.xml"));
			System.out.println("Creado fichero XML....");

		} catch (IOException e) {
			System.err.println("Error: " + e);
		}

		return false;
	}


	public Medicamento leerMedicamento() {
		
		return null;
	}

	public void guardarMedicamento(Medicamento medicamento) {
	}

	
}
