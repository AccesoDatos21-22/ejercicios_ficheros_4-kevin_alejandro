package dao;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import modelo.Empleado;
import modelo.Empresa;
import modelo.Farmacia;
import modelo.Medicamento;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FarmaciaXSTREAM implements FarmaciaDAO{
	
	final static String rutaFarmcia =""; // localizacion del fichero XML
	final static String rutaMedicamento =""; // localizacion del fichero XML

	@Override
	public Farmacia leer() {
		Farmacia farmacia = new Farmacia();
		try {
			XStream xstream = new XStream();
			xstream.alias("Farmacia", Farmacia.class);
			xstream.alias("Medicamento", Medicamento.class);
			xstream.addImplicitCollection(Farmacia.class, "Farmacia");

			farmacia = (Farmacia) xstream
					.fromXML(new FileInputStream("xml/MedicamentosXSTREAM.xml"));

			for(Medicamento e: farmacia.getMedicamentos()) {
				System.out.println(e);
			}
		}catch (FileNotFoundException e){
			System.err.println("Error: " + e);
		}

		return null;
	}

	@Override
	public boolean guardar(Farmacia farmacia) {

		try {
			XStream xstream = new XStream();
			xstream.alias("Medicamento", Medicamento.class);
			xstream.alias("Farmacia", Farmacia.class);


			xstream.addImplicitCollection(Farmacia.class, "Farmacia");
			xstream.toXML(farmacia, new FileOutputStream("xml/MedicamentosXSTREAM.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}


	public Medicamento leerMedicamento() {
		
		return null;
	}

	public void guardarMedicamento(Medicamento medicamento) {
	}

	
}
