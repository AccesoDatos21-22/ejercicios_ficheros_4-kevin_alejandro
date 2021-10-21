package dao;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import modelo.Medicamento;

public class MedicamentoAleatorio implements MedicamentoDAO {
	
	public final static int TAM_NOMBRE = 30;
	public final static int TAM_REGISTRO = 88;
	public static final String FICHERO = "medicamentos.bin";
	
	@Override
	public boolean guardar(Medicamento medicamento) {
		try (RandomAccessFile raf = new RandomAccessFile(FICHERO, "rw")) {
			// posiciono en el registro del empleado a insertar
			raf.seek((long) medicamento.getCod() * TAM_REGISTRO);
			// Escribo el Código
			raf.writeInt(medicamento.getCod());
			// Escribo el nombre
			StringBuilder nombre = new StringBuilder(medicamento.getNombre());
			nombre.setLength(TAM_NOMBRE);
			raf.writeChars(nombre.toString());
			// Escribo el Precio
			raf.writeDouble(medicamento.getPrecio());
			// Escribo el Stock
			raf.writeInt(medicamento.getStock());
			// Escribo el StockMáximo
			raf.writeInt(medicamento.getStockMaximo());
			// Escribo el StockMínimo
			raf.writeInt(medicamento.getStockMinimo());
			// Escribo el Código del Proveedor
			raf.writeInt(medicamento.getCodProveedor());
		} catch (IOException exception) {
			exception.printStackTrace();
			return false;
		}

		System.out.println("Guardado");
		return true;
	}

	@Override
	public List<Medicamento> buscar(String nombre) {
		
		return null;
	}
	@Override
	public Medicamento buscar(int cod) {

		return null;
	}

	@Override
	public boolean actualizar(Medicamento medicamento) {
		
		return false;
	}

	@Override
	public boolean borrar(Medicamento medicamento) {
		
		return false;
	}



	@Override
	public List<Medicamento> leerTodos() {
		return null;
	}

}
