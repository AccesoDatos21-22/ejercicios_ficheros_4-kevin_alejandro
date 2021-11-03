package dao;

import jakarta.xml.bind.*;
import modelo.JCCPokemon;

import java.io.File;
import java.util.ArrayList;

public class JCCPokemonJAXB implements JCCPokemonDAO {

	@Override
	public JCCPokemon leer() {
		try {
			JAXBContext context = JAXBContext.newInstance( JCCPokemon.class );
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JCCPokemon jccPokemon = (JCCPokemon)unmarshaller.unmarshal(
					new File("xml/JCCPokemon.xml") );

			return  jccPokemon;

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean guardar(JCCPokemon pokemon) {

		File file = new File("./xml/JCCPokemon.xml");
		JAXBContext jaxbContext = null;

		try {
			jaxbContext = JAXBContext.newInstance(JCCPokemon.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(pokemon, file);
			return true;
		} catch (JAXBException e) {
			e.printStackTrace();
			return false;
		}
	}

}
